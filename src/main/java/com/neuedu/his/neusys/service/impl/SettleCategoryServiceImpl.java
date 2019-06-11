package com.neuedu.his.neusys.service.impl;

import java.util.List;
import java.util.Map;

import com.neuedu.his.neusys.service.SettleCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.neuedu.his.mapper.SettleCategoryMapper;

/** 
* 结算类别专属Service 
* @auther: 东软教师
* @date:   2019-2-18
*/ 
@Service
@Transactional
public class SettleCategoryServiceImpl implements SettleCategoryService {
    @Autowired
    SettleCategoryMapper settleCategoryMapper;
    
    /** 
     * 根据 类别编码 或者 类别名称 查询结算类别表SettleCategory的记录，无分页
     * @param: keywords查询关键字
     * @return: 包含查询结果的Map集合
     */ 
    @Override
	public List<Map<String, Object>> getSettleCategoryList(String keywords) {
    	return settleCategoryMapper.getSettleCategoryList(keywords);
	}
    
   //上面 李用------------------------------------------------------------------------------------}
    
    /** 
     * 按条件分页查询结算类别记录的方法 
     * @param: page当前页   count每页显示记录数量   keywords查询关键字
     * @return: 包含查询结果的Map集合
     */ 
    public List<Map<String,Object>> getSettleCategoryList( Integer page, Integer count,String keywords) {
         int start = (page - 1) * count;
         return settleCategoryMapper.getSettleCategoryList(start, count, keywords);
     }

    /** 
     * 按条件统计记录数量  确定页数最大值时用的
     * @param keywords 查询关键字
     * @return 记录数量  整型
     */ 
     public int getSettleCategoryCount( String keywords) {
         return settleCategoryMapper.getSettleCategoryCount(keywords);
     }

    /** 
 	 * 删除结算类别记录的方法  
 	 * @param ids ID字符串
 	 * @return 删除成功==true  删除失败==false
     */
     public boolean deleteSettleCategoryByIds(String ids) {
         String[] split = ids.split(",");
         int result = settleCategoryMapper.deleteSettleCategoryByIds(split);
         return result == split.length;
     }

    /** 
  	 * 更新结算类别记录的方法
  	 * @param 包含更新信息的结算类别对象
  	 * @return 更新成功==1  更新失败==0
     */ 
     public int updateSettleCategoryById(Map<String,Object> settleCategory) {
          return settleCategoryMapper.updateSettleCategoryById(settleCategory);
     }
     
    /** 
 	 * 添加结算类别记录的方法  
 	 * @param 包含结算类别信息的对象
 	 * @return 添加成功==1  添加失败==0
     */
     public int addSettleCategory(Map<String,Object> settleCategory) {
         return settleCategoryMapper.addSettleCategory(settleCategory);
     }
}