package com.neuedu.his.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ExpenseclassMapper extends BaseMapper{
	
	List<Map<String,Object>> getExpenseClassList(@Param("start") Integer start, @Param("count") Integer count
            , @Param("keywords") String keywords);

    int getExpenseClassCount(@Param("keywords") String keywords);
    
    //删除
    int deleteExpenseClassByIds(@Param("ids") String[] ids);
    //删除2
    int deleteExpenseClassByIds2(@Param("ids") String[] ids);
    
    //更新
    int updateExpenseClassById(Map<String, Object> expenseClass);
    //添加d
    int addExpenseClass(Map<String, Object> expenseClass);
    
}
