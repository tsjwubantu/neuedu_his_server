package com.neuedu.his.neudoc.service;

import java.util.List;
import java.util.Map;

public interface MedicalRecordService {

	Map<String, Object> getMedicalRecord(String keywords01, String keywords02);

	List<Map<String, Object>> getMedicalRecord(Map<String, Object> map);

	/**
	 * @param map 
	 * 		病历信息：
	 *@param state 
	 *		病历状态：
	 *	151、暂存：病历首页内容可以修改；诊断中西医病症可以修改
	 *	152、提交状态：病历首页内容只能查看，不能修改，暂存按钮无效；诊断中西医病症可以修改
	 *		原状态为 151：保存 患者病历信息  -- 如果不存在：新建；如果存在：修改
	 *		原状态为 152：保存 患者确诊病历信息 -- 如果不存在：报错；如果存在：修改
	 *	153、确诊状态：诊断中西医病症不可以修改
	 */
	int saveMedicalRecord(Map<String, Object> map);

	int endRegist(String registID, String visitState, String caseState);

}