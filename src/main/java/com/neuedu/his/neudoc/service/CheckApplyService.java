package com.neuedu.his.neudoc.service;

import java.util.List;
import java.util.Map;

public interface CheckApplyService {

	int addCheckApply(Map<String, Object> map);
	/**
	 * 根据 患者挂号ID 申请状态 申请类型  进行查询
	 */
	List<Map<String, Object>> getCheckApplyList(String RegistID, String State, String RecordType);

	

	boolean delCheckApply(String ids);

	int upToSaved(String ids, String state);

}