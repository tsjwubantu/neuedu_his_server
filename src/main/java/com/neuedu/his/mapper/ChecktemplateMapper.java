package com.neuedu.his.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ChecktemplateMapper extends BaseMapper{
	List<Map<String, Object>> getCheckTemplateList(@Param("keywords01") String keywords01, @Param("keywords02") String keywords02, @Param("keywords03") String keywords03);

	int getCheckTemplateCount(@Param("keywords") String keywords);
	
	List<Map<String, Object>> getTemplateByDoc(@Param("UserID") String userID, @Param("RecordType") String recordType, @Param("Name") String name);
}
