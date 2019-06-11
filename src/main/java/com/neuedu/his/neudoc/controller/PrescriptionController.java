package com.neuedu.his.neudoc.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neuedu.his.bean.RespBean;
import com.neuedu.his.neudoc.service.PrescriptionService;

/** 
* 患者成药处方申请操作 
* @auther: 东软教师
* @date:   2018-11-29
*/
@RestController
@RequestMapping("/neudoc/prescription")
public class PrescriptionController {
    @Autowired
    private PrescriptionService prescriptionService;
    
    /**
     * 根据ID，批量删除检查申请
	 */
    @RequestMapping(value = "/{ids}", method = RequestMethod.DELETE)
    public RespBean delPrescription(@PathVariable("ids") String ids) {
        boolean result = prescriptionService.delPrescription(ids);
        if (result) {
            return new RespBean("success", "删除成功!(成药处方信息)");
        }
        return new RespBean("error", "删除失败!");
    }
   
	/**
	 * 添加处方
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST)
    public RespBean savePres( @RequestParam Map<String,Object> map ) {
		
		int result = prescriptionService.addPrescription(map);
        if (result > 0) {
            return new RespBean("success", "成功!(成药处方)");
        }
        return new RespBean("error", "添加失败!");
    }
	
	/**
	 * 根据模板添加处方
	 */
	@RequestMapping(value = "/savePredByTempl", method = RequestMethod.POST)
    public RespBean savePredByTempl( @RequestParam Map<String,Object> map ) {
		
		int result = prescriptionService.addPrescriptionByTempl(map);
        if (result > 0) {
            return new RespBean("success", "成功!(使用模板)");
        }
        return new RespBean("error", "添加失败!");
    }
	
	/**
	 * 得到处方（不包括作废状态）
	 */
	@RequestMapping(value = "/listPres", method = RequestMethod.GET)
    public RespBean listPrescription( String RegistID, String PrescriptionState ) {
		RespBean resp = new RespBean();
		resp.setList(prescriptionService.getPresByRegist(RegistID, PrescriptionState));
        return resp;
    }
	
	/**
	 * 修改处方状态
	 */
	@RequestMapping(value = "/upToSaved", method = RequestMethod.GET)
    public RespBean upToSaved( String ids,String state ) {
		
		int result = prescriptionService.upToSaved(ids,state);
        if (result > 0) {
            return new RespBean("success", "成功!(处方模板)");
        }
        return new RespBean("error", "没有成功!");
    }	
		
}
