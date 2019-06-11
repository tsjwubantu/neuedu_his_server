package com.neuedu.his.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MedicaldiseaseMapper extends BaseMapper {
	//病历号CaseNumber，加载一条病历信息
    public List<Map<String,Object>> getMedicalDisease(@Param("RegistID") String RegistID, @Param("DiagnoseCate") String DiagnoseCate);

    public int deleteByRegistID(@Param("RegistID") String RegistID, @Param("DiagnoseCate") String DiagnoseCate);

}
