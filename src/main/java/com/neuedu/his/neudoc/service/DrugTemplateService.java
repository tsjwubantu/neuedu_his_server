package com.neuedu.his.neudoc.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface DrugTemplateService {

	List<Map<String, Object>> getDrugTemplateList(String keywords01, String keywords02, String userID);

	boolean deleteDrugTemplateByIds(String ids);

	int updateDrugTemplateById(Map<String, Object> map);

	int addDrugTemplate(Map<String, Object> map);
	/**
	 * 医生根据ID得到可以查看的模板记录（包括同科室医生开立的 范围是科室的模板）
	 */
	List<Map<String, Object>> getDrugTemplateByDocid(String id, String name);

}