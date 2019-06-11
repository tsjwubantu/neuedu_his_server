package com.neuedu.his.neusys.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** 
* 常数内容管理专属Service接口
* @auther: 东软教师
* @date:   2019-2-18
*/ 
public interface ConstantItemService {
	/** 
	 * 获取常数项内容数据列表的方法 
	 * @param 根据常数类别表ConstantType中，常数类别编码ConstantTypeCode
	 * @return 包含常数项内容对象的列表 ,无分页
     */
	List<Map<String, Object>> getConstantItemList(String constantTypeCode);

	/** 
	 * 获取常数项内容数据列表的方法 
	 * @param 无
	 * @return 包含常数项内容对象的列表 ,无分页
     */
	List<Map<String, Object>> getConstantItemList();

	/** 
     * 获取符合条件的记录数量
     * @param keywords keywords2 查询条件
     * @return 记录数量 
     */ 
	int getConstantItemCount(String keywords, String keywords2);

	/** 
     * 获取常数项内容数据列表
     * @param 当前页 每页显示记录数 keywords keywords2 查询条件 
     * @return 记录列表
    */ 
	List<Map<String, Object>> getConstantItemList(Integer page, Integer count, String keywords, String keywords2);

	/** 
     * 获取符合条件的记录数量
     * @param keywords查询条件
     * @return 记录数量 
     */ 
	int getConstantItemCount2(String keywords);

	/** 
	 * 获取常数项内容数据列表的方法 
	 * @param keywords查询条件
	 * @return 包含常数项内容对象的列表 ,无分页
     */
	List<Map<String, Object>> getConstantItemList2(String keywords);
	
	/** 
	 * 添加常数项内容记录的方法  
	 * @param 包含常数项内容信息的对象
	 * @return 添加成功==1  添加失败==0
     */
	int addConstantItem(Map<String, Object> constantItem);

	/** 
	 * 删除常数项内容记录的方法   业务上禁用
	 * @param ids ID字符串
	 * @return 删除成功==true  删除失败==false
     */
	boolean deleteConstantItemByIds(String ids);
    
	/** 
	 * 更新常数项内容记录的方法   业务上禁用
	 * @param 包含更新信息的常数项内容对象
	 * @return 更新成功==1  更新失败==0
     */
	int updateConstantItemById(Map<String, Object> constantItem);
}