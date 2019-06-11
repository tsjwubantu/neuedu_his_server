package com.neuedu.his.neudoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neuedu.his.bean.RespBean;
import com.neuedu.his.neudoc.service.DiseaseService;

/** 
* 操作疾病Controller 
* @auther: 东软教师
* @date:   2018-11-29
*/ 
@RestController
@RequestMapping("/neudoc/disease")
public class DiseaseController {
    @Autowired
    private DiseaseService diseaseService;
    @RequestMapping(value = "/listPage", method = RequestMethod.GET)
    /**
     * 分页查询疾病信息
     */
    public RespBean getDiseasesPage( @RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber, 
    		@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,String keywords01 ) {
        RespBean resp = diseaseService.getDiseaseByType(pageNumber, pageSize,keywords01);       
        return resp;
    }
    /**
     * 根据疾病“名称”或者“ICD编码”查询疾病信息
     */
    @RequestMapping(value = "/getPage", method = RequestMethod.GET)
    public RespBean getDiseaseByNameOrIcd( String DicaType,String keywords01 ) {
        RespBean resp = diseaseService.getDiseaseByNameOrIcd(DicaType,keywords01);       
        return resp;
    }
}
