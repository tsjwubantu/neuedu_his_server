package com.neuedu.his.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ConstanttypeMapper extends BaseMapper{
	/** 
	 * 查询所有类型的方法   分页显示 
	 * @param: start当前页   count每页显示记录数量   keywords查询关键字
	 * @return: 包含查询结果的Map集合
	*/ 
    List<Map<String,Object>> getConstantTypeList(@Param("start") Integer start, @Param("count") Integer count
            , @Param("keywords") String keywords);
    
    /** 
     * 获取符合条件的常数类型数量的方法
     * @param keywords查询关键字 
     * @return  数量值 
     * @auther: 东软教师
     * @date:   2019-2-27
     */ 
    int getConstantTypeCount(@Param("keywords") String keywords);
    
    /** 
     * 删除常数类型的方法，可以批量删除
     * @param 待删除的ids 字符串 (1-n个id) 
     * @return 是否删除成功的布尔值 
     * @auther: 东软教师
     * @date:   2019-2-27
     */ 
    int deleteConstantTypeByIds(@Param("ids") String[] ids);
    
    /** 
     * 更新常量类型的方法
     * @param constantType 包含更新信息的Map集合
     * @return 是否更新成功的布尔值 
     * @auther: 东软教师
     * @date:   2019-2-27
     */  
    int updateConstantTypeById(Map<String, Object> constantType);
    
    /** 
     * 添加常量类型的方法
     * @param constantType 包含常量类型信息的Map集合 
     * @return 1添加成功  0添加失败 
     * @auther: 东软教师
     * @date:   2019-2-27
     */ 
    int addConstantType(Map<String, Object> constantType);
}
