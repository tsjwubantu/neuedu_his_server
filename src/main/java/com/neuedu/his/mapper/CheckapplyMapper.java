package com.neuedu.his.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CheckapplyMapper extends BaseMapper{
	List<Map<String, Object>> getApplyByRegistAndStateAndType(@Param("RegistID") String RegistID, @Param("State") String State, @Param("RecordType") String RecordType);
}
