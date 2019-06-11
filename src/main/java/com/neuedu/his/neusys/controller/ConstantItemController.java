package com.neuedu.his.neusys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.neuedu.his.bean.RespBean;
import com.neuedu.his.neusys.service.ConstantItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** 
* 常数内容管理专属Controller 
* @auther: 东软教师
* @date:   2019-2-18
*/ 
@RestController
@RequestMapping("/neusys/constantItem")
public class ConstantItemController {
    @Autowired
    ConstantItemService constatntItemService;
    
  //li--------------------------------------------------------------------{
    /** 
     * 根据常数类别表ConstantType中，常数类别编码ConstantTypeCode，查询符合条件的常数项表ConstantItem的记录，无分页  
     * @param: 常数类别编码ConstantTypeCode
     * @return: 包含查询结果的RespBean对象
     */ 
    @RequestMapping(value = "/listByType", method = RequestMethod.GET)
    public RespBean getConstantItemList(@RequestParam(value = "keywords") String keywords) {
        List<Map<String, Object>> list = constatntItemService.getConstantItemList(keywords);
        RespBean bean = new RespBean();
        bean.setList(list);        
        return bean;
    }
    
    /** 
     * 查询常数项表ConstantItem的记录，无分页  
     * @param: 常数类别编码ConstantTypeCode
     * @return: 包含查询结果的RespBean对象
     */ 
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public RespBean getConstantItemList( ) {
        List<Map<String, Object>> list = constatntItemService.getConstantItemList();
        RespBean bean = new RespBean();
        bean.setList(list);        
        return bean;
    }
    
  //li----------------------------------------------------------------------------------}
    
    /** 
     * 查询所有常量的方法   分页显示 
     * @param: page当前页   count每页显示记录数量   keywords查询关键字1(编码或名称)  keywords2查询关键字2(类型)
     * @return: 包含查询结果的Map集合
     */ 
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Map<String, Object> getConstantItemList( @RequestParam(value = "page", defaultValue = "1") Integer page, 
    		@RequestParam(value = "count", defaultValue = "6") Integer count,@RequestParam(value = "keywords") String keywords,@RequestParam(value = "keywords2")String keywords2) {
        int totalCount = constatntItemService.getConstantItemCount(keywords, keywords2);
        List<Map<String,Object>> list = constatntItemService.getConstantItemList( page, count,keywords,keywords2);
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);
        map.put("list", list);
        
        return map;
    }
    
    /** 
     * 查询所有常量的方法   显示所有的数据  
     * @param: page当前页   count每页显示记录数量  keywords查询关键字1(编码或名称)
     * @return: 包含查询结果的Map集合
     */ 
    @RequestMapping(value = "/all2", method = RequestMethod.GET)
    public Map<String, Object> getConstantItemList2(@RequestParam(value = "keywords") String keywords) {
        int totalCount = constatntItemService.getConstantItemCount2(keywords);
        List<Map<String,Object>> list = constatntItemService.getConstantItemList2(keywords);
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);
        map.put("list", list);
        
        return map;
    }
    
    /** 
     * 根据id号删除常量的方法  可以批量删除
     * @param: ids 包含id的字符串
     * @return: 包含响应信息的对象
     */ 
    @RequestMapping(value = "/{ids}", method = RequestMethod.DELETE)
    public RespBean deleteConstantItemById(@PathVariable String ids) {
        boolean result = constatntItemService.deleteConstantItemByIds(ids);
        if (result) {
            return new RespBean("success", "删除成功!");
        }
        return new RespBean("error", "删除失败!");
    }
    
    /** 
     * 更新常量的方法 
     * @param: constantType 包含更新信息的Map集合
     * @return: 包含响应信息的对象
     */ 
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public RespBean updateConstantItem(@RequestParam Map<String,Object> constantItem) {
        int i = constatntItemService.updateConstantItemById(constantItem);
        if (i == 1) {
            return new RespBean("success", "修改成功!");
        }
        return new RespBean("error", "修改失败!");
    }
    
    /** 
     * 添加常量的方法 
     * @param: constantType 包含常量类型信息的Map集合
     * @return: 包含响应信息的对象
     */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public RespBean addNewConstantItem(@RequestParam Map<String,Object> constantItem) {
        int result = constatntItemService.addConstantItem(constantItem);
        if (result == 1) {
            return new RespBean("success", "添加成功!");
        }
        return new RespBean("error", "添加失败!");
    }
}
