package com.neuedu.his.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DisecategoryMapper extends BaseMapper{
	List<Map<String,Object>> getDiseCategoryList(@Param("start") Integer start, @Param("count") Integer count
            , @Param("keywords") String keywords);

    int getDiseCategoryCount(@Param("keywords") String keywords);
    
    //删除
    int deleteDiseCategoryByIds(@Param("ids") String[] ids);
    //删除2
    int deleteDiseCategoryByIds2(@Param("ids") String[] ids);
    
    //更新
    int updateDiseCategoryById(Map<String, Object> diseCategory);
    //添加d
    int addDiseCategory(Map<String, Object> diseCategory);
}
