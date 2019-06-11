package com.neuedu.his.neusys.service.impl;

import java.util.List;
import java.util.Map;

import com.neuedu.his.neusys.service.ConstantItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neuedu.his.mapper.ConstantitemMapper;

/** 
* 常数项内容管理专属Service 
* @auther: 东软教师
* @date:   2019-3-13
*/ 
@Service
@Transactional
public class ConstantItemServiceImpl implements ConstantItemService {
    
	@Autowired
    ConstantitemMapper constantitemMapper;
	
	/** 
     * 获取常数项内容数据列表的方法 
     * @param page当前页   count每页显示记录数量   keywords 查询关键字1 keywords2 查询关键字2
     * @return 包含常数项内容对象的列表 
     * @auther: 东软教师
     * @date:   2019-2-27
     */ 
    public List<Map<String,Object>> getConstantItemList( Integer page, Integer count,String keywords,String keywords2) {
         int start = (page - 1) * count;
         return constantitemMapper.getConstantItemList(start, count, keywords, keywords2);
    }
    
    /** 
     * 获取常数项内容数据列表的方法 
     * @param  keywords 查询关键字1
     * @return 包含常数项内容对象的列表 
     * @auther: 东软教师
     * @date:   2019-2-27
     */ 
    public List<Map<String,Object>> getConstantItemList2(String keywords) {
        //int start = (page - 1) * count;
        return constantitemMapper.getConstantItemList2(keywords);
    }

    /** 
     * 获取符合条件的常数项内容数量
     * @param  keywords 查询关键字1  keywords2 查询关键字2 
     * @return 常数项内容数量 
     * @auther: 东软教师
     * @date:   2019-2-27
     */ 
     public int getConstantItemCount( String keywords, String keywords2) {
         return constantitemMapper.getConstantItemCount(keywords, keywords2);
     }
     
     /** 
      * 获取符合条件的常数项内容数量
      * @param  keywords 查询关键字1
      * @return 常数项内容数量 
      * @auther: 东软教师
      * @date:   2019-2-27
      */ 
     public int getConstantItemCount2( String keywords) {
         return constantitemMapper.getConstantItemCount2(keywords);
     }
     
     /** 
      * 删除常数项内容的方法，可以批量删除
      * @param 待删除的 id (1-n个) 
      * @return 是否删除成功的布尔值 
      * @auther: 东软教师
      * @date:   2019-2-27
      */ 
      public boolean deleteConstantItemByIds(String ids) {
         String[] split = ids.split(",");
         int result = constantitemMapper.deleteConstantItemByIds(split);
         return result == split.length;
     }

      /** 
       * 更新常量项内容的方法
       * @param constant 包含更新信息的Map集合
       * @return 是否更新成功的布尔值 
       * @auther: 东软教师
       * @date:   2019-2-27
       */  
     public int updateConstantItemById(Map<String,Object> constant) {
          return constantitemMapper.updateConstantItemById(constant);
     }
     
     /** 
      * 添加常量项内容的方法
      * @param constant 包含常量项内容信息的Map集合 
      * @return 1添加成功  0添加失败 
      * @auther: 东软教师
      * @date:   2019-2-27
      */ 
      public int addConstantItem(Map<String,Object> constant) {
         return constantitemMapper.addConstantItem(constant);
     }
	
	//li-----------------------------------------------------------------------------{
	@Override
  	public List<Map<String,Object>> getConstantItemList(String constantTypeCode) {
           return constantitemMapper.getConstantItemListByType(constantTypeCode);
	}
      
	@Override
  	public List<Map<String,Object>> getConstantItemList() {
           return constantitemMapper.getConstantItem();
	}
}