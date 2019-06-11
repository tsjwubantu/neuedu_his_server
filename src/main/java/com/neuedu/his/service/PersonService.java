package com.neuedu.his.service;

import java.util.List;
import java.util.Map;

import com.neuedu.his.bean.Person;
import com.neuedu.his.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** 
* 人员信息维护 
* @auther: 东软教师
* @date:   2018-11-29
*/ 
@Service
@Transactional
public class PersonService {
    @Autowired
    PersonMapper personMapper;
    
    /** 
    * 获取人员信息数据列表 
    * @param 当前页 总页数  姓名条件  身份证条件
    * @return 人员信息对象列表 
    * @auther: 东软教师
    * @date:   2018-11-7
    */ 
     public List<Person> getPersonList(Integer page, Integer count, String keywords , String keywords2) {
         int start = (page - 1) * count;
         return personMapper.getPersonList(start, count, keywords,keywords2);
     }


     /** 
      * 获取符合条件的人员数量
      * @param 用户id 姓名条件  身份证条件 
      * @return 人员数量 
      * @auther: 东软教师
      * @date:   2018-11-7
      */ 
     public int getPersonCount( String keywords,String keywords2) {
         return personMapper.getPersonCount(keywords,keywords2);
     }
    
     /** 
      * 获取所有的人员信息 
      * @return 人员信息对象列表 
      * @auther: 东软教师
      * @date:   2018-11-7
      */ 
     public List<Map<String,Object>> getAllPerson() {
        return personMapper.getAllPerson();
     }

    
    /** 
     * 删除人员信息，可以批量删除
     * @param 待删除的 id (1-n个) 
     * @return 是否删除成功的布尔值 
     * @auther: 东软教师
     * @date:   2018-11-7
     */ 
     public boolean deletePersonByIds(String ids) {
        String[] split = ids.split(",");
        int result = personMapper.deletePersonByIds(split);
        return result == split.length;
    }

    /** 
     * 更新人员信息
     * @param 人员对象 
     * @return 1更新成功  0更新失败 
     * @auther: 东软教师
     * @date:   2018-11-7
     */ 
    public int updatePersonById(Person person) {
        return personMapper.updatePersonById(person);
    }

    /** 
     * 添加人员信息
     * @param 人员对象 
     * @return 1添加成功  0添加失败 
     * @auther: 东软教师
     * @date:   2018-11-7
     */ 
    public int addPerson(Person person) {
        return personMapper.addPerson(person);
    }
}
