package com.neuedu.his.neureg.service.impl;

import java.util.List;
import java.util.Map;

import com.neuedu.his.neureg.service.PatientCostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neuedu.his.bean.Register;
import com.neuedu.his.mapper.PatientCostsMapper;

/** 
* 患者消费信息专属Service 
* @auther: 东软教师
* @date:   2019-3-25
*/ 
@Service
@Transactional
public class PatientCostsServiceImpl implements PatientCostsService {
    @Autowired
    PatientCostsMapper patientCostsMapper;
    
    /** 
     * 获取符合条件的记录数量
     * @param keywords 查询条件 
     * @return 记录数量 
     */ 
     public int getPatientChargeCount( String keywords) {
          int n1=patientCostsMapper.getPatientCostsCount(keywords,2);
          int n2=patientCostsMapper.getPatientCostsCount02(keywords,2);
          
          return (n1 + n2);
     }
    
    /** 
    * 患者费用查询  交费时用的
    * @param 当前页 每页显示记录数   查询条件
    * @return 对象列表 
    */ 
    public List<Map<String,Object>> getPatientChargeList( Integer page, Integer count,String keywords) {
         int start = (page - 1) * count;
         return patientCostsMapper.getPatientCostsList(start, count, keywords,2);//2-已开立
    }
    
    @Override
	public List<Map<String, Object>> getTab(String keywords, int state) {
	       return patientCostsMapper.getTab(keywords, state);
	}
    
    /** 
     * 获取符合退费条件的记录数量
     * @param keywords 查询条件 
     * @return 记录数量 
    */            
    public int getPatientRefundCount( String keywords) {
        //return patientCostsMapper.getPatientCostsCount(keywords,3);
        
        int n1=patientCostsMapper.getPatientCostsCount(keywords,3);//3-已交费
        int n2=patientCostsMapper.getPatientCostsCount02(keywords,3);//3-已交费
        
        return (n1 + n2);
    }
    
    
    /** 
     * 获取符合退费条件的记录列表
     * @param 当前页 每页显示记录数 keywords 查询条件 
     * @return 记录列表
    */                               
    public List<Map<String,Object>> getPatientRefundList( Integer page, Integer count,String keywords) {
        int start = (page - 1) * count;
        return patientCostsMapper.getPatientCostsList(start, count, keywords, 3);//3-已交费
    }
    
    
    /** 
     * 获取符合条件的包含患者信息的Register对象   患者退费时 确认身份查询用的
     * @param keywords 查询条件 
     * @return Register对象
    */ 
    public Register getPatient( String keywords) {
        return patientCostsMapper.getPatient(keywords);
    }
    
   
    /** 
     * 获取符合查询条件的患者消费记录的数量   患者费用查询用的
     * @param  keywords keywords2 keywords3 查询条件 
     * @return 记录数量 整型
    */ 
    public int getPatientQueryCount( String keywords,String keywords2,String keywords3) {
        return patientCostsMapper.getPatientQueryCount(keywords, keywords2, keywords3);
    }
    
    
    /** 
     * 获取患者消费记录列表    患者费用查询用的
     * @param 当前页 每页显示记录数 keywords keywords2 keywords3 查询条件 
     * @return 记录列表
    */ 
    public List<Map<String,Object>> getPatientQuery( Integer page, Integer count,String keywords,String keywords2,String keywords3) {
        int start = (page - 1) * count;
        return patientCostsMapper.getPatientQuery(start, count, keywords, keywords2, keywords3);
    }

    /** 
     * 获取患者消费总金额
     * @param keywords keywords2 keywords3查询条件 
     * @return float数值
    */ 
    public float getPatientSumFee( String keywords,String keywords2,String keywords3) {
         return patientCostsMapper.getPatientSumFee(keywords, keywords2, keywords3);
     }
     
     /** 
      * 添加患者消费信息
      * @param 患者消费信息对象 
      * @return 1添加成功  0添加失败 
      */ 
      public int addPatientCosts(Map<String,Object> patientCosts) {
         return patientCostsMapper.addPatientCosts(patientCosts);
      }

	
}