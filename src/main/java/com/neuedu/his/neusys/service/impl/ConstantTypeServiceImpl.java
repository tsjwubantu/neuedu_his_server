package com.neuedu.his.neusys.service.impl;

import java.util.List;
import java.util.Map;

import com.neuedu.his.neusys.service.ConstantTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neuedu.his.mapper.ConstanttypeMapper;


//ConstantTypeService

/** 
* 常数类型管理专属Service 
* @auther: 东软教师
* @date:   2019-2-27
*/ 
@Service
@Transactional
public class ConstantTypeServiceImpl implements ConstantTypeService {
    @Autowired
    ConstanttypeMapper constantTypeMapper;
    
    /** 
    * 获取常数类型数据列表的方法 
    * @param page当前页   count每页显示记录数量   keywords查询关键字
    * @return 包含常数类型对象的列表 
    * @auther: 东软教师
    * @date:   2019-2-27
    */ 
    public List<Map<String,Object>> getConstantTypeList( Integer page, Integer count,String keywords) {
         int start = (page - 1) * count;
         return constantTypeMapper.getConstantTypeList(start, count, keywords);
     }

    
    /** 
     * 获取符合条件的常数类型数量的方法
     * @param keywords查询关键字 
     * @return  数量值 
     * @auther: 东软教师
     * @date:   2019-2-27
     */ 
     public int getConstantTypeCount( String keywords) {
         return constantTypeMapper.getConstantTypeCount(keywords);
     }
     
     /** 
      * 删除常数类型的方法，可以批量删除
      * @param 待删除的ids 字符串 (1-n个id) 
      * @return 是否删除成功的布尔值 
      * @auther: 东软教师
      * @date:   2019-2-27
      */ 
      public boolean deleteConstantTypeByIds(String ids) {
         String[] split = ids.split(",");
         int result = constantTypeMapper.deleteConstantTypeByIds(split);
         return result == split.length;
     }

      /** 
       * 更新常量类型的方法
       * @param constantType 包含更新信息的Map集合
       * @return 是否更新成功的布尔值 
       * @auther: 东软教师
       * @date:   2019-2-27
       */  
     public int updateConstantTypeById(Map<String,Object> constant) {
          return constantTypeMapper.updateConstantTypeById(constant);
     }
     
     /** 
      * 添加常量类型的方法
      * @param constantType 包含常量类型信息的Map集合 
      * @return 1添加成功  0添加失败 
      * @auther: 东软教师
      * @date:   2019-2-27
      */ 
      public int addConstantType(Map<String,Object> constantType) {
         return constantTypeMapper.addConstantType(constantType);
     }
}