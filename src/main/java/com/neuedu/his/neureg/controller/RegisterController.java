package com.neuedu.his.neureg.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neuedu.his.bean.Register;
import com.neuedu.his.bean.RespBean;
import com.neuedu.his.bean.User;
import com.neuedu.his.neureg.service.RegisterService;
import com.neuedu.his.util.ParamUtil;

/** 
* 挂号业务专属Controller 
* @auther: 东软教师
* @date:   2019-3-25
*/ 
@RestController
@RequestMapping("/neureg/register")
public class RegisterController {
    @Autowired
    RegisterService registerService;
    
   /** 
     * 查找病历编号最大的挂号信息对象
     * @param: 无
     * @return: 病历编号最大的挂号信息对象
     */ 
    @RequestMapping(value = "/max_casenumber", method = RequestMethod.GET)
    public Register getRegisterBean() 
    {
    	Register register = registerService.getRegisterBean();
        return register;
    }
    
    /** 
     * 通过病历编号查找指定的挂号信息对象
     * @param: CaseNumber  病历编号
     * @return: 指定的挂号信息对象
     */ 
    @RequestMapping(value = "/get_register", method = RequestMethod.GET)
    public Register getRegByCase(String CaseNumber) 
    {
    	Register register = registerService.getRegByCase(CaseNumber);
        return register;
    }
    
    /** 
     * 添加挂号信息的方法 
     * @param: register 包含科室信息的Map集合
     * @return: 包含响应信息的RespBean对象
     */ 
    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    public RespBean regist(@RequestParam Map<String,Object> register) {
    	int result = registerService.addRegister(register);
        if (result == 1) {
            return new RespBean("success", "挂号成功!(1)");
        }
        return new RespBean("error", "挂号失败!(1)");
    }
    
    /** 
     * 按条件分页查询挂号记录的方法 
     * @param: page当前页   count每页显示记录数量   keywords keywords2查询关键字
     * @return: 包含查询结果的Map集合
     */ 
    @RequestMapping(value = "/register_list", method = RequestMethod.GET)
    public Map<String, Object> getRegisterList( @RequestParam(value = "page", defaultValue = "1") Integer page, 
    		@RequestParam(value = "count", defaultValue = "60") Integer count,String keywords,String keywords2) {
        int totalCount = registerService.getRegisterCount(keywords,keywords2);
        List<Map<String,Object>> list = registerService.getRegisterList( page, count,keywords,keywords2);
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);//totalCount  6
        map.put("list", list);
        
        return map;
    }
    
    /** 
     * 按条件统计 已经挂号的记录数量 
     * @param: keywords keywords2 keywords3 查询关键字
     * @return: 包含查询结果的Map集合
     */ 
    @RequestMapping(value = "/regist_num", method = RequestMethod.GET)
    public int getRegisterList02(String keywords,String keywords2,String keywords3) {
        int totalCount = registerService.getAlreadyRegisterCount(keywords, keywords2, keywords3, 4);
        return totalCount;
    }
    
    /** 
     * 更新挂号信息状态的方法  调用退号功能  只更新 状态字段  患者退号时调用
     * @param: register 包含更新信息的Map集合
     * @return: 包含响应信息的RespBean对象
     */ 
    @RequestMapping(value = "/updateState", method = RequestMethod.PUT)
    public RespBean updateRegisterState(@RequestParam Map<String,Object> register) {
        int i = registerService.updateRegisterStateById(register);
        if (i == 1) {
            return new RespBean("success", "退号成功!(2)");
        }
        return new RespBean("error", "退号失败!(2)");
    }
    
    /**
	 * 医生看诊，得到“1已挂号/2医生接诊”状态的患者,不分页
	 * @param: 医生ID 患者挂号信息的状态
	 * @return: 符合条件的患者挂号信息对象集合
	 */
	@RequestMapping(value = "/listStateRegisters", method = RequestMethod.GET)
    public List<Map<String, Object>> getStateRegisters( String keywords01,String keywords02, HttpSession session ) {
		String docId = "1";
		try {
			User user = (User)session.getAttribute("login_user");
			docId = user.getId();
		}catch(Exception e) {
			docId = ParamUtil.stringToInt(keywords01, 1);
		}
		String state = ParamUtil.stringToInt(keywords02, 1);
        List<Map<String, Object>> list = registerService.getStateRegistersByDoc(docId,state);        
        return list;
    }
}