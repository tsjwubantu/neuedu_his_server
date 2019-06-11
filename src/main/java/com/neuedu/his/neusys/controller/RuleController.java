package com.neuedu.his.neusys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.neuedu.his.bean.RespBean;
import com.neuedu.his.neusys.service.RuleService;


/** 
* 规则管理专属Controller 
* @auther: 东软教师
* @date:   2019-2-18
*/ 
@RestController
@RequestMapping("/neusys/rule")
public class RuleController {
    @Autowired
    RuleService ruleService;

    /** 
     * 按条件分页查询规则的方法   
     * @param: page当前页   count每页显示记录数量   keywords 查询关键字
     * @return: 包含查询结果的Map集合
     */  
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Map<String, Object> getRuleList( @RequestParam(value = "page", defaultValue = "1") Integer page, 
    		@RequestParam(value = "count", defaultValue = "6") Integer count,String keywords) {
        int totalCount = ruleService.getRuleCount(keywords);
        List<Map<String,Object>> list = ruleService.getRuleList( page, count,keywords);
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);//totalCount  6
        map.put("list", list);
        
        return map;
    }
    
    /** 
     * 根据id号删除规则的方法  可以批量删除
     * @param: ids 包含id的字符串
     * @return: 包含响应信息的对象
     */ 
    @RequestMapping(value = "/{ids}", method = RequestMethod.DELETE)
    public RespBean deleteRuleById(@PathVariable String ids) {
        boolean result = ruleService.deleteRuleByIds(ids);
        if (result) {
            return new RespBean("success", "删除成功!(排班规则)");
        }
        return new RespBean("error", "删除失败!(排班规则)");
    }
    
    /** 
     * 更新规则的方法 
     * @param: registLevel 包含更新信息的Map集合
     * @return: 包含响应信息的对象
     */ 
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public RespBean updateRule(@RequestParam Map<String,Object> rule) {
        int i = ruleService.updateRuleById(rule);
        if (i == 1) {
            return new RespBean("success", "修改成功!(排班规则)");
        }
        return new RespBean("error", "修改失败!(排班规则)");
    }
    
    /** 
     * 添加规则的方法 
     * @param: registLevel 包含科室信息的Map集合
     * @return: 包含响应信息的对象
     */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public RespBean addNewRule(@RequestParam Map<String,Object> rule) {
        int result = ruleService.addRule(rule);
        if (result == 1) {
            return new RespBean("success", "添加成功!(排班规则)");
        }
        return new RespBean("error", "添加失败!(排班规则)");
    }
}
