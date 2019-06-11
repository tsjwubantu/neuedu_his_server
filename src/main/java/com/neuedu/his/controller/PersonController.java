package com.neuedu.his.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neuedu.his.bean.Person;
import com.neuedu.his.bean.RespBean;
import com.neuedu.his.service.PersonService;

/** 
* 人员信息数据专属Controller 
* @auther: 东软教师
* @date:   2018-11-29
*/ 
@RestController
@RequestMapping("/gj_admin/person")
public class PersonController {
    @Autowired
    PersonService personService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Map<String,Object>> getAllPerson() {
        return personService.getAllPerson();
    }
    
    
    @RequestMapping(value = "/all2", method = RequestMethod.GET)
    public Map<String, Object> getPersonList( @RequestParam(value = "page", defaultValue = "1") Integer page, 
    		@RequestParam(value = "count", defaultValue = "6") Integer count,String keywords ,String keywords2) {
        int totalCount = personService.getPersonCount( keywords,keywords2);
        List<Person> list = personService.getPersonList( page, count,keywords,keywords2);
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);//totalCount  6
        map.put("list", list);
        return map;
    }

    @RequestMapping(value = "/{ids}", method = RequestMethod.DELETE)
    public RespBean deleteById(@PathVariable String ids) {
        boolean result = personService.deletePersonByIds(ids);
        if (result) {
            return new RespBean("success", "删除成功!");
        }
        return new RespBean("error", "删除失败!");
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public RespBean addNewCate(Person person) {
        int result = personService.addPerson(person);
        if (result == 1) {
            return new RespBean("success", "添加成功!(人员信息)");
        }
        return new RespBean("error", "添加失败!");
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public RespBean updateCate(Person category) {
        int i = personService.updatePersonById(category);
        if (i == 1) {
            return new RespBean("success", "修改成功!");
        }
        return new RespBean("error", "修改失败!");
    }
}
