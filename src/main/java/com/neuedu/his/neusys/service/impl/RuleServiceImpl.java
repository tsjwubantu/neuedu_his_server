package com.neuedu.his.neusys.service.impl;

import java.util.List;
import java.util.Map;

import com.neuedu.his.neusys.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.neuedu.his.mapper.RuleMapper;

/** 
* 规则管理专属Service
* @auther: 东软教师
* @date:   2019-2-18
*/ 
@Service
@Transactional
public class RuleServiceImpl implements RuleService {
    @Autowired
    RuleMapper ruleMapper;
    
    /** 
     * 按条件分页查询规则记录的方法 
     * @param: page当前页   count每页显示记录数量   keywords查询关键字
     * @return: 包含查询结果的Map集合
     */ 
    public List<Map<String,Object>> getRuleList( Integer page, Integer count,String keywords) {
         int start = (page - 1) * count;
         return ruleMapper.getRuleList(start, count, keywords);
     }

    
    /** 
     * 按条件统计记录数量  确定页数最大值时用的
     * @param keywords 查询关键字
     * @return 记录数量  整型
     */ 
     public int getRuleCount( String keywords) {
         return ruleMapper.getRuleCount(keywords);
     }

     /** 
 	 * 删除规则记录的方法  
 	 * @param ids ID字符串
 	 * @return 删除成功==true  删除失败==false
      */
     public boolean deleteRuleByIds(String ids) {
         String[] split = ids.split(",");
         int result = ruleMapper.deleteRuleByIds(split);
         return result == split.length;
     }

     /** 
 	 * 更新规则记录的方法
 	 * @param 包含更新信息的规则对象
 	 * @return 更新成功==1  更新失败==0
      */
     public int updateRuleById(Map<String,Object> rule) {
          return ruleMapper.updateRuleById(rule);
     }
     
    /** 
 	 * 添加规则记录的方法  
 	 * @param 包含规则信息的对象
 	 * @return 添加成功==1  添加失败==0
     */
     public int addRule(Map<String,Object> rule) {
         return ruleMapper.addRule(rule);
     }
}