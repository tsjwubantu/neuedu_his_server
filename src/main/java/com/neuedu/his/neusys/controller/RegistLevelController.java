package com.neuedu.his.neusys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.neuedu.his.bean.RespBean;
import com.neuedu.his.neusys.service.RegistLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/** 
* 挂号等级属Controller 
* @auther: 东软教师
* @date:   2019-2-18
*/ 
@RestController
@RequestMapping("/neusys/registLevel")
public class RegistLevelController {
    @Autowired
    RegistLevelService registLevelService;

    /** 
     * 按条件分页查询挂号等级的方法   
     * @param: page当前页   count每页显示记录数量   keywords 查询关键字
     * @return: 包含查询结果的Map集合
     */ 
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Map<String, Object> getRegistLevelList( @RequestParam(value = "page", defaultValue = "1") Integer page, 
    		@RequestParam(value = "count", defaultValue = "6") Integer count,String keywords) {
        int totalCount = registLevelService.getRegistLevelCount(keywords);
        List<Map<String,Object>> list = registLevelService.getRegistLevelList( page, count,keywords);
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);//totalCount  6
        map.put("list", list);
        
        return map;
    }
    
    /** 
     * 根据id号删除挂号等级的方法  可以批量删除
     * @param: ids 包含id的字符串
     * @return: 包含响应信息的对象
     */ 
    @RequestMapping(value = "/{ids}", method = RequestMethod.DELETE)
    public RespBean deleteRegistLevelById(@PathVariable String ids) {
        boolean result = registLevelService.deleteRegistLevelByIds(ids);
        if (result) {
            return new RespBean("success", "删除成功!(挂号级别)");
        }
        return new RespBean("error", "删除失败!(挂号级别)");
    }
    
    /** 
     * 更新挂号等级的方法 
     * @param: registLevel 包含更新信息的Map集合
     * @return: 包含响应信息的对象
     */ 
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public RespBean updateRegistLevel(@RequestParam Map<String,Object> registLevel) {
        int i = registLevelService.updateRegistLevelById(registLevel);
        if (i == 1) {
            return new RespBean("success", "修改成功!(挂号级别)");
        }
        return new RespBean("error", "修改失败!(挂号级别)");
    }
    
    /** 
     * 添加挂号等级的方法 
     * @param: registLevel 包含科室信息的Map集合
     * @return: 包含响应信息的对象
     */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public RespBean addNewRegistLevel(@RequestParam Map<String,Object> registLevel) {
        int result = registLevelService.addRegistLevel(registLevel);
        if (result == 1) {
            return new RespBean("success", "添加成功!(挂号级别)");
        }
        return new RespBean("error", "添加失败!(挂号级别)");
    }
}
