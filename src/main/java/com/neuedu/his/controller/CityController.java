package com.neuedu.his.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.neuedu.his.service.CityService;

/** 
* 城市数据专属Controller 
* @auther: 东软教师
* @date:   2018-11-29
*/ 
@RestController
@RequestMapping("/gj_admin/city")
public class CityController {
    @Autowired
    CityService cityService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Map<String,Object>> getAllCity() {
        return cityService.getAllCity();
    }   
}
