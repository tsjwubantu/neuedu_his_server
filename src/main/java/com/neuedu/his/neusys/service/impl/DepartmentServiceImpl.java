package com.neuedu.his.neusys.service.impl;

import java.util.List;
import java.util.Map;

import com.neuedu.his.neusys.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.neuedu.his.mapper.DepartmentMapper;


/** 
* 科室管理专属Service
* @auther: 东软教师
* @date:   2019-3-12
*/ 
@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    DepartmentMapper departmentMapper;
    
    /** 
     * 按条件分页查询科室的方法 
     * @param: page当前页   count每页显示记录数量   keywords查询关键字
     * @return: 包含查询结果的Map集合
     */ 
    public List<Map<String,Object>> getDepartmentList( Integer page, Integer count,String keywords) {
         int start = (page - 1) * count;
         return departmentMapper.getDepartmentList(start, count, keywords);
     }

    
     public int getDepartmentCount( String keywords) {
         return departmentMapper.getDepartmentCount(keywords);
     }
     
     /** 
      * 根据id号删除科室的方法  可以批量删除
      * @param: ids 包含id的字符串
      * @return: 成功返回为真  失败返回为假
      */ 
      public boolean deleteDepartmentByIds(String ids) {
         String[] split = ids.split(",");
         int result = departmentMapper.deleteDepartmentByIds(split);
         return result == split.length;
     }

      /** 
       * 更新科室信息的方法 
       * @param: department 包含更新信息的Map集合
       * @return: 成功返回1  失败返回0
       */ 
     public int updateDepartmentById(Map<String,Object> department) {
          return departmentMapper.updateDepartmentById(department);
     }
     
     /** 
      * 添加科室的方法 
      * @param: department 包含科室信息的Map集合
      * @return: 成功返回1  失败返回0
      */ 
      public int addDepartment(Map<String,Object> department) {
         return departmentMapper.addDepartment(department);
     }

     
}