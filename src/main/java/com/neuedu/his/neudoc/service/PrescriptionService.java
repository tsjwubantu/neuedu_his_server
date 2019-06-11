package com.neuedu.his.neudoc.service;

import java.util.List;
import java.util.Map;

public interface PrescriptionService {
	/**
	 * 根据 患者挂号ID 处方状态  进行查询
	 */
	List<Map<String, Object>> getPresByRegist(String registID, String prescriptionState);
	/**
	 * 添加处方
	 */
	int addPrescription(Map<String, Object> map);
	/**
	 * 删除处方 和 处方中所有药品信息
	 */
	boolean delPrescription(String ids);
	
	/**
	 * 根据模板添加处方
	 */
	int addPrescriptionByTempl(Map<String, Object> templID);
	
	/**
	 * 修改处方状态
	 */
	int upToSaved(String ids, String state);

	
}
