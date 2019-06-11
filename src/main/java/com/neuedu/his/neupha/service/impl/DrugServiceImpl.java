package com.neuedu.his.neupha.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neuedu.his.bean.RespBean;
import com.neuedu.his.mapper.DrugsMapper;
import com.neuedu.his.neupha.service.DrugService;
@Service
public class DrugServiceImpl implements DrugService {
	
	@Autowired
	private DrugsMapper drugsMapper;

	@Override
	@Transactional(readOnly=true)
	public RespBean getDrugList( Integer page, Integer count,String keywords) {
		RespBean resp = new RespBean();
		if(count == 10 || count<=1){
			count = resp.defaultSize;
		}
	    int start = (page - 1) * count;
	    resp.setList(drugsMapper.getDrugList(start, count, keywords));
	    resp.setTotalCount(drugsMapper.getDrugCount(keywords));	        
	    return resp;
	}

	@Override
	@Transactional(readOnly=true)
    public int getDrugCount( String keywords) {
        return drugsMapper.getDrugCount(keywords);
    }


	@Override
	@Transactional
	public int addDrugOne(Map<String, Object> map) {
		map.remove("ID");		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			String creationDate = map.get("CreationDate").toString();
			map.put("CreationDate",sdf.parse(creationDate));
		} catch (Exception e) {
			map.put("CreationDate",new java.util.Date());
		}
		return drugsMapper.insertSelective(map);
	}

	@Override
	@Transactional
	public int updateDrugOne(Map<String, Object> map) {
		map.remove("CreationDate");
		map.put("LastUpdateDate", new java.util.Date());
		return drugsMapper.updateByPrimaryKeySelective(map);
	}

	@Override
	@Transactional
	public boolean delDrug(String ids) {
        String[] split = ids.split(",");
        int result = 0;
        Map<String,Object> map = new HashMap<String,Object>();
        for(int i=0;i<split.length;i++) {
        	map.put("ID", split[i]);
        	map.put("DelMark", 0);
        	result += drugsMapper.updateByPrimaryKeySelective(map);
        }        
        return result == split.length;
	}

	@Override
	@Transactional
    public int addDrug(List<Map<String,Object>> drugList) {
		List<Map<String,Object>> dbList = new ArrayList<>(drugList.size());
		for(Map<String,Object> map : drugList) {
			dbList.add(this.excelmapToDbmap(map));
		}
		//增加数据条数
		int addNumber = 0;
    	//每10条批量插入一次，性能最好
		int batchCount = 10;
		int drugListSize = drugList.size();
        int subCount = drugListSize%batchCount==0 ? drugListSize/batchCount : drugListSize/batchCount+1;
        int startIndext = 0;
        int stopIndext = 0;
        for(int i=0;i<subCount;i++){
            stopIndext = (i==subCount-1) ? stopIndext + drugListSize%batchCount : stopIndext + batchCount;
            addNumber += drugsMapper.insertDrugsBatch(dbList.subList(startIndext, stopIndext)); 
            startIndext = stopIndext;
        }
        return addNumber;
    }
	private Map<String,Object> excelmapToDbmap(Map<String,Object> map){
		Map<String,String> ruleMap = new HashMap<>();
		initRuleMap(ruleMap);
		
		Map<String,Object> dbMap = new HashMap<>();
		Set<Entry<String, Object>> set = map.entrySet();
		for(Entry<String, Object> entry : set) {
			dbMap.put(ruleMap.get(entry.getKey()), entry.getValue());
		}
		return dbMap;
	}
	private void initRuleMap(Map<String,String> ruleMap) {
		ruleMap.put("药品编码", "DrugsCode");
		ruleMap.put("药品名称", "DrugsName");
		ruleMap.put("药品规格", "Format");
		ruleMap.put("包装单位", "Unit");
		ruleMap.put("生产厂家", "Manufacturer");
		ruleMap.put("药品剂型", "Dosage");
		ruleMap.put("药品类型", "DrugsType");
		ruleMap.put("药品单价", "Price");
		ruleMap.put("拼音助记码", "MnemonicCode");
	}

}
