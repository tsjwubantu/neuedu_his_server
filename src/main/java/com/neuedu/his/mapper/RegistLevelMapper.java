package com.neuedu.his.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Mapper
@Repository
public interface RegistLevelMapper extends BaseMapper {
	
    List<Map<String,Object>> getRegistLevelList(@Param("start") Integer start, @Param("count") Integer count
            , @Param("keywords") String keywords);

    int getRegistLevelCount(@Param("keywords") String keywords);
    
    //删除
    int deleteRegistLevelByIds(@Param("ids") String[] ids);
    //删除2
    int deleteRegistLevelByIds2(@Param("ids") String[] ids);
    
    //更新
    int updateRegistLevelById(Map<String, Object> department);
    //添加d
    int addRegistLevel(Map<String, Object> department);
    
}
