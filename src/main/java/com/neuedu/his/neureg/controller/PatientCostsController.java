package com.neuedu.his.neureg.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neuedu.his.bean.Register;
import com.neuedu.his.bean.RespBean;
import com.neuedu.his.neureg.service.PatientCostsService;


/** 
* 患者消费信息专属Controller 
* @auther: 东软教师
* @date:   2019-3-25
*/ 
@RestController
@RequestMapping("/neureg/patientCosts")
public class PatientCostsController {
    @Autowired
    PatientCostsService patientCostsService;
     
    /**
     * 按条件分页查询患者消费信息的方法   交费时用的
     * @param: page当前页   count每页显示记录数量   keywords keywords2 keywords3查询关键字
     * @return: 包含查询结果的Map集合
     */ 
    @RequestMapping(value = "/patient_charge", method = RequestMethod.GET)
    public Map<String, Object> getPatientCostsList( @RequestParam(value = "page", defaultValue = "1") Integer page, 
    		@RequestParam(value = "count", defaultValue = "10") Integer count,String keywords) {
        int totalCount = patientCostsService.getPatientChargeCount(keywords);
        List<Map<String,Object>> list = patientCostsService.getPatientChargeList( page, count,keywords);
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);
        map.put("list", list);
        
        return map;
    }
    
    @RequestMapping(value = "/patient_getTab", method = RequestMethod.GET)
    public Map<String, Object> getTab(String keywords, int state) {
        List<Map<String,Object>> list = patientCostsService.getTab( keywords, state);
        Map<String, Object> map = new HashMap<>();
        map.put("list", list);
        return map;
    }
  
    /**
     * 按条件分页查询患者消费信息的方法   退费时用的
     * @param: page当前页   count每页显示记录数量   keywords keywords2 keywords3查询关键字
     * @return: 包含查询结果的Map集合
     */ 
    @RequestMapping(value = "/patient_refund", method = RequestMethod.GET)
    public Map<String, Object> getPatientCostsList02( @RequestParam(value = "page", defaultValue = "1") Integer page, 
    		@RequestParam(value = "count", defaultValue = "10") Integer count,String keywords) {
        int totalCount = patientCostsService.getPatientRefundCount(keywords);
        List<Map<String,Object>> list = patientCostsService.getPatientRefundList( page, count,keywords);
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);//totalCount  60
        map.put("list", list);
        
        return map;
    }
    
    /** 
     * 按条件查询患者挂号信息的方法   确认身份查询用的
     * @param: page当前页   count每页显示记录数量   keywords keywords2 查询关键字
     * @return: 包含查询结果的Map集合
     */ 
    @RequestMapping(value = "/get_patient", method = RequestMethod.GET)
    public Register getPatient( String keywords ) {
        Register register = patientCostsService.getPatient( keywords);
        return register;
    }
    
    /** 
     * 按条件分页查询患者消费信息的方法   退费时用的
     * @param: page当前页   count每页显示记录数量   keywords keywords2 keywords3查询关键字
     * @return: 包含查询结果的Map集合
     */ 
    @RequestMapping(value = "/patient_query", method = RequestMethod.GET)
    public Map<String, Object> getPatientCostsList03( @RequestParam(value = "page", defaultValue = "1") Integer page, 
    		@RequestParam(value = "count", defaultValue = "10") Integer count,String keywords,String keywords2,String keywords3) {
        int totalCount = patientCostsService.getPatientQueryCount(keywords,keywords2,keywords3);
        List<Map<String,Object>> list = patientCostsService.getPatientQuery( page, count,keywords,keywords2,keywords3);
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);//totalCount  6
        map.put("list", list);
        
        return map;
    }
    
    /**
     * 患者消费金额统计  患者查询时用的
     * @param: keywords keywords2 keywords3 查询关键字
     * @return: 包含查询结果的Map集合
     */ 
    @RequestMapping(value = "/patient_sum", method = RequestMethod.GET)
    public Map<String, Object> getPatientSumFee(String keywords,String keywords2,String keywords3) {
        float totalCount = patientCostsService.getPatientSumFee(keywords,keywords2,keywords3);
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);
        return map;
    }
    
    /** 
     * 添加患者消费信息的方法 
     * @param: patientCosts 包含科室信息的Map集合
     * @return: 包含响应信息的对象
     */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public RespBean addNewPatientCosts(@RequestParam Map<String,Object> patientCosts) {
        int result = patientCostsService.addPatientCosts(patientCosts);
        if (result == 1) {
            return new RespBean("success", "添加成功!(患者费用明细)");
        }
        return new RespBean("error", "添加失败!(患者费用明细)");
    }
}