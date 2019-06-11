package com.neuedu.his.neudoc.service;

import java.util.List;
import java.util.Map;

public interface PrescriptiondetailedService {
	/**
	 * 根据 处方ID 查询处方中药品详情
	 */
	List<Map<String, Object>> getDrugsByPrescription(String PrescriptionID);

	int addPrescriptionDetailed(Map<String, Object> map);

	boolean delPrescriptionDetailed(String ids);

	List<Map<String, Object>> getPrescriptionByRegisterAndDate(String caseNumber, String searchDate);

	boolean toSendDrugs(String ids);

	boolean toBackDrugs(String ids);
	

}
