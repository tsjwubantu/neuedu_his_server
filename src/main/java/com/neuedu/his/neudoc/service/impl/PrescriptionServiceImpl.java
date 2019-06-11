package com.neuedu.his.neudoc.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.neuedu.his.neudoc.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neuedu.his.mapper.DrugsdetailedMapper;
import com.neuedu.his.mapper.DrugstemplateMapper;
import com.neuedu.his.mapper.PrescriptionMapper;
import com.neuedu.his.mapper.PrescriptiondetailedMapper;

/**
 * @auther: 东软教师
 * @date: 2018-11-29
 */
@Service
@Transactional
public class PrescriptionServiceImpl implements PrescriptionService {
	@Autowired
	private PrescriptionMapper prescriptionMapper;
	@Autowired
    private PrescriptiondetailedMapper prescriptiondetailedMapper;
	@Autowired
    private DrugstemplateMapper drugstemplateMapper;
	@Autowired
    private DrugsdetailedMapper drugsdetailedMapper;

	@Override
	public List<Map<String, Object>> getPresByRegist(String registID, String prescriptionState) {
		if(prescriptionState==null || "".equals(prescriptionState)) {
			prescriptionState = "!=0";
		}else {
			prescriptionState = "="+prescriptionState;
		}
		return prescriptionMapper.getPresByRegist(registID, prescriptionState);
	}
	
	@Override
	public int addPrescription(Map<String, Object> map) {
		map.remove("ID");
		Object num = map.get("UserID");
		if(num!=null && num.toString().length()>0){
			try{
				new Integer(num.toString());
			}catch(Exception e){
				map.put("UserID", "1");
			}
		}else {
			map.put("UserID", "1");
		}
		map.put("PrescriptionTime", new java.util.Date() );
		map.put("PrescriptionState", "1" );
		return prescriptionMapper.insertSelective(map);
	}
	//TODO
	//根据模板ID 得到模板详情 添加到成药处方 和处方明细 中
	@Override
	public int addPrescriptionByTempl(Map<String, Object> map) {
		int sum = 0;
		String templID = map.get("TemplID").toString();
		List<Map<String, Object>> listTempl = drugstemplateMapper.selectByPrimaryKey(templID);
		Map<String, Object> map01 = listTempl.get(0);
		String keywords01 = "模板："+map01.get("Name").toString();
		map.remove("ID");
		Object num = map.get("UserID");
		if(num!=null && num.toString().length()>0){
			try{
				new Integer(num.toString());
			}catch(Exception e){
				map.put("UserID", "1");
			}
		}else {
			map.put("UserID", "1");
		}
		map.put("PrescriptionName", keywords01);
		map.put("PrescriptionTime", new java.util.Date() );
		map.put("PrescriptionState", "1" );
		prescriptionMapper.insertSelective(map);
		String prescriptionID = map.get("ID").toString();
		List<Map<String, Object>> listTemplDrugs = drugsdetailedMapper.getTemplateDrugsList(templID);
		for(int i=0;i<listTemplDrugs.size();i++) {
			Map<String, Object> map02 = listTemplDrugs.get(i);
			Map<String, Object> presDrugs = new HashMap<String,Object>();
			presDrugs.put("PrescriptionID", prescriptionID);
			presDrugs.put("DrugsID", map02.get("DrugsId"));
			presDrugs.put("DrugsUsage", map02.get("DrugsUsage"));
			presDrugs.put("Dosage", map02.get("DrugsDosage"));
			presDrugs.put("Frequency", map02.get("DrugsFrequency"));
			presDrugs.put("Amount", "1");
			sum = prescriptiondetailedMapper.insertSelective(presDrugs);
		}
		return sum;
	}

	//根据ID，批量删除申请
	@Override
	@Transactional
	public boolean delPrescription(String ids) {
        String[] split = ids.split(",");
        int result = prescriptionMapper.deleteByPrimaryKey(split);
        prescriptiondetailedMapper.delDrugsByPrescription(split);
        return result == split.length;
	}

	@Override
	public int upToSaved(String ids,String state) {
		int num = 0;
		String[] split = ids.split(",");
		Map<String,Object> map = new HashMap<>();
		for(int i=0;i<split.length;i++) {
			map.clear();
			map.put("ID", split[i]);
			map.put("PrescriptionState", state);
			num += prescriptionMapper.updateByPrimaryKeySelective(map);
		}
		return num;
	}
	

//	// 根据范围 类型  医生 查询模板
//	@Override
//	public List<Map<String, Object>> getTemplateByDoc(String userID, String recordType, String name) {
//		if( "undefined".equals(userID) ) {
//			userID = "";
//		}
//		if( "undefined".equals(recordType) ) {
//			recordType = "";
//		}
//		if( "undefined".equals(name) ) {
//			name = "";
//		}
//		return checktemplateMapper.getTemplateByDoc(userID, recordType, name);
//	}
//	
}
