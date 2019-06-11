package com.neuedu.his.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DrugsMapper extends BaseMapper{
	//批量插入上传的excel数据
    public int insertDrugsBatch(List<Map<String, Object>> drugList);
    
    //分页显示药品信息
    public List<Map<String,Object>> getDrugList(@Param("start") int start, @Param("count") Integer count, @Param("keywords") String keywords);
    int getDrugCount(@Param("keywords") String keywords);
    
    
}
