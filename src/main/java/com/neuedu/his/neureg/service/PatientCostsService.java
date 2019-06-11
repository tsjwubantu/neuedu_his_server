package com.neuedu.his.neureg.service;

import java.util.List;
import java.util.Map;
import com.neuedu.his.bean.Register;

/** 
* 患者消费信息专属Service接口
* @auther: 东软教师
* @date:   2019-3-25
*/ 
public interface PatientCostsService {

	/** 
     * 获取符合条件的记录数量
     * @param keywords 查询条件 
     * @return 记录数量 
    */ 
	int getPatientChargeCount(String keywords);

	/** 
	 * 患者消费信息查询   交费时使用
	 * @param 当前页 每页显示记录数   查询条件
	 * @return 对象列表 
	*/ 
	List<Map<String, Object>> getPatientChargeList(Integer page, Integer count, String keywords);
	
	List<Map<String, Object>> getTab(String keywords, int state);

	/** 
     * 获取符合退费条件的记录数量
     * @param keywords 查询条件 
     * @return 记录数量 
    */ 
	int getPatientRefundCount(String keywords);

	/** 
     * 获取符合退费条件的记录列表
     * @param 当前页 每页显示记录数 keywords 查询条件 
     * @return 记录列表
    */ 
	List<Map<String, Object>> getPatientRefundList(Integer page, Integer count, String keywords);

	/** 
     * 获取符合条件的包含患者信息的Register对象   患者退费时 确认身份查询用的
     * @param keywords 查询条件 
     * @return Register对象
    */ 
	Register getPatient(String keywords);

	/** 
     * 获取符合查询条件的患者消费记录的数量   患者费用查询用的
     * @param  keywords keywords2 keywords3 查询条件 
     * @return 记录数量 整型
    */ 
	int getPatientQueryCount(String keywords, String keywords2, String keywords3);

	/** 
     * 获取患者消费记录列表
     * @param 当前页 每页显示记录数 keywords keywords2 keywords3 查询条件 
     * @return 记录列表
    */ 
	List<Map<String, Object>> getPatientQuery(Integer page, Integer count, String keywords,
                                              String keywords2, String keywords3);
	/** 
     * 获取患者消费总金额
     * @param keywords keywords2 keywords3查询条件 
     * @return float数值
    */ 
	float getPatientSumFee(String keywords, String keywords2, String keywords3);

	/** 
     * 添加患者消费信息
     * @param 患者消费信息对象 
     * @return 1添加成功  0添加失败 
     */ 
	int addPatientCosts(Map<String, Object> patientCosts);
}
