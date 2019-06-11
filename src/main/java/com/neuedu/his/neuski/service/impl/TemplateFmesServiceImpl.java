package com.neuedu.his.neuski.service.impl;

import java.util.List;
import java.util.Map;

import com.neuedu.his.neuski.service.TemplateFmesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neuedu.his.mapper.CheckrelationMapper;

/** 
* @auther: 东软教师
* @date:   2018-11-29
*/ 
@Service
@Transactional
public class TemplateFmesServiceImpl implements TemplateFmesService {

    @Autowired
    CheckrelationMapper checkrelationMapper;
  

	@Override
	public List<Map<String, Object>> getTemplateChecksList(String keywords01) {
		Integer CheckTempID = 0;
		try{
			CheckTempID = new Integer(keywords01);
		}catch(Exception e){
			CheckTempID = 0;
		}
		return checkrelationMapper.getTemplateChecksList(CheckTempID);
	}


	@Override
	public int addTemplateChecks(Map<String, Object> map) {
		map.remove("ID");
		return checkrelationMapper.insertSelective(map);
	}


	@Override
	public int updateTemplateCheckById(Map<String, Object> map) {
		return checkrelationMapper.updateByPrimaryKeySelective(map);
	}


	@Override
	public boolean deleteTemplateChecksByIds(String ids) {
		String[] split = ids.split(",");
        int result = checkrelationMapper.deleteByPrimaryKey(split);
        return result == split.length;
	}     
}