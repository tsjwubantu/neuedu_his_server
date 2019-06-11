package com.neuedu.his.neusys.service.impl;

import java.util.List;
import java.util.Map;

import com.neuedu.his.neusys.service.DiseaseSYSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.neuedu.his.mapper.DiseaseMapper;


/** 
* 疾病管理专属Service 
* @auther: 东软教师
* @date:   2018-11-29
*/ 
@Service
@Transactional
public class DiseaseSYSServiceImpl implements DiseaseSYSService {
    @Autowired
    DiseaseMapper diseaseMapper;
    
    /** 
     * 按条件分页查询疾病的方法 
     * @param: page当前页   count每页显示记录数量   keywords查询关键字
     * @return: 包含查询结果的Map集合
     */ 
    public List<Map<String,Object>> getDiseaseList( Integer page, Integer count,String keywords) {
         int start = (page - 1) * count;
         return diseaseMapper.getDiseaseList(start, count, keywords);
     }

    
    
     public int getDiseaseCount( String keywords) {
         return diseaseMapper.getDiseaseCount(keywords);
     }

     /** 
      * 根据id号删除疾病的方法  可以批量删除
      * @param: ids 包含id的字符串
      * @return: 成功返回为真  失败返回为假
      */ 
      public boolean deleteDiseaseByIds(String ids) {
         String[] split = ids.split(",");
         int result = diseaseMapper.deleteDiseaseByIds(split);
         return result == split.length;
     }

      /** 
       * 更新疾病信息的方法 
       * @param: disease 包含更新信息的Map集合
       * @return: 成功返回1  失败返回0
       */ 
     public int updateDiseaseById(Map<String,Object> disease) {
          return diseaseMapper.updateDiseaseById(disease);
     }
     
     /** 
      * 添加疾病的方法 
      * @param: disease 包含科室信息的Map集合
      * @return: 成功返回1  失败返回0
      */ 
      public int addDisease(Map<String,Object> disease) {
         return diseaseMapper.addDisease(disease);
     }
}