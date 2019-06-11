package com.neuedu.his.neusys.service.impl;

import java.util.List;
import java.util.Map;

import com.neuedu.his.neusys.service.DiseCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.neuedu.his.mapper.DisecategoryMapper;


/** 
* 人员信息维护 
* @auther: 东软教师
* @date:   2018-11-29
*/ 
@Service
@Transactional
public class DiseCategoryServiceImpl implements DiseCategoryService {
    @Autowired
    DisecategoryMapper diseCategoryMapper;
    
    /** 
    * 获取人员信息数据列表 
    * @param 当前页 总页数  姓名条件  身份证条件
    * @return 人员信息对象列表 
    * @auther: 东软教师
    * @date:   2018-11-7
    */ 
    public List<Map<String,Object>> getDiseCategoryList( Integer page, Integer count,String keywords) {
         int start = (page - 1) * count;
         return diseCategoryMapper.getDiseCategoryList(start, count, keywords);
     }

    
    /** 
     * 获取符合条件的人员数量
     * @param 用户id 姓名条件  身份证条件 
     * @return 人员数量 
     * @auther: 东软教师
     * @date:   2018-11-7
     */ 
     public int getDiseCategoryCount( String keywords) {
         return diseCategoryMapper.getDiseCategoryCount(keywords);
     }

     /** 
      * 删除人员信息，可以批量删除
      * @param 待删除的 id (1-n个) 
      * @return 是否删除成功的布尔值 
      * @auther: 东软教师
      * @date:   2018-11-7
      */ 
      public boolean deleteDiseCategoryByIds(String ids) {
         String[] split = ids.split(",");
         int result = diseCategoryMapper.deleteDiseCategoryByIds(split);
         return result == split.length;
     }

     public int updateDiseCategoryById(Map<String,Object> diseCategory) {
          return diseCategoryMapper.updateDiseCategoryById(diseCategory);
     }
     
     /** 
      * 添加人员信息
      * @param 人员对象 
      * @return 1添加成功  0添加失败 
      * @auther: 东软教师
      * @date:   2018-11-7
      */ 
      public int addDiseCategory(Map<String,Object> diseCategory) {
         return diseCategoryMapper.addDiseCategory(diseCategory);
     }
}