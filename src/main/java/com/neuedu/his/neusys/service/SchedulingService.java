package com.neuedu.his.neusys.service;

import java.util.List;
import java.util.Map;

/** 
* 排班管理专属Service接口 
* @auther: 东软教师
* @date:   2019-2-18
*/ 
public interface SchedulingService {
    
	/** 
     * 按条件统计记录数量  确定页数最大值时用的
     * @param keywords keywords2 查询关键字
     * @return 记录数量  整型
     */ 
	int getSchedulingCount(String keywords, String keywords2);
	
	/** 
     * 按条件统计记录数量  确定页数最大值时用的
     * @param keywords 查询关键字
     * @return 记录数量  整型
     */ 
	int getSchedulingCount02(String keywords);
	
	/** 
     * 获取排班医生的 挂号额度
     * @param keywords keywords2 keywords3 查询关键字
     * @return 挂号额度  整型
     */ 
	int getSchedulingCount06(String keywords, String keywords2, String keywords3);
	
	/** 
     * 获取排班医生的 挂号费金额
     * @param keywords keywords2 keywords3 查询关键字
     * @return 挂号费金额  单精度浮点类型
     */ 
	float getSchedulingCount07(String keywords, String keywords2, String keywords3);

	/** 
     * 按条件分页查询规则记录的方法 
     * @param: page当前页   count每页显示记录数量   keywords keywords2查询关键字
     * @return: 包含查询结果的Map集合
     */ 
	List<Map<String, Object>> getSchedulingList(Integer page, Integer count, String keywords, String keywords2);

	/** 
     * 根据日期 午别 科室 号别 查医生  按条件分页查询规则记录的方法 
     * @param: page当前页   count每页显示记录数量   keywords keywords2 keywords3 keywords4查询关键字
     * @return: 包含查询结果的Map集合
     */ 
	List<Map<String, Object>> getSchedulingList02(Integer page, Integer count, String keywords, String keywords2,
                                                  String keywords3, String keywords4);
	
	
	/** 
     * 根据日期和午别查科室   按条件分页查询规则记录的方法 
     * @param: page当前页   count每页显示记录数量   keywords keywords2查询关键字
     * @return: 包含查询结果的Map集合
     */ 
	List<Map<String, Object>> getSchedulingList03(Integer page, Integer count, String keywords, String keywords2);

	
	/** 
     * 根据日期 午别 科室 查号别    按条件分页查询规则记录的方法 
     * @param: page当前页   count每页显示记录数量   keywords keywords2 keywords3查询关键字
     * @return: 包含查询结果的Map集合
     */ 
	List<Map<String, Object>> getSchedulingList04(Integer page, Integer count, String keywords, String keywords2,
                                                  String keywords3);
	
	
	/** 
     * 根据排班日期(看诊日期)查午别   按条件分页查询规则记录的方法    
     * @param: page当前页   count每页显示记录数量   keywords 查询关键字
     * @return: 包含查询结果的Map集合
     */ 
	List<Map<String, Object>> getSchedulingList05(Integer page, Integer count, String keywords);

	/** 
	 * 删除排班记录的方法  
	 * @param ids ID字符串
	 * @return 删除成功==true  删除失败==false
     */
	boolean deleteSchedulingByIds(String ids);

	/** 
	 * 更新排班记录的方法
	 * @param 包含更新信息的排班对象
	 * @return 更新成功==1  更新失败==0
     */
	int updateSchedulingById(Map<String, Object> scheduling);

	/** 
	 * 添加排班记录的方法  
	 * @param 包含排班信息的对象
	 * @return 添加成功==1  添加失败==0
     */
	int addScheduling(Map<String, Object> scheduling);
}
