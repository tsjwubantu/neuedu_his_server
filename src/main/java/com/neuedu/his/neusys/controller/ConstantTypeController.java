package com.neuedu.his.neusys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.neuedu.his.bean.RespBean;
import com.neuedu.his.neusys.service.ConstantTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/** 
* 常数类型管理专属Controller 
* @auther: 东软教师
* @date:   2019-2-18
*/ 
@RestController
@RequestMapping("/neusys/constantType")
public class ConstantTypeController {
    @Autowired
    ConstantTypeService constantTypeService;

    /** 
    * 查询所有类型的方法   分页显示 
    * @param: page当前页   count每页显示记录数量   keywords查询关键字
    * @return: 包含查询结果的Map集合
    */ 
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Map<String, Object> getConstantTypeList( @RequestParam(value = "page", defaultValue = "1") Integer page, 
    		@RequestParam(value = "count", defaultValue = "10") Integer count,String keywords) {
        int totalCount = constantTypeService.getConstantTypeCount(keywords);
        List<Map<String,Object>> list = constantTypeService.getConstantTypeList( page, count,keywords);
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);
        map.put("list", list);
        
        return map;
    }
    
    /** 
     * 查询所有类型的方法   显示所有的数据   
     * @param: page当前页   count每页显示记录数量   keywords查询关键字
     * @return: 包含查询结果的Map集合
     */ 
    @RequestMapping(value = "/all2", method = RequestMethod.GET)
    public Map<String, Object> getConstantTypeList2( @RequestParam(value = "page", defaultValue = "1") Integer page, 
    		@RequestParam(value = "count", defaultValue = "100") Integer count,String keywords) {
        int totalCount = constantTypeService.getConstantTypeCount(keywords);
        List<Map<String,Object>> list = constantTypeService.getConstantTypeList( page, count,keywords);
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);//totalCount  6
        map.put("list", list);
        
        return map;
    }
    
    /** 
     * 根据id号删除常量类型的方法  可以批量删除
     * @param: ids 包含id的字符串
     * @return: 包含响应信息的对象
     */ 
    @RequestMapping(value = "/{ids}", method = RequestMethod.DELETE)
    public RespBean deleteConstantTypeById(@PathVariable String ids) {
        boolean result = constantTypeService.deleteConstantTypeByIds(ids);
        if (result) {
            return new RespBean("success", "删除成功!");
        }
        return new RespBean("error", "删除失败!");
    }
    
    /** 
     * 更新常量类型的方法 
     * @param: constantType 包含更新信息的Map集合
     * @return: 包含响应信息的对象
     */ 
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public RespBean updateConstantType(@RequestParam Map<String,Object> constantType) {
        int i = constantTypeService.updateConstantTypeById(constantType);
        if (i == 1) {
            return new RespBean("success", "修改成功!");
        }
        return new RespBean("error", "修改失败!");
    }
    
    /** 
     * 添加常量类型的方法 
     * @param: constantType 包含常量类型信息的Map集合
     * @return: 包含响应信息的对象
     */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public RespBean addNewConstantType(@RequestParam Map<String,Object> constantType) {
        int result = constantTypeService.addConstantType(constantType);
        if (result == 1) {
            return new RespBean("success", "添加成功!");
        }
        return new RespBean("error", "添加失败!");
    }
}
