package com.neuedu.his.neudoc.service.impl;

import java.util.List;
import java.util.Map;

import com.neuedu.his.neudoc.service.CheckTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neuedu.his.mapper.CheckrelationMapper;
import com.neuedu.his.mapper.ChecktemplateMapper;

/**
 * @auther: 东软教师
 * @date: 2018-11-29
 */
@Service
@Transactional
public class CheckTemplateServiceImpl implements CheckTemplateService {
	@Autowired
	ChecktemplateMapper checktemplateMapper;
	@Autowired
	CheckrelationMapper checkrelationMapper;

	// 根据 名称 范围 类型 查询
	@Override
	public List<Map<String, Object>> getCheckTemplateList(String keywords01, String keywords02, String keywords03) {
		return checktemplateMapper.getCheckTemplateList(keywords01, keywords02, keywords03);
	}
	
	// 根据范围 类型  医生 查询模板
	@Override
	public List<Map<String, Object>> getTemplateByDoc(String userID, String recordType, String name) {
		if( "undefined".equals(userID) ) {
			userID = "";
		}
		if( "undefined".equals(recordType) ) {
			recordType = "";
		}
		if( "undefined".equals(name) ) {
			name = "";
		}
		return checktemplateMapper.getTemplateByDoc(userID, recordType, name);
	}

	@Override
	public boolean deleteCheckTemplateByIds(String ids) {
		String[] split = ids.split(",");
		int result = checktemplateMapper.deleteByPrimaryKey(split);
		if(split!=null && split.length==1 ){
			try{
				checkrelationMapper.deleteTemplateChecksByTempsd(Integer.parseInt(split[0]));
			}catch(Exception e){
				return false;
			}
		}
		return result == split.length;
	}

	@Override
	public int updateCheckTemplateById(Map<String, Object> map) {
		map.remove("CreationTime");
		return checktemplateMapper.updateByPrimaryKeySelective(map);
	}

	@Override
	public int addCheckTemplate(Map<String, Object> map) {
		map.remove("ID");
		if (map.get("UserID") == null || map.get("UserID") == "") {
			map.put("UserID", "1");
		} else {
			try {
				Integer.parseInt(map.get("UserID").toString());
			} catch (Exception e) {
				map.put("UserID", "1");
			}
		}
		map.put("CreationTime", new java.util.Date());
		map.put("DelMark", new Integer(1));
		return checktemplateMapper.insertSelective(map);
	}
}