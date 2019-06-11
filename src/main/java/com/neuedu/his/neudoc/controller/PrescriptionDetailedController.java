package com.neuedu.his.neudoc.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neuedu.his.bean.RespBean;
import com.neuedu.his.neudoc.service.PrescriptiondetailedService;

/** 
* 患者成药处方申请操作 
* @auther: 东软教师
* @date:   2018-11-29
*/
@RestController
@RequestMapping("/neudoc/prescriptionDetailed")
public class PrescriptionDetailedController {
    @Autowired
    private PrescriptiondetailedService prescriptiondetailedService;
    
    /**
     * 根据ID，批量删除处方中的药品
	 */
    @RequestMapping(value = "/{ids}", method = RequestMethod.DELETE)
    public RespBean delPrescription(@PathVariable("ids") String ids) {
        boolean result = prescriptiondetailedService.delPrescriptionDetailed(ids);
        if (result) {
            return new RespBean("success", "删除成功!(药品信息)");
        }
        return new RespBean("error", "删除失败!");
    }
   
	/**
	 * 添加处方中药品信息
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST)
    public RespBean saveCheck( @RequestParam Map<String,Object> map ) {
		
		int result = prescriptiondetailedService.addPrescriptionDetailed(map);
        if (result > 0) {
            return new RespBean("success", "成功添加!(处方中药品)");
        }
        return new RespBean("error", "添加失败!");
    }
	
	/**
	 * 得到处方明细
	 */
	@RequestMapping(value = "/listDetail", method = RequestMethod.GET)
    public RespBean listPrescriptionDetail( String PrescriptionID ) {
		RespBean resp = new RespBean();
		resp.setList(prescriptiondetailedService.getDrugsByPrescription(PrescriptionID));
        return resp;
    }
	/**
	 * 发药查询
	 */
	@RequestMapping(value = "/getPrescriptionByRegisterAndDate", method = RequestMethod.GET)
    public RespBean getPrescriptionByRegisterAndDate( String caseNumber,String searchDate ) {
		RespBean resp = new RespBean();
		resp.setList(prescriptiondetailedService.getPrescriptionByRegisterAndDate(caseNumber,searchDate));
        return resp;
    }
	
	/**
     * 发药
	 */
    @RequestMapping(value = "/drugs/{ids}", method = RequestMethod.DELETE)
    public RespBean sendDrugs(@PathVariable("ids") String ids) {
        boolean result = prescriptiondetailedService.toSendDrugs(ids);
        if (result) {
            return new RespBean("success", "发药成功!(药品信息)");
        }
        return new RespBean("error", "失败!");
    }
    
    /**
     * 退药
	 */
    @RequestMapping(value = "/backDrugs/{ids}", method = RequestMethod.DELETE)
    public RespBean backDrugs(@PathVariable("ids") String ids) {
        boolean result = prescriptiondetailedService.toBackDrugs(ids);
        if (result) {
            return new RespBean("success", "发药成功!(药品信息)");
        }
        return new RespBean("error", "失败!");
    }

}
