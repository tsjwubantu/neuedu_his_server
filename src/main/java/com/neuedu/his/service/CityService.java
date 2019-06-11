package com.neuedu.his.service;

import java.util.List;
import java.util.Map;

import com.neuedu.his.mapper.CityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** 
* 城市数据维护 
* @auther: 东软教师
* @date:   2018-11-29
*/ 
@Service
@Transactional
public class CityService {
    @Autowired
    CityMapper cityMapper;

    /** 
     * 获取所有的城市信息 
     * @return 城市信息对象列表 
     * @auther: 东软教师
     * @date:   2018-11-7
     */ 
    public List<Map<String,Object>> getAllCity() {
        return cityMapper.getAllCity();
    }

}
