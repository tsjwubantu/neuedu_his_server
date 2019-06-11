package com.neuedu.his.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ConstantitemMapper extends BaseMapper{
    //根据常数类别表ConstantType中，常数类别编码ConstantTypeCode，查询符合条件的常数项表ConstantItem的记录，无分页
	public List<Map<String,Object>> getConstantItemListByType(@Param("ConstantTypeCode") String constantTypeCode);
	//查询常数项表ConstantItem的记录，无分页
	public List<Map<String, Object>> getConstantItem();
	
	//-----------------------高
	//版本2 ------------------------------------------------------------------------}
	/** 
     * 获取常数项内容数据列表的方法 
     * @param start当前页   count每页显示记录数量   keywords 查询关键字1 keywords2 查询关键字2
     * @return 包含常数项内容对象的列表 
     * @auther: 东软教师
     * @date:   2019-2-27
     */ 
    List<Map<String,Object>> getConstantItemList(@Param("start") Integer start, @Param("count") Integer count
            , @Param("keywords") String keywords, @Param("keywords2") String keywords2);
    
    /** 
     * 获取常数项内容数据列表的方法 
     * @param  keywords 查询关键字1
     * @return 包含常数项内容对象的列表 
     * @auther: 东软教师
     * @date:   2019-2-27
     */ 
    List<Map<String,Object>> getConstantItemList2(@Param("keywords") String keywords);
    
    /** 
     * 获取符合条件的常数项内容数量
     * @param  keywords 查询关键字1  keywords2 查询关键字2 
     * @return 常数项内容数量 
     * @auther: 东软教师
     * @date:   2019-2-27
     */ 
    int getConstantItemCount(@Param("keywords") String keywords, @Param("keywords2") String keywords2);
    
    /** 
     * 获取符合条件的常数项内容数量
     * @param  keywords 查询关键字1
     * @return 常数项内容数量 
     * @auther: 东软教师
     * @date:   2019-2-27
     */ 
    int getConstantItemCount2(@Param("keywords") String keywords);
    
    /** 
     * 删除常数项内容的方法，可以批量删除
     * @param 待删除的 id (1-n个) 
     * @return 是否删除成功的布尔值 
     * @auther: 东软教师
     * @date:   2019-2-27
     */ 
    int deleteConstantItemByIds(@Param("ids") String[] ids);
    
    /** 
     * 更新常量项内容的方法
     * @param constant 包含更新信息的Map集合
     * @return 是否更新成功的布尔值 
     * @auther: 东软教师
     * @date:   2019-2-27
     */  
    int updateConstantItemById(Map<String, Object> constantItem);
    
    /** 
     * 添加常量项内容的方法
     * @param constant 包含常量项内容信息的Map集合 
     * @return 1添加成功  0添加失败 
     * @auther: 东软教师
     * @date:   2019-2-27
     */ 
    int addConstantItem(Map<String, Object> constantItem);
}
