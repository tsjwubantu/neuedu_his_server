package com.neuedu.his.neusys.service;

import java.util.List;
import java.util.Map;

/** 
* 常数类型管理专属Service接口 
* @auther: 东软教师
* @date:   2019-2-18
*/ 
public interface ConstantTypeService {
    
	/** 
     * 获取符合条件的记录数量
     * @param keywords查询条件
     * @return 记录数量 
     */ 
	int getConstantTypeCount(String keywords);

	/** 
     * 获取常数类型数据列表
     * @param 当前页 每页显示记录数 keywords 查询条件 
     * @return 记录列表
    */ 
	List<Map<String, Object>> getConstantTypeList(Integer page, Integer count, String keywords);

	/** 
	 * 添加常数类型记录的方法  
	 * @param 包含常数类型信息的对象
	 * @return 添加成功==1  添加失败==0
     */
	int addConstantType(Map<String, Object> constantType);
	
	/** 
	 * 删除常数项类型记录的方法   业务上禁用
	 * @param ids ID字符串
	 * @return 删除成功==true  删除失败==false
     */
	boolean deleteConstantTypeByIds(String ids);

	/** 
	 * 更新常数类型记录的方法   业务上禁用
	 * @param 包含更新信息的常数类型对象
	 * @return 更新成功==1  更新失败==0
     */
	int updateConstantTypeById(Map<String, Object> constantType);
}
