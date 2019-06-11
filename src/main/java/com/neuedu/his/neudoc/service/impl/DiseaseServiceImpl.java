package com.neuedu.his.neudoc.service.impl;

import com.neuedu.his.neudoc.service.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neuedu.his.bean.RespBean;
import com.neuedu.his.mapper.DiseaseMapper;

@Service
@Transactional
public class DiseaseServiceImpl implements DiseaseService {
	@Autowired
	private DiseaseMapper diseaseMapper;

	@Override
	public RespBean getDiseaseByType(Integer page, Integer count,String DicaType) {
		RespBean resp = new RespBean();
		if(count == 10 || count<=1){
			count = resp.defaultSize;
		}
	    int start = (page - 1) * count;
		resp.setList(diseaseMapper.getDiseaseByType(start,count,DicaType));
		resp.setTotalCount(diseaseMapper.getDiseaseByTypeCount(DicaType));
		return resp;
	}
	
	@Override
	public RespBean getDiseaseByNameOrIcd(String DicaType, String keywords01) {
		RespBean resp = new RespBean();
		resp.setList(diseaseMapper.getDiseaseByNameOrIcd(DicaType, keywords01));		
		return resp;
	}
}
