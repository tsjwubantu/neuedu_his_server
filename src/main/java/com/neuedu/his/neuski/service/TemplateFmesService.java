package com.neuedu.his.neuski.service;

import java.util.List;
import java.util.Map;

public interface TemplateFmesService {

	List<Map<String, Object>> getTemplateChecksList(String keywords01);

	int addTemplateChecks(Map<String, Object> map);

	int updateTemplateCheckById(Map<String, Object> map);

	boolean deleteTemplateChecksByIds(String ids);

}