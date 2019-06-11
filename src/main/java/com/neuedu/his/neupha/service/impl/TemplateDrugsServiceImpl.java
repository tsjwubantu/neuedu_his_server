package com.neuedu.his.neupha.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neuedu.his.mapper.DrugsdetailedMapper;
import com.neuedu.his.neupha.service.TemplateDrugsService;

/** 
* @auther: 东软教师
* @date:   2018-11-29
*/ 
@Service
@Transactional
public class TemplateDrugsServiceImpl implements TemplateDrugsService {
    @Autowired
    DrugsdetailedMapper templateDrugsMapper;
  
	/* (non-Javadoc)
	 * @see com.neuedu.neupha.service.impl.TemplateDrugsService#getTemplateDrugsList(java.lang.String)
	 */
	@Override
	public List<Map<String, Object>> getTemplateDrugsList(String keywords01) {
		return templateDrugsMapper.getTemplateDrugsList(keywords01);
	}

	/* (non-Javadoc)
	 * @see com.neuedu.neupha.service.impl.TemplateDrugsService#addTemplateDrugs(java.util.Map)
	 */
	@Override
	public int addTemplateDrugs(Map<String, Object> map) {
		return templateDrugsMapper.addTemplateDrugs(map);
	}

	/* (non-Javadoc)
	 * @see com.neuedu.neupha.service.impl.TemplateDrugsService#updateTemplateDrugsById(java.util.Map)
	 */
	@Override
	public int updateTemplateDrugsById(Map<String, Object> map) {
		return templateDrugsMapper.updateTemplateDrugsById(map);
	}

	/* (non-Javadoc)
	 * @see com.neuedu.neupha.service.impl.TemplateDrugsService#deleteTemplateDrugsByIds(java.lang.String)
	 */
	@Override
	public boolean deleteTemplateDrugsByIds(String ids) {
		String[] split = ids.split(",");
        int result = templateDrugsMapper.deleteTemplateDrugsByIds(split);
        return result == split.length;
	}     
}