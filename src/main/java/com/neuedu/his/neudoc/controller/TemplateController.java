package com.neuedu.his.neudoc.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neuedu.his.bean.RespBean;
import com.neuedu.his.neudoc.service.CheckTemplateService;
import com.neuedu.his.neudoc.service.DrugTemplateService;

/** 
* 模板管理Controller 
* @auther: 东软教师
* @date:   2018-11-29
*/ 
@RestController
@RequestMapping("/neupha/template")
public class TemplateController {
    @Autowired
    private DrugTemplateService drugTemplateService;
    @Autowired
    private CheckTemplateService checkTemplateService;
	/**
	 * 成品药模板功能 --- 以下：增删改查 RESTful
	 */
	@RequestMapping(value = "/listDrugTemplate", method = RequestMethod.GET)
    public List<Map<String, Object>> getDrugTemplatePage( String keywords01,String keywords02 ,HttpSession session) {
		String userID = "1";
		try{
			userID = ((Map)session.getAttribute("User")).get("ID").toString();
		}catch(Exception e){
			userID = "1";//默认用户ID为1
		}
		
        List<Map<String, Object>> list = drugTemplateService.getDrugTemplateList(keywords01,keywords02,userID);        
        return list;
    }
	
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public RespBean addDrugTemplate(@RequestParam Map<String,Object> map) {
        int result = drugTemplateService.addDrugTemplate(map);
        if (result == 1) {
            return new RespBean("success", "添加成功!(成品药模板信息)");
        }
        return new RespBean("error", "添加失败!");
    }
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public RespBean updateDrugTemplate(@RequestParam Map<String,Object> map) {
        int result = drugTemplateService.updateDrugTemplateById(map);
        if (result == 1) {
            return new RespBean("success", "修改成功!(成品药模板信息)");
        }
        return new RespBean("error", "修改失败!");
    }
    
    @RequestMapping(value = "/drug/{ids}", method = RequestMethod.DELETE)
    public RespBean delDrugOne(@PathVariable("ids") String ids) {
        boolean result = drugTemplateService.deleteDrugTemplateByIds(ids);
        if (result) {
            return new RespBean("success", "删除成功!(成品药模板信息)");
        }
        return new RespBean("error", "删除失败!");
    }
    /**
	 * 医生根据ID得到可以查看的模板记录（包括同科室医生开立的 范围是科室的模板,不包括作废状态）
	 */
	@RequestMapping(value = "/drug/listTemplateByDoc", method = RequestMethod.GET)
    public RespBean getDrugTemplateByDocid( String Name,String ID,HttpSession session ) {
		RespBean resp = new RespBean();
//		根据登录，得到医生信息
//		TODO
//		Object user = session.getAttribute("loginUser");
//		if(user!=null) {
//			ID = user.getID();
//		}
		resp.setList(drugTemplateService.getDrugTemplateByDocid(ID,Name));
        return resp;
    }
    
    /*
	 * 中草药模板功能
	 * @return
	 */
    
    
    
    /*
	 * 检查、检验、处置模板功能
	 * @return
	 */
    @RequestMapping(value = "/listCheckTemplate", method = RequestMethod.GET)
    public List<Map<String, Object>> getCheckTemplatePage( String keywords01,String keywords02,String keywords03 ) {
        List<Map<String, Object>> list = checkTemplateService.getCheckTemplateList(keywords01, keywords02, keywords03);        
        return list;
    }
    
    /*
	 * 根据范围 类型  医生 查询模板
	 * @return
	 */
    @RequestMapping(value = "/check/listTemplateByDoc", method = RequestMethod.GET)
    public RespBean getTemplateByDoc(String UserID, String RecordType, String Name) {
        RespBean resp = new RespBean();
    	List<Map<String, Object>> list = checkTemplateService.getTemplateByDoc(UserID, RecordType, Name);        
        resp.setList(list);
    	return resp;
    }
	
    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public RespBean addCheckTemplate(@RequestParam Map<String,Object> map) {
        int result = checkTemplateService.addCheckTemplate(map);
        if (result == 1) {
            return new RespBean("success", "添加成功!(医技模板信息)");
        }
        return new RespBean("error", "添加失败!");
    }
    @RequestMapping(value = "/check", method = RequestMethod.PUT)
    public RespBean updateCheckTemplate(@RequestParam Map<String,Object> map) {
        int result = checkTemplateService.updateCheckTemplateById(map);
        if (result == 1) {
            return new RespBean("success", "修改成功!(医技模板信息)");
        }
        return new RespBean("error", "修改失败!");
    }
    
    @RequestMapping(value = "/check/{ids}", method = RequestMethod.DELETE)
    public RespBean delCheck(@PathVariable("ids") String ids) {
        boolean result = checkTemplateService.deleteCheckTemplateByIds(ids);
        if (result) {
            return new RespBean("success", "删除成功!(医技模板信息)");
        }
        return new RespBean("error", "删除失败!");
    }
    
    
}
