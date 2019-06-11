package com.neuedu.his.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DrugstemplateMapper extends BaseMapper{
	List<Map<String, Object>> getDrugTemplateList(@Param("keywords01") String keywords01, @Param("keywords02") String keywords02, @Param("UserID") String UserID);

	int getDrugTemplateCount(@Param("keywords") String keywords);

	int deleteDrugTemplateByIds(@Param("ids") String[] ids);

	int updateDrugTemplateById(Map<String, Object> map);

	int addDrugTemplate(Map<String, Object> map);
	
	List<Map<String, Object>> getDrugTemplateByDocid(@Param("ID") String id, @Param("Name") String name);
}
