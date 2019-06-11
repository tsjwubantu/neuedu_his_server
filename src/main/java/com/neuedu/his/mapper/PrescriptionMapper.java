package com.neuedu.his.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PrescriptionMapper extends BaseMapper{
	List<Map<String, Object>> getPresByRegist(@Param("RegistID") String RegistID, @Param("PrescriptionState") String PrescriptionState);
}
