package com.neuedu.his.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MedicalrecordMapper extends BaseMapper{
	//病历号CaseNumber，加载一条病历信息
    public List<Map<String,Object>> getMedicalRecord(@Param("RegistID") String RegistID);
}
