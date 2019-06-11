package com.neuedu.his.neusys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.neuedu.his.bean.RespBean;
import com.neuedu.his.neusys.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** 
* 科室管理专属Controller 
* @auther: 东软教师
* @date:   2019-2-18
*/ 
@RestController
@RequestMapping("/neusys/department")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    /** 
     * 按条件分页查询科室的方法 
     * @param: page当前页   count每页显示记录数量   keywords查询关键字
     * @return: 包含查询结果的Map集合
     */ 
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Map<String, Object> getDepartmentList( @RequestParam(value = "page", defaultValue = "1") Integer page, 
    		@RequestParam(value = "count", defaultValue = "10") Integer count,String keywords) {
        int totalCount = departmentService.getDepartmentCount(keywords);
        List<Map<String,Object>> list = departmentService.getDepartmentList( page, count,keywords);
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);//totalCount  6
        map.put("list", list);
        
        return map;
    }
    
    /** 
     * 根据id号删除科室的方法  可以批量删除
     * @param: ids 包含id的字符串
     * @return: 包含响应信息的对象
     */ 
    @RequestMapping(value = "/{ids}", method = RequestMethod.DELETE)
    public RespBean deleteDepartmentById(@PathVariable String ids) {
        boolean result = departmentService.deleteDepartmentByIds(ids);
        if (result) {
            return new RespBean("success", "删除成功!(科室)");
        }
        return new RespBean("error", "删除失败!(科室)");
    }
    
    /** 
     * 更新科室信息的方法 
     * @param: department 包含更新信息的Map集合
     * @return: 包含响应信息的对象
     */ 
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public RespBean updateDepartment(@RequestParam Map<String,Object> department) {
        int i = departmentService.updateDepartmentById(department);
        if (i == 1) {
            return new RespBean("success", "修改成功!(科室)");
        }
        return new RespBean("error", "修改失败!(科室)");
    }
    
    /** 
     * 添加科室的方法 
     * @param: department 包含科室信息的Map集合
     * @return: 包含响应信息的对象
     */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public RespBean addNewDepartment(@RequestParam Map<String,Object> department) {
        int result = departmentService.addDepartment(department);
        if (result == 1) {
            return new RespBean("success", "添加成功!(科室)");
        }
        return new RespBean("error", "添加失败!(科室)");
    }
}
