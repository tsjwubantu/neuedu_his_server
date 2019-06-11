package com.neuedu.his.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CheckrelationMapper extends BaseMapper{

	List<Map<String, Object>> getTemplateChecksList(@Param("CheckTempID") Integer CheckTempID);

	int deleteTemplateChecksByTempsd(@Param("CheckTempID") Integer CheckTempID);
    
}
