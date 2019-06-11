package com.neuedu.his.neusys.service.impl;

import java.util.List;
import java.util.Map;

import com.neuedu.his.neusys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neuedu.his.bean.User;
import com.neuedu.his.mapper.UserMapper;

/** 
* 用户管理专属Service
* @auther: 东软教师
* @date:   2019-2-18
*/ 
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    
    /** 
     * 按条件分页查询用户的方法   
     * @param: page当前页   count每页显示记录数量   keywords 查询关键字
     * @return: 包含查询结果的Map集合
     */ 
    public List<Map<String,Object>> getUserList( Integer page, Integer count,String keywords) {
         int start = (page - 1) * count;
         return userMapper.getUserList(start, count, keywords);
    }
    
    /** 
     * 按条件分页查询用户记录的方法 
     * @param: page当前页   count每页显示记录数量   keywords keywords2 keywords3 查询关键字
     * @return: 包含查询结果的Map集合
     */ 
    public List<Map<String,Object>> getUserList02( Integer page, Integer count,String keywords,String keywords2,String keywords3) {
        int start = (page - 1) * count;
        return userMapper.getUserList02(start, count, keywords, keywords2, keywords3);
    }

    
    /** 
     * 按条件统计记录数量  确定页数最大值时用的
     * @param keywords 查询关键字
     * @return 记录数量  整型
     */ 
     public int getUserCount( String keywords) {
         return userMapper.getUserCount(keywords);
     }

     /** 
      * 按条件统计记录数量  确定页数最大值时用的
      * @param keywords keywords2 keywords3 查询关键字
      * @return 记录数量  整型
      */ 
     public int getUserCount02( String keywords,String keywords2,String keywords3) {
         return userMapper.getUserCount02(keywords, keywords2, keywords3);
     }
     
     /** 
 	 * 删除用户记录的方法  
 	 * @param ids ID字符串
 	 * @return 删除成功==true  删除失败==false
      */
      public boolean deleteUserByIds(String ids) {
         String[] split = ids.split(",");
         int result = userMapper.deleteUserByIds(split);
         return result == split.length;
     }

    /** 
  	 * 更新用户记录的方法
  	 * @param 包含更新信息的用户对象
  	 * @return 更新成功==1  更新失败==0
     */ 
     public int updateUserById(Map<String,Object> user) {
          return userMapper.updateUserById(user);
     }
     
    /** 
 	 * 添加用户记录的方法  
 	 * @param 包含用户信息的对象
 	 * @return 添加成功==1  添加失败==0
     */
     public int addUser(Map<String,Object> user) {
         return userMapper.addUser(user);
     }
     
     @Override
 	public int login(Map<String, Object> user) {
 		return userMapper.login(user);
 	}
 	
 	@Override
 	public User getUserBean(String keywords, String keywords2) {
 		return userMapper.getUserBean(keywords,keywords2);
 	}
}