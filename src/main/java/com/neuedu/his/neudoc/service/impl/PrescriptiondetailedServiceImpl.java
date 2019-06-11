package com.neuedu.his.neudoc.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.neuedu.his.neudoc.service.PrescriptiondetailedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neuedu.his.mapper.PrescriptiondetailedMapper;
import com.neuedu.his.mapper.RegisterMapper;

/**
 * @auther: 东软教师
 * @date: 2018-11-29
 */
@Service
@Transactional
public class PrescriptiondetailedServiceImpl implements PrescriptiondetailedService {
	@Autowired
	private PrescriptiondetailedMapper prescriptiondetailedMapper;
	@Autowired
	private RegisterMapper registerMapper;

	@Override
	public List<Map<String, Object>> getDrugsByPrescription(String PrescriptionID){
		
		return prescriptiondetailedMapper.getDrugsByPrescription(PrescriptionID);
	}

	@Override
	public int addPrescriptionDetailed(Map<String, Object> map) {
		map.remove("ID");
		Object num = map.get("Amount");
		if(num!=null && num.toString().length()>0){
			try{
				new Integer(num.toString());
			}catch(Exception e){
				map.put("Amount", "1");
			}
		}else {
			map.put("Amount", "1");
		}
		
		return prescriptiondetailedMapper.insertSelective(map);
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
	//根据ID，批量删除
	@Override
	@Transactional
	public boolean delPrescriptionDetailed(String ids) {
        String[] split = ids.split(",");
        int result = prescriptiondetailedMapper.deleteByPrimaryKey(split);
        return result == split.length;
	}

	@Override
	public List<Map<String, Object>> getPrescriptionByRegisterAndDate(String caseNumber, String searchDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = new Date();
		try {
			date = sdf.parse(searchDate);
		} catch (ParseException e) {
			date = new Date();
		}
		String registID = registerMapper.getRegByCase(caseNumber).getId();
		List<Map<String, Object>> list = prescriptiondetailedMapper.getPrescriptionByRegisterAndDate(registID, date);
		return list;
	}

	@Override
	public boolean toSendDrugs(String ids) {
		String[] split = ids.split(",");
		Map<String,Object> map = new HashMap<String,Object>();
		int result = 0;
		for(int i=0;i<split.length;i++){
			map.clear();
			map.put("ID", split[i]);
			map.put("State", "4");
			result += prescriptiondetailedMapper.updateByPrimaryKeySelective(map);
		}
        return result == split.length;
	}
	@Override
	public boolean toBackDrugs(String ids) {
		String[] split = ids.split(",");
		Map<String,Object> map = new HashMap<String,Object>();
		int result = 0;
		for(int i=0;i<split.length;i++){
			map.clear();
			map.put("ID", split[i]);
			map.put("State", "5");
			result += prescriptiondetailedMapper.updateByPrimaryKeySelective(map);
		}
        return result == split.length;
	}

//	@Override
//	public int upToSaved(String ids,String state) {
//		int num = 0;
//		String[] split = ids.split(",");
//		Map<String,Object> map = new HashMap<>();
//		for(int i=0;i<split.length;i++) {
//			map.put("ID", split[i]);
//			map.put("State", state);
//			num += checkapplyMapper.updateByPrimaryKeySelective(map);
//		}
//		return num;
//	}
}
