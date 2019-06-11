package com.neuedu.his.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DrugsdetailedMapper extends BaseMapper{
	List<Map<String, Object>> getTemplateDrugsList(@Param("keywords01") String keywords01);

	int addTemplateDrugs(Map<String, Object> map);

	int updateTemplateDrugsById(Map<String, Object> map);

	int deleteTemplateDrugsByIds(@Param("ids") String[] ids);
	
	int deleteTemplateDrugsByTemplate(@Param("TemplateIds") String[] TemplateId);
}
