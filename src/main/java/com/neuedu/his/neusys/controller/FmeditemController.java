package com.neuedu.his.neusys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.neuedu.his.bean.RespBean;
import com.neuedu.his.neusys.service.FmeditemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** 
* 非药品收费项目专属Controller 
* @auther: 东软教师
* @date:   2019-2-18
*/ 
@RestController
@RequestMapping("/neusys/fmeditem")
public class FmeditemController {
    @Autowired
    FmeditemService fmeditemService;

    /** 
     * 按条件分页查询非药品收费项目的方法 
     * @param: page当前页   count每页显示记录数量   keywords查询关键字
     * @return: 包含查询结果的Map集合
     */ 
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Map<String, Object> getFmeditemList( @RequestParam(value = "page", defaultValue = "1") Integer page, 
    		@RequestParam(value = "count", defaultValue = "10") Integer count,String keywords) {
        int totalCount = fmeditemService.getFmeditemCount(keywords);
        List<Map<String,Object>> list = fmeditemService.getFmeditemList( page, count,keywords);
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);//totalCount  6
        map.put("list", list);
        
        return map;
    }
    
    /** 
     * 根据id号删除非药品收费项目的方法  可以批量删除
     * @param: ids 包含id的字符串
     * @return: 包含响应信息的对象
     */ 
    @RequestMapping(value = "/{ids}", method = RequestMethod.DELETE)
    public RespBean deleteFmeditemById(@PathVariable String ids) {
        boolean result = fmeditemService.deleteFmeditemByIds(ids);
        if (result) {
            return new RespBean("success", "删除成功!(非药品收费项目)");
        }
        return new RespBean("error", "删除失败!(非药品收费项目)");
    }
    
    /** 
     * 更新非药品收费项目的方法 
     * @param: expenseClass 包含更新信息的Map集合
     * @return: 包含响应信息的对象
     */ 
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public RespBean updateFmeditem(@RequestParam Map<String,Object> fmeditem) {
        int i = fmeditemService.updateFmeditemById(fmeditem);
        if (i == 1) {
            return new RespBean("success", "修改成功!(非药品收费项目)");
        }
        return new RespBean("error", "修改失败!(非药品收费项目)");
    }
    
    /** 
     * 添加非药品收费项目的方法 
     * @param: fmeditem 包含科室信息的Map集合
     * @return: 包含响应信息的对象
     */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public RespBean addNewFmeditem(@RequestParam Map<String,Object> fmeditem) {
        int result = fmeditemService.addFmeditem(fmeditem);
        if (result == 1) {
            return new RespBean("success", "添加成功!(非药品收费项目)");
        }
        return new RespBean("error", "添加失败!(非药品收费项目)");
    }
}
