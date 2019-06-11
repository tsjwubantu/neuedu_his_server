package com.neuedu.his.neusys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.neuedu.his.bean.RespBean;
import com.neuedu.his.neusys.service.SettleCategoryService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/** 
* 结算类别专属Controller 
* @auther: 东软教师
* @date:   2019-2-18
*/ 
@RestController
@RequestMapping("/neusys/settleCategory")
public class SettleCategoryController {
    @Autowired
    SettleCategoryService settleCategoryService;
    
    /** 
     * 查询结算类别表SettleCategory的记录，无分页  
     * @param: keywords:  类别编码 或者 类别名称
     * @return: 包含查询结果的RespBean对象
     */ 
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public RespBean getSettleCategory(@Param("keywords")String keywords ) {
        List<Map<String, Object>> list = settleCategoryService.getSettleCategoryList(keywords);
        RespBean bean = new RespBean();
        bean.setList(list);        
        return bean;
    }

    
//版本2---------------------------------------------------------------------------------------------}    
    /** 
     * 按条件分页查询结算类别的方法   
     * @param: page当前页   count每页显示记录数量   keywords 查询关键字
     * @return: 包含查询结果的Map集合
     */  
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Map<String, Object> getSettleCategoryList( @RequestParam(value = "page", defaultValue = "1") Integer page, 
    		@RequestParam(value = "count", defaultValue = "10") Integer count,String keywords) {
        int totalCount = settleCategoryService.getSettleCategoryCount(keywords);
        List<Map<String,Object>> list = settleCategoryService.getSettleCategoryList( page, count,keywords);
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);//totalCount  6
        map.put("list", list);
        
        return map;
    }
    
    /** 
     * 根据id号删除结算类别的方法  可以批量删除
     * @param: ids 包含id的字符串
     * @return: 包含响应信息的对象
     */ 
    @RequestMapping(value = "/{ids}", method = RequestMethod.DELETE)
    public RespBean deleteSettleCategoryById(@PathVariable String ids) {
        boolean result = settleCategoryService.deleteSettleCategoryByIds(ids);
        if (result) {
            return new RespBean("success", "删除成功!(结算类别)");
        }
        return new RespBean("error", "删除失败!(结算类别)");
    }
    
    /** 
     * 更新结算类别的方法 
     * @param: settleCategory 包含更新信息的Map集合
     * @return: 包含响应信息的对象
     */ 
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public RespBean updateSettleCategory(@RequestParam Map<String,Object> settleCategory) {
        int i = settleCategoryService.updateSettleCategoryById(settleCategory);
        if (i == 1) {
            return new RespBean("success", "修改成功!(结算类别)");
        }
        return new RespBean("error", "修改失败!(结算类别)");
    }
    
    /** 
     * 添加结算类别的方法 
     * @param: settleCategory 包含科室信息的Map集合
     * @return: 包含响应信息的对象
     */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public RespBean addNewSettleCategory(@RequestParam Map<String,Object> settleCategory) {
        int result = settleCategoryService.addSettleCategory(settleCategory);
        if (result == 1) {
            return new RespBean("success", "添加成功!(结算类别)");
        }
        return new RespBean("error", "添加失败!(结算类别)");
    }
}
