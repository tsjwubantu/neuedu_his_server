package com.neuedu.his.neusys.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/** 
* 结算类别专属Service接口 
* @auther: 东软教师
* @date:   2019-2-18
*/ 
public interface SettleCategoryService {

    /** 
     * 根据 类别编码 或者 类别名称 查询结算类别表SettleCategory的记录，无分页
     * @param: keywords查询关键字
     * @return: 包含查询结果的Map集合
     */ 
	public List<Map<String,Object>> getSettleCategoryList(@Param("keywords") String keywords);
	
	//上面 李用------------------------------------------------------------------------------------}
	
	/** 
     * 按条件统计记录数量  确定页数最大值时用的
     * @param keywords 查询关键字
     * @return 记录数量  整型
     */ 
	int getSettleCategoryCount(String keywords);

	/** 
     * 按条件分页查询结算类别记录的方法 
     * @param: page当前页   count每页显示记录数量   keywords查询关键字
     * @return: 包含查询结果的Map集合
     */ 
	List<Map<String, Object>> getSettleCategoryList(Integer page, Integer count, String keywords);

	/** 
	 * 删除结算类别记录的方法  
	 * @param ids ID字符串
	 * @return 删除成功==true  删除失败==false
     */
	boolean deleteSettleCategoryByIds(String ids);
	
	/** 
	 * 更新结算类别记录的方法
	 * @param 包含更新信息的结算类别对象
	 * @return 更新成功==1  更新失败==0
     */
	int updateSettleCategoryById(Map<String, Object> settleCategory);

	/** 
	 * 添加结算类别记录的方法  
	 * @param 包含结算类别信息的对象
	 * @return 添加成功==1  添加失败==0
     */
	int addSettleCategory(Map<String, Object> settleCategory);
}
