package com.neuedu.his.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Mapper
@Repository
public interface RuleMapper extends BaseMapper {
	
    List<Map<String,Object>> getRuleList(@Param("start") Integer start, @Param("count") Integer count
            , @Param("keywords") String keywords);

    int getRuleCount(@Param("keywords") String keywords);
    
    //删除
    int deleteRuleByIds(@Param("ids") String[] ids);
    //删除2
    int deleteRuleByIds2(@Param("ids") String[] ids);
    
    //更新r
    int updateRuleById(Map<String, Object> rule);
    //添加d
    int addRule(Map<String, Object> rule);
}
