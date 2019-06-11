package com.neuedu.his.neudoc.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neuedu.his.bean.RespBean;
import com.neuedu.his.neudoc.service.MedicalRecordService;

/** 
* 医生病历信息操作 
* @auther: 东软教师
* @date:   2018-11-29
*/
@RestController
@RequestMapping("/neudoc/medical")
public class MedicalRecordController {
    @Autowired
    private MedicalRecordService docMedicalRecordService;
	/**
	 * 医生根据病历号CaseNumber，得到患者的病历信息
	 */
	@RequestMapping(value = "/getDataByCaseNumber", method = RequestMethod.GET)
    public Map<String, Object> getMedicalRecord( String keywords01,String keywords02 ) {
        Map<String, Object> map = docMedicalRecordService.getMedicalRecord(keywords01,keywords02);        
        return map;
    }
   
	/**
	 * 病历状态：1-暂存 2-已提交 3-诊毕
	 * 医生根据患者id，暂存 患者病历信息  -- 如果不存在：新建；如果存在：修改
	 * 医生根据患者id，保存 患者病历信息  -- 如果不存在：新建；如果存在：修改
	 * 医生根据病历id和患者id，保存 患者确诊病历信息 -- 如果不存在：报错；如果存在：修改
	 * 医生根据病历id和患者id，保存 患者病历信息状态为诊毕 -- 如果不存在：报错；如果存在：修改
	 */
	@RequestMapping(value = "/saveCheck", method = RequestMethod.POST)
    public RespBean saveCheck( @RequestParam Map<String,Object> map )  {
		
		int result = docMedicalRecordService.saveMedicalRecord(map);
        if (result > 0) {
            return new RespBean("success", "成功!(病历信息)");
        }
        return new RespBean("error", "添加失败!");
    }
	/**
	 * 病历状态：1-暂存 2-已提交 3-诊毕
	 * 医生根据患者id，诊毕 患者病历信息  
	 */
	@RequestMapping(value = "/endCheck", method = RequestMethod.GET)
    public RespBean endCheck( String registID, String visitState, String caseState  )  {	
		int result = docMedicalRecordService.endRegist(registID, visitState, caseState);
//        System.out.println(result);
		if (result > 0) {
            return new RespBean("success", "成功!(诊毕)");
        }
        return new RespBean("error", "添加失败!");
    }	
	
}
