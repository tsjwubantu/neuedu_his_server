package com.neuedu.his.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Mapper
@Repository
public interface SchedulingMapper extends BaseMapper{
	
    List<Map<String,Object>> getSchedulingList(@Param("start") Integer start, @Param("count") Integer count
            , @Param("keywords") String keywords, @Param("keywords2") String keywords2);
    
    //1午别 根据排班日期(看诊日期)查午别
    List<Map<String,Object>> getSchedulingList05(@Param("start") Integer start, @Param("count") Integer count
            , @Param("keywords") String keywords);
    
    //2科室   根据日期和午别查科室
    List<Map<String,Object>> getSchedulingList03(@Param("start") Integer start, @Param("count") Integer count
            , @Param("keywords") String keywords, @Param("keywords2") String keywords2);
    
    //3号别   根据日期 午别 科室 查号别
    List<Map<String,Object>> getSchedulingList04(@Param("start") Integer start, @Param("count") Integer count
            , @Param("keywords") String keywords, @Param("keywords2") String keywords2, @Param("keywords3") String keywords3);

    //4医生  根据日期 午别 科室 号别 查医生
    List<Map<String,Object>> getSchedulingList02(@Param("start") Integer start, @Param("count") Integer count
            , @Param("keywords") String keywords, @Param("keywords2") String keywords2
            , @Param("keywords3") String keywords3, @Param("keywords4") String keywords4);
    
    
    int getSchedulingCount(@Param("keywords") String keywords, @Param("keywords2") String keywords2);
    
    int getSchedulingCount02(@Param("keywords") String keywords);
    
    int getSchedulingCount06(@Param("keywords") String keywords, @Param("keywords2") String keywords2
            , @Param("keywords3") String keywords3);
    
    //获取排班医生的 挂号费金额
    float getSchedulingCount07(@Param("keywords") String keywords, @Param("keywords2") String keywords2
            , @Param("keywords3") String keywords3);
    
    //获取排班医生的 挂号级别
    int getSchedulingCount08(@Param("keywords") String keywords, @Param("keywords2") String keywords2
            , @Param("keywords3") String keywords3);
    
    //获取排班医生的 科室id
    int getSchedulingCount09(@Param("keywords") String keywords, @Param("keywords2") String keywords2
            , @Param("keywords3") String keywords3);
    
    //删除
    //父类有 int deleteSchedulingByIds(@Param("ids") String[] ids);
    //删除2 更新状态 代替删除 
    int deleteSchedulingByIds2(@Param("ids") String[] ids);
    
    //删除3 按日期 用户id 午别的删除
    int deleteSchedulingByDate(@Param("SchedDate") String schedDate,
                               @Param("UserId") String userId, @Param("Noon") String noon);
    
    //更新
    int updateSchedulingById(Map<String, Object> scheduling);
    //添加
    int addScheduling(Map<String, Object> scheduling);
    
}
