package com.neuedu.his.neusys.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** 
* 科室管理专属Service接口  
* @auther: 东软教师
* @date:   2019-2-18
*/ 
public interface DepartmentService {
	/** 
     * 按条件统计记录数量  确定页数最大值时用的
     * @param keywords 查询关键字
     * @return 记录数量  整型
     */ 
	int getDepartmentCount(String keywords);

	/** 
     * 按条件分页查询科室记录的方法 
     * @param: page当前页   count每页显示记录数量   keywords查询关键字
     * @return: 包含查询结果的Map集合
     */ 
	List<Map<String, Object>> getDepartmentList(Integer page, Integer count, String keywords);

	/** 
	 * 删除科室记录的方法  
	 * @param ids ID字符串
	 * @return 删除成功==true  删除失败==false
     */
	boolean deleteDepartmentByIds(String ids);

	/** 
	 * 更新科室记录的方法
	 * @param 包含更新信息的科室对象
	 * @return 更新成功==1  更新失败==0
     */
	int updateDepartmentById(Map<String, Object> department);

	/** 
	 * 添加科室记录的方法  
	 * @param 包含科室信息的对象
	 * @return 添加成功==1  添加失败==0
     */
	int addDepartment(Map<String, Object> department);
}