package com.neuedu.his.neusys.service;

import java.util.List;
import java.util.Map;

/** 
* 费用科目管理专属Service接口 
* @auther: 东软教师
* @date:   2019-2-18
*/ 
public interface ExpenseClassService {
    
	/** 
     * 按条件统计记录数量  确定页数最大值时用的
     * @param keywords 查询关键字
     * @return 记录数量  整型
     */ 
	int getExpenseClassCount(String keywords);

	/** 
     * 按条件分页查询费用科目记录的方法 
     * @param: page当前页   count每页显示记录数量   keywords查询关键字
     * @return: 包含查询结果的Map集合
     */ 
	List<Map<String, Object>> getExpenseClassList(Integer page, Integer count, String keywords);
    
	/** 
	 * 删除费用科目记录的方法  
	 * @param ids ID字符串
	 * @return 删除成功==true  删除失败==false
     */
	boolean deleteExpenseClassByIds(String ids);

	/** 
	 * 更新费用科目记录的方法
	 * @param 包含更新信息的费用科目对象
	 * @return 更新成功==1  更新失败==0
     */
	int updateExpenseClassById(Map<String, Object> expenseClass);

	/** 
	 * 添加费用科目记录的方法  
	 * @param 包含费用科目信息的对象
	 * @return 添加成功==1  添加失败==0
     */
	int addExpenseClass(Map<String, Object> expenseClass);
}