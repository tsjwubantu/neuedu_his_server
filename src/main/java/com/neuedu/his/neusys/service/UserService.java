package com.neuedu.his.neusys.service;

import java.util.List;
import java.util.Map;

import com.neuedu.his.bean.User;

/** 
* 用户管理专属Service接口 
* @auther: 东软教师
* @date:   2019-2-18
*/ 
public interface UserService {

	/** 
     * 按条件统计记录数量  确定页数最大值时用的
     * @param keywords 查询关键字
     * @return 记录数量  整型
     */ 
	int getUserCount(String keywords);

	/** 
     * 按条件分页查询用户记录的方法 
     * @param: page当前页   count每页显示记录数量   keywords查询关键字
     * @return: 包含查询结果的Map集合
     */ 
	List<Map<String, Object>> getUserList(Integer page, Integer count, String keywords);

	/** 
     * 按条件统计记录数量  确定页数最大值时用的
     * @param keywords keywords2 keywords3 查询关键字
     * @return 记录数量  整型
     */ 
	int getUserCount02(String keywords, String keywords2, String keywords3);

	/** 
     * 按条件分页查询用户记录的方法 
     * @param: page当前页   count每页显示记录数量   keywords keywords2 keywords3 查询关键字
     * @return: 包含查询结果的Map集合
     */ 
	List<Map<String, Object>> getUserList02(Integer page, Integer count,
                                            String keywords, String keywords2,
                                            String keywords3);
	/** 
	 * 删除用户记录的方法  
	 * @param ids ID字符串
	 * @return 删除成功==true  删除失败==false
     */
	boolean deleteUserByIds(String ids);

	/** 
	 * 更新用户记录的方法
	 * @param 包含更新信息的用户对象
	 * @return 更新成功==1  更新失败==0
     */
	int updateUserById(Map<String, Object> user);

	/** 
	 * 添加用户记录的方法  
	 * @param 包含用户信息的对象
	 * @return 添加成功==1  添加失败==0
     */
	int addUser(Map<String, Object> user);
	
	/** 
	 * 用户登录的方法 
	 * @param 包含用户信息的对象
	 * @return 登录成功==1 登录失败==0
     */
	int login(Map<String, Object> user);
	
	User getUserBean(String keywords, String keywords2);
}
