package com.neuedu.his.neudoc.service;

import java.util.List;
import java.util.Map;

public interface CheckTemplateService {
	/**
	 * @param keywords01 名称
	 * @param keywords02 范围
	 * @param keywords03 类型 ：1-检查 2-检验 3-处置
	 * @return
	 */
	List<Map<String, Object>> getCheckTemplateList(String keywords01, String keywords02, String keywords03);
	
	/**
	 * 根据范围 类型  医生 查询模板
	 */
	List<Map<String, Object>> getTemplateByDoc(String userID, String recordType, String name);
	/**
	 * 根据ID，批量删除数据
	 * @param ids
	 * @return 传入的条数和数据库删除的条数 是否相同
	 */
	boolean deleteCheckTemplateByIds(String ids);

	int updateCheckTemplateById(Map<String, Object> map);

	int addCheckTemplate(Map<String, Object> map);

}