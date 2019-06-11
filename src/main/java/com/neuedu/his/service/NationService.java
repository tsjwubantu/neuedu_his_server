package com.neuedu.his.service;

import java.util.List;
import java.util.Map;

import com.neuedu.his.mapper.NationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** 
* 民族数据维护 
* @auther: 东软教师
* @date:   2018-11-29
*/ 
@Service
@Transactional
public class NationService {
    @Autowired
    NationMapper nationMapper;

    /** 
    * 获取所有的民族信息 
    * @return 民族信息对象列表 
    * @auther: 东软教师
    * @date:   2018-11-7
    */ 
    public List<Map<String,Object>> getAllNation() {
        return nationMapper.getAllNation();
    }    
}
