package com.neuedu.his.neusys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.neuedu.his.bean.RespBean;
import com.neuedu.his.neusys.service.DiseaseSYSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/** 
* 疾病管理专属Controller 
* @auther: 东软教师
* @date:   2019-2-18
*/ 
@RestController
@RequestMapping("/neusys/disease")
public class DiseaseSYSController {
    @Autowired
    DiseaseSYSService diseaseService;

    /** 
     * 按条件分页查询疾病的方法 
     * @param: page当前页   count每页显示记录数量   keywords查询关键字
     * @return: 包含查询结果的Map集合
     */ 
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Map<String, Object> getDiseaseList( @RequestParam(value = "page", defaultValue = "1") Integer page, 
    		@RequestParam(value = "count", defaultValue = "10") Integer count,String keywords) {
        int totalCount = diseaseService.getDiseaseCount(keywords);
        List<Map<String,Object>> list = diseaseService.getDiseaseList( page, count,keywords);
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);
        map.put("list", list);
        
        return map;
    }
    
    /** 
     * 根据id号删除疾病的方法  可以批量删除
     * @param: ids 包含id的字符串
     * @return: 包含响应信息的对象
     */ 
    @RequestMapping(value = "/{ids}", method = RequestMethod.DELETE)
    public RespBean deleteDiseaseById(@PathVariable String ids) {
        boolean result = diseaseService.deleteDiseaseByIds(ids);
        if (result) {
            return new RespBean("success", "删除成功!(疾病)");
        }
        return new RespBean("error", "删除失败!(疾病)");
    }
    
    /** 
     * 更新疾病信息的方法 
     * @param: disease 包含更新信息的Map集合
     * @return: 包含响应信息的对象
     */ 
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public RespBean updateDepartment(@RequestParam Map<String,Object> disease) {
        int i = diseaseService.updateDiseaseById(disease);
        if (i == 1) {
            return new RespBean("success", "修改成功!(疾病)");
        }
        return new RespBean("error", "修改失败!(疾病)");
    }
    
    /** 
     * 添加疾病的方法 
     * @param: disease 包含科室信息的Map集合
     * @return: 包含响应信息的对象
     */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public RespBean addNewDepartment(@RequestParam Map<String,Object> disease) {
        int result = diseaseService.addDisease(disease);
        if (result == 1) {
            return new RespBean("success", "添加成功!(疾病)");
        }
        return new RespBean("error", "添加失败!(疾病)");
    }
}
