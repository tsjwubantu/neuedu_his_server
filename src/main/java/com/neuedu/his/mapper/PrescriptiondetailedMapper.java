package com.neuedu.his.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PrescriptiondetailedMapper extends BaseMapper{
	List<Map<String, Object>> getDrugsByPrescription(@Param("PrescriptionID") String PrescriptionID);

	void delDrugsByPrescription(@Param("ids") String[] ids);
	
	List<Map<String, Object>> getPrescriptionByRegisterAndDate(@Param("RegistID") String registID, @Param("SearchDate") Date searchDate);

}
