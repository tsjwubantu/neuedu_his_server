package com.neuedu.his.neusys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neuedu.his.bean.RespBean;
import com.neuedu.his.bean.User;
import com.neuedu.his.neusys.service.UserService;


/** 
* 用户管理专属Controller 
* @auther: 东软教师
* @date:   2019-2-18
*/ 
@RestController
@RequestMapping("/neusys/user")
public class UserController {
    @Autowired
    UserService userService;

    /** 
     * 按条件分页查询用户的方法   
     * @param: page当前页   count每页显示记录数量   keywords 查询关键字
     * @return: 包含查询结果的Map集合
     */  
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Map<String, Object> getUserList( @RequestParam(value = "page", defaultValue = "1") Integer page, 
    		@RequestParam(value = "count", defaultValue = "10") Integer count,String keywords) {
        int totalCount = userService.getUserCount(keywords);
        List<Map<String,Object>> list = userService.getUserList( page, count,keywords);
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);
        map.put("list", list);
        
        return map;
    }
    
    /** 
     * 按条件分页查询用户的方法    新增排班规则时的医生查询
     * @param: page当前页   count每页显示记录数量   keywords keywords2 keywords3查询关键字
     * @return: 包含查询结果的Map集合
     */  
    @RequestMapping(value = "/all02", method = RequestMethod.GET)
    public Map<String, Object> getUserList02( @RequestParam(value = "page", defaultValue = "1") Integer page, 
    		@RequestParam(value = "count", defaultValue = "10") Integer count,String keywords,String keywords2,String keywords3) {
        int totalCount = userService.getUserCount02(keywords,keywords2,keywords3);
        List<Map<String,Object>> list = userService.getUserList02( page, count,keywords,keywords2,keywords3);
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);
        map.put("list", list);
        
        return map;
    }
    
    /** 
     * 根据id号删除用户的方法  可以批量删除
     * @param: ids 包含id的字符串
     * @return: 包含响应信息的对象
     */ 
    @RequestMapping(value = "/{ids}", method = RequestMethod.DELETE)
    public RespBean deleteDepartmentById(@PathVariable String ids) {
        boolean result = userService.deleteUserByIds(ids);
        if (result) {
            return new RespBean("success", "删除成功!(用户)");
        }
        return new RespBean("error", "删除失败!(用户)");
    }
    
    /** 
     * 更新用户的方法 
     * @param: user 包含更新信息的Map集合
     * @return: 包含响应信息的对象
     */ 
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public RespBean updateUser(@RequestParam Map<String,Object> user) {
        int i = userService.updateUserById(user);
        if (i == 1) {
            return new RespBean("success", "修改成功!(用户)");
        }
        return new RespBean("error", "修改失败!(用户)");
    }
    
    /** 
     * 添加用户的方法 
     * @param: user 包含科室信息的Map集合
     * @return: 包含响应信息的对象
     */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public RespBean addNewUser(@RequestParam Map<String,Object> user) {
        int result = userService.addUser(user);
        if (result == 1) {
            return new RespBean("success", "添加成功!(用户)");
        }
        return new RespBean("error", "添加失败!(用户)");
    }
    
    /** 
     * 用户登录的方法 
     * @param: user 包含用户登录信息的Map集合
     * @return: 包含响应信息的对象
     */ 
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public RespBean login(@RequestParam Map<String,Object> user) {
        int result = userService.login(user);
        if (result == 1) {
            return new RespBean("success", "登录成功!(用户)");
        }
        return new RespBean("error", "登录失败!(用户)");
    }
    
    /** 
     * 通过用户名和密码查找指定的用户对象
     * @param: keywords 用户名, keywords2 密码
     * @return: 指定的用户对象
     */ 
    @RequestMapping(value = "/get_user", method = RequestMethod.GET)
    public User getUserBean(@RequestParam String keywords, @RequestParam String keywords2,HttpSession session){
    	User user = userService.getUserBean(keywords, keywords2);    	
    	if(user != null)
    	   session.setAttribute("login_user", user);
    	return  user;
    }
}