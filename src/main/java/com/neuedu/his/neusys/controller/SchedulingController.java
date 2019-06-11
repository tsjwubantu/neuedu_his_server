package com.neuedu.his.neusys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.neuedu.his.bean.RespBean;
import com.neuedu.his.neusys.service.SchedulingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/** 
* 排班管理专属Controller 
* @auther: 东软教师
* @date:   2019-2-18
*/ 
@RestController
@RequestMapping("/neusys/scheduling")
public class SchedulingController {
    @Autowired
    SchedulingService schedulingService;

    /** 
     * 按条件分页查询排班的方法   
     * @param: page当前页   count每页显示记录数量   keywords keywords2 查询关键字
     * @return: 包含查询结果的Map集合
     */  
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Map<String, Object> getSchedulingList( @RequestParam(value = "page", defaultValue = "1") Integer page, 
    		@RequestParam(value = "count", defaultValue = "10") Integer count,@RequestParam("keywords") String keywords,@RequestParam("keywords2") String keywords2) {
        int totalCount = schedulingService.getSchedulingCount(keywords,keywords2);
        List<Map<String,Object>> list = schedulingService.getSchedulingList( page, count,keywords,keywords2);
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);//totalCount  10
        map.put("list", list);
        
        return map;
    }
    
    /**
     * 按条件分页查询排班的方法      1午别 根据排班日期(看诊日期)查午别
     * @param: page当前页   count每页显示记录数量   keywords  查询关键字
     * @return: 包含查询结果的Map集合
     */  
    @RequestMapping(value = "/get_noon", method = RequestMethod.GET)
    public Map<String, Object> getSchedulingList05( @RequestParam(value = "page", defaultValue = "1") Integer page, 
    		@RequestParam(value = "count", defaultValue = "20") Integer count,@RequestParam("keywords") String keywords) {
        int totalCount = schedulingService.getSchedulingCount02(keywords);
        List<Map<String,Object>> list = schedulingService.getSchedulingList05( page, count,keywords);
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);//totalCount  20
        map.put("list", list);
        
        return map;
    }
    
    /** all03
     * 按条件分页查询排班的方法      2科室   根据日期和午别查科室
     * @param: page当前页   count每页显示记录数量   keywords keywords2 查询关键字
     * @return: 包含查询结果的Map集合
     */  
    @RequestMapping(value = "/get_dept", method = RequestMethod.GET)
    public Map<String, Object> getSchedulingList03( @RequestParam(value = "page", defaultValue = "1") Integer page, 
    		@RequestParam(value = "count", defaultValue = "20") Integer count,@RequestParam("keywords") String keywords
    		,@RequestParam("keywords2") String keywords2) {
        int totalCount = schedulingService.getSchedulingCount02(keywords);
        List<Map<String,Object>> list = schedulingService.getSchedulingList03( page, count,keywords,keywords2);
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);//totalCount  20
        map.put("list", list);
        
        return map;
    }
    
    
    /** 
     * 按条件分页查询排班的方法      3号别   根据日期 午别 科室 查号别
     * @param: page当前页   count每页显示记录数量   keywords keywords2 keywords3查询关键字
     * @return: 包含查询结果的Map集合
     */  
    @RequestMapping(value = "/get_regist_level", method = RequestMethod.GET)
    public Map<String, Object> getSchedulingList04( @RequestParam(value = "page", defaultValue = "1") Integer page, 
    		@RequestParam(value = "count", defaultValue = "20") Integer count
    		,@RequestParam("keywords") String keywords,@RequestParam("keywords2") String keywords2
    		,@RequestParam("keywords3") String keywords3) {
        int totalCount = schedulingService.getSchedulingCount02(keywords);
        List<Map<String,Object>> list = schedulingService.getSchedulingList04( page, count,keywords,keywords2,keywords3);
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);//totalCount  20
        map.put("list", list);
        
        return map;
    }
    
    
    /** 
     * 按条件分页查询排班的方法      4医生  根据日期 午别 科室 号别 查医生
     * @param: page当前页   count每页显示记录数量   keywords keywords2 keywords3 keywords4查询关键字
     * @return: 包含查询结果的Map集合
     */  
    @RequestMapping(value = "/get_doc", method = RequestMethod.GET)
    public Map<String, Object> getSchedulingList02( @RequestParam(value = "page", defaultValue = "1") Integer page, 
    		@RequestParam(value = "count", defaultValue = "20") Integer count,@RequestParam("keywords") String keywords
    		,@RequestParam("keywords2") String keywords2,@RequestParam("keywords3") String keywords3
    		,@RequestParam("keywords4") String keywords4) {
        int totalCount = schedulingService.getSchedulingCount02(keywords);
        List<Map<String,Object>> list = schedulingService.getSchedulingList02( page, count,keywords,keywords2,keywords3,keywords4);
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);//totalCount  20
        map.put("list", list);
        
        return map;
    }
    
    
    /**
     * 获取排班医生的 挂号额度
     * @param:    keywords keywords2 keywords3 查询关键字
     * @return: 整型 数量值
     */  
    @RequestMapping(value = "/get_regist_quota", method = RequestMethod.GET)
    public int getSchedulingList06(String keywords,String keywords2,String keywords3) {
        int totalCount = schedulingService.getSchedulingCount06(keywords,keywords2,keywords3);
        
        return totalCount;
    }
    
    /**
     * 获取排班医生的 挂号费金额
     * @param:    keywords keywords2 keywords3 查询关键字
     * @return: 浮点型 数量值
     */  
    @RequestMapping(value = "/get_regist_fee", method = RequestMethod.GET)
    public float getSchedulingList07(String keywords,String keywords2,String keywords3) {
    	float totalCount = schedulingService.getSchedulingCount07(keywords,keywords2,keywords3);
        
        return totalCount;
    }
    
    /** 
     * 根据id号删除排班记录的方法  可以批量删除
     * @param: ids 包含id的字符串
     * @return: 包含响应信息的对象
     */ 
    @RequestMapping(value = "/{ids}", method = RequestMethod.DELETE)
    public RespBean deleteSchedulingById(@PathVariable String ids) {
        boolean result = schedulingService.deleteSchedulingByIds(ids);
        if (result) {
            return new RespBean("success", "删除成功!(排班)");
        }
        return new RespBean("error", "删除失败!(排班)");
    }
    
    /** 
     * 更新排班记录的方法 
     * @param: registLevel 包含更新信息的Map集合
     * @return: 包含响应信息的对象
     */ 
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public RespBean updateScheduling(@RequestParam Map<String,Object> scheduling) {
        int i = schedulingService.updateSchedulingById(scheduling);
        if (i == 1) {
            return new RespBean("success", "修改成功!(排班)");
        }
        return new RespBean("error", "修改失败!(排班)");
    }
    
    /** 
     * 添加排班记录的方法 
     * @param: scheduling 包含科室信息的Map集合
     * @return: 包含响应信息的对象
     */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public RespBean addNewScheduling(@RequestParam Map<String,Object> scheduling) {
        int result = schedulingService.addScheduling(scheduling);
        if (result == 1) {
            return new RespBean("success", "添加成功!(排班)");
        }
        return new RespBean("error", "添加失败!(排班)");
    }
}