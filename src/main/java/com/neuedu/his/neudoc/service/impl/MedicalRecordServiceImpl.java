package com.neuedu.his.neudoc.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.neuedu.his.neudoc.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neuedu.his.mapper.MedicaldiseaseMapper;
import com.neuedu.his.mapper.MedicalrecordMapper;
import com.neuedu.his.mapper.RegisterMapper;
import com.neuedu.his.util.ParamUtil;

@Service
@Transactional
public class MedicalRecordServiceImpl implements MedicalRecordService {
	@Autowired
	private MedicalrecordMapper medicalrecordMapper;
	@Autowired
	private MedicaldiseaseMapper medicaldiseaseMapper;
	@Autowired
	private RegisterMapper registerMapper;
	
	//挂号ID RegistID，加载一条病历信息
	@Override
	public Map<String, Object> getMedicalRecord(String keywords01,String keywords02) {
		List<Map<String, Object>> list = medicalrecordMapper.getMedicalRecord(keywords01);
		if(list!=null&&list.size()==1) {
			List<Map<String, Object>> mdList = medicaldiseaseMapper.getMedicalDisease(keywords01,keywords02);
			Map<String,Object> map = list.get(0);
			map.put("MedicalDisease", mdList);
			return list.get(0);
		}
		return new HashMap<String,Object>();
	}

	@Override
	public List<Map<String, Object>> getMedicalRecord(Map<String, Object> map) {

		return null;
	}
//	病历状态： 3-诊毕
	@Override
	public int endRegist(String registID,String visitState,String caseState) {
		int num = 0;
		Map<String,Object> regist = new HashMap<String,Object>();
		regist.put("ID", registID);
		regist.put("VisitState", "3");
		num = registerMapper.updateByPrimaryKeySelective(regist);
		regist.clear();
		List<Map<String, Object>> list = medicalrecordMapper.getMedicalRecord(registID);
		if(list!=null && list.size()>0) {
			regist.put("ID", list.get(0).get("ID"));
			regist.put("CaseState", caseState);
			medicalrecordMapper.updateByPrimaryKeySelective(regist);
		}
		return num;
	}
	
//	病历状态：1-暂存 2-已提交 3-诊毕
	@Override
	public int saveMedicalRecord(Map<String, Object> map) {
		for(Entry<String, Object> set:map.entrySet()) {
			if("undefined".equals(set.getValue())){
				map.put(set.getKey(), "");
			}
		}
		int num = 0;
		if(map!=null&&map.size()>0) {
			int id = this.saveOrUpdate(map);
			num = 1;
			List<Map<String,Object>> list = this.formatMedicalDisease(map.get("MedicalDisease").toString());
			if(list!=null){				
				medicaldiseaseMapper.deleteByRegistID(map.get("RegistID").toString(), "1");
				num = this.batchAdd(list,id);
			}
		}
		//如果是“提交”，改变患者状态为"已经诊断"
		if("2".equals(map.get("CaseState").toString())){
			Map<String,Object> regist = new HashMap<String,Object>();
			regist.put("ID", map.get("RegistID"));
			regist.put("VisitState", "2");
			num = registerMapper.updateByPrimaryKeySelective(regist);
		}
		return num;
	}
	//根据条件保存或增加数据
	private int saveOrUpdate(Map<String, Object> map) {
		int id = 1;
		String RegistID = map.get("RegistID").toString();
		List<Map<String, Object>> list = medicalrecordMapper.getMedicalRecord(RegistID);
		if(list!=null && list.size()>0) {
			map.put("ID", list.get(0).get("ID"));
			medicalrecordMapper.updateByPrimaryKeySelective(map);
			id = Integer.parseInt(list.get(0).get("ID").toString());
		}else {	
			id = medicalrecordMapper.insertSelective(map);
		}
		
		return id;
	}
	//将json字符串，转换为对象
	private List<Map<String,Object>> formatMedicalDisease(String str) {
		ObjectMapper objectMapper = new ObjectMapper();
		List<Map<String, Object>> list = null;
		try {
			list = objectMapper.readValue(str, List.class);
			for(Map<String,Object> map:list) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				try {
//					System.out.println(map.get("GetSiskDate"));
					map.put("GetSiskDate",sdf.parse(map.get("GetSiskDate").toString()));
				} catch (ParseException e) {
					map.put("GetSiskDate",new java.util.Date());
//					e.printStackTrace();
				}
			}
		} catch (Exception e) {
//			e.printStackTrace();
		}		
		return list;
	}
	//插入数据
	private int batchAdd(List<Map<String,Object>> list,int medicalID) {
		//增加数据条数
		int addNumber = 0;
		
        for(int i=0;i<list.size();i++){
        	Map<String,Object> map = list.get(i);
        	map.remove("ID");
        	String registID = map.get("RegistID").toString();
        	String diseaseID = map.get("DiseaseID").toString();
        	String diagnoseType = map.get("DiagnoseType").toString();
        	map.put("MedicalID", medicalID);
        	map.put("RegistID", ParamUtil.stringToInt(registID, 0));
        	map.put("DiseaseID", ParamUtil.stringToInt(diseaseID, 0));
        	map.put("DiagnoseType", ParamUtil.stringToInt(diagnoseType, 0));
        	map.put("DiagnoseCate", 1);
            addNumber += medicaldiseaseMapper.insertSelective(map);
        }
		
        return addNumber;
	}
}
