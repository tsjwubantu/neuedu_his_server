package com.neuedu.his.neudoc.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neuedu.his.bean.RespBean;
import com.neuedu.his.neudoc.service.CheckApplyService;

/** 
* 患者检查/检验/处置申请操作 
* @auther: 东软教师
* @date:   2018-11-29
*/
@RestController
@RequestMapping("/neudoc/apply")
public class CheckApplyController {
    @Autowired
    private CheckApplyService checkApplyService;
    
    /**
     * 根据ID，批量删除检查申请
	 */
    @RequestMapping(value = "/check/{ids}", method = RequestMethod.DELETE)
    public RespBean delDrugOne(@PathVariable("ids") String ids) {
        boolean result = checkApplyService.delCheckApply(ids);
        if (result) {
            return new RespBean("success", "删除成功!(检查申请信息)");
        }
        return new RespBean("error", "删除失败!");
    }
   
	/**
	 * 添加检查申请
	 */
	@RequestMapping(value = "/check", method = RequestMethod.POST)
    public RespBean saveCheck( @RequestParam Map<String,Object> map ) {
		
		int result = checkApplyService.addCheckApply(map);
        if (result > 0) {
            return new RespBean("success", "成功!(检查申请)");
        }
        return new RespBean("error", "添加失败!");
    }
	
	/**
	 * 开立检查申请
	 */
	@RequestMapping(value = "/check", method = RequestMethod.GET)
    public RespBean upToSaved( String ids,String state ) {
		
		int result = checkApplyService.upToSaved(ids,state);
        if (result > 0) {
            return new RespBean("success", "成功!(检查申请)");
        }
        return new RespBean("error", "没有开立!");
    }
	
	/**
	 * 得到检查申请（不包括作废状态）
	 */
	@RequestMapping(value = "/listCheck", method = RequestMethod.GET)
    public RespBean listCheck( String RegistID, String State, String RecordType ) {
		RespBean resp = new RespBean();
		resp.setList(checkApplyService.getCheckApplyList(RegistID, State, RecordType));
        return resp;
    }
		
}
