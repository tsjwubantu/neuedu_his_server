package com.neuedu.his.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DiseaseMapper extends BaseMapper{
   public List<Map<String,Object>> getDiseaseByType(@Param("start") int start, @Param("count") Integer count, @Param("DicaType") String DicaType);
   
   public Integer getDiseaseByTypeCount(@Param("DicaType") String DicaType);
   
   //根据疾病名称或ICD查询--无分页
   public List<Map<String,Object>> getDiseaseByNameOrIcd(@Param("DicaType") String DicaType, @Param("keywords01") String keywords01);

   //---------------- 下 是 高 ----------------
   
   List<Map<String,Object>> getDiseaseList(@Param("start") Integer start, @Param("count") Integer count
           , @Param("keywords") String keywords);

   int getDiseaseCount(@Param("keywords") String keywords);
   
   //删除
   int deleteDiseaseByIds(@Param("ids") String[] ids);
   //删除2
   int deleteDiseaseByIds2(@Param("ids") String[] ids);
   
   //更新
   int updateDiseaseById(Map<String, Object> disease);
   //添加
   int addDisease(Map<String, Object> disease);
}
