package com.neuedu.his.neusys.service;

import java.util.List;
import java.util.Map;
/** 
* 挂号等级专属Service接口
* @auther: 东软教师
* @date:   2019-2-18
*/ 
public interface RegistLevelService {

	/** 
     * 按条件统计记录数量  确定页数最大值时用的
     * @param keywords 查询关键字
     * @return 记录数量  整型
     */ 
	int getRegistLevelCount(String keywords);

	/** 
     * 按条件分页查询挂号等级记录的方法 
     * @param: page当前页   count每页显示记录数量   keywords查询关键字
     * @return: 包含查询结果的Map集合
     */ 
	List<Map<String, Object>> getRegistLevelList(Integer page, Integer count, String keywords);

	/** 
	 * 删除挂号等级记录的方法  
	 * @param ids ID字符串
	 * @return 删除成功==true  删除失败==false
     */
	boolean deleteRegistLevelByIds(String ids);

	/** 
	 * 更新挂号等级记录的方法
	 * @param 包含更新信息的挂号等级对象
	 * @return 更新成功==1  更新失败==0
     */
	int updateRegistLevelById(Map<String, Object> registLevel);

	/** 
	 * 添加挂号等级记录的方法  
	 * @param 包含挂号等级信息的对象
	 * @return 添加成功==1  添加失败==0
     */
	int addRegistLevel(Map<String, Object> registLevel);
}