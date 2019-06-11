package com.neuedu.his.neupha.service;

import java.util.List;
import java.util.Map;

public interface TemplateDrugsService {

	List<Map<String, Object>> getTemplateDrugsList(String keywords01);

	int addTemplateDrugs(Map<String, Object> map);

	int updateTemplateDrugsById(Map<String, Object> map);

	boolean deleteTemplateDrugsByIds(String ids);

}