package com.neuedu.his.neudoc.service.impl;

import java.util.List;
import java.util.Map;

import com.neuedu.his.neudoc.service.DrugTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neuedu.his.mapper.DrugsdetailedMapper;
import com.neuedu.his.mapper.DrugstemplateMapper;

/** 
* @auther: 东软教师
* @date:   2018-11-29
*/ 
@Service
@Transactional
public class DrugTemplateServiceImpl implements DrugTemplateService {
    @Autowired
    DrugstemplateMapper drugTemplateMapper;
    @Autowired
    DrugsdetailedMapper templateDrugsMapper;


    @Override
	public List<Map<String,Object>> getDrugTemplateList(String keywords01,String keywords02,String userID) {
         return drugTemplateMapper.getDrugTemplateList(keywords01,keywords02, userID);
	}

	@Override
	public boolean deleteDrugTemplateByIds(String ids) {
		String[] split = ids.split(",");
		int result = drugTemplateMapper.deleteDrugTemplateByIds(split);
		templateDrugsMapper.deleteTemplateDrugsByTemplate(split);
		return result == split.length;
	}

    @Override
	public int updateDrugTemplateById(Map<String,Object> map) {
          return drugTemplateMapper.updateDrugTemplateById(map);
     }

    @Override
	public int addDrugTemplate(Map<String,Object> map) {
    	if(map.get("UserID")==null||map.get("UserID")==""){
    			map.put("UserID", "1");
    	}else {
    		try {
    			Integer.parseInt(map.get("UserID").toString());
    		}catch(Exception e) {
    			map.put("UserID", "1");
    		}
    	}    	
    	return drugTemplateMapper.addDrugTemplate(map);
    }
    
    @Override
    public List<Map<String, Object>> getDrugTemplateByDocid(String id,String name){
    	if(id!=null && id.length()>0) {
    		try {
    			Integer.parseInt(id);
    		}catch(Exception e) {
    			id = "1";
    		}
    	}else {
    		id = "1";
    	}    	
    	return this.drugTemplateMapper.getDrugTemplateByDocid(id,name);
    }
}