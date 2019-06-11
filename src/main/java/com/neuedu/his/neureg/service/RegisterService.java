package com.neuedu.his.neureg.service;

import java.util.List;
import java.util.Map;
import com.neuedu.his.bean.Register;

/** 
* 挂号信息专属Service接口
* @auther: 东软教师
* @date:   2019-3-25
*/ 
public interface RegisterService {

	/**
	 * 根据医生id和状态，取得患者数据，不分页
	 * @param: 医生ID 患者挂号信息的状态
	 * @return: 符合条件的患者挂号信息对象集合
	 */
	List<Map<String, Object>> getStateRegistersByDoc(String docId, String state);

	/** 
     * 查找病历编号最大的挂号信息对象
     * @param: 无
     * @return: 病历编号最大的挂号信息对象
     */ 
	Register getRegisterBean();
    
	/** 
     * 通过病历编号查找指定的挂号信息对象
     * @param: CaseNumber  病历编号
     * @return: 指定的挂号信息对象
     */ 
	Register getRegByCase(String caseNumber);

	/** 
     * 添加挂号信息的方法 
     * @param: register 挂号信息对象
     * @return: 添加成功返回 1   添加失败返回 0
     */ 
	int addRegister(Map<String, Object> register);

	/** 
     * 按条件分页查询挂号记录的方法 
     * @param: page当前页   count每页显示记录数量   keywords keywords2查询关键字
     * @return: 包含查询结果的Map集合
     */ 
	List<Map<String, Object>> getRegisterList(Integer page, Integer count, String keywords, String keywords2);

	/** 
     * 按条件统计记录数量  确定页数最大值时用的
     * @param keywords keywords2查询关键字
     * @return 记录数量  整型
     */ 
	int getRegisterCount(String keywords, String keywords2);
	
	
	/** 
     * 按条件统计记录数量  统计已经挂号人数用的
     * @param keywords keywords2 keywords3查询关键字
     * @return 记录数量  整型
     */ 
	int getAlreadyRegisterCount(String keywords, String keywords2, String keywords3, int n);

	/** 
     * 更新挂号信息的方法  包含退号业务逻辑   患者退号时调用
     * @param: register 包含更新信息的Map集合
     * @return: 更新成功返回 1   更新失败返回 0
     */ 
	int updateRegisterStateById(Map<String, Object> register);
}