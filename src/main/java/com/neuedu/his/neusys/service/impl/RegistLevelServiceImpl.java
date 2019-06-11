package com.neuedu.his.neusys.service.impl;

import java.util.List;
import java.util.Map;

import com.neuedu.his.neusys.service.RegistLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.neuedu.his.mapper.RegistLevelMapper;

/** 
* 挂号等级专属Service
* @auther: 东软教师
* @date:   2019-2-18
*/ 
@Service
@Transactional
public class RegistLevelServiceImpl implements RegistLevelService {
    @Autowired
    RegistLevelMapper registLevelMapper;
    
    /** 
     * 按条件分页查询挂号等级记录的方法 
     * @param: page当前页   count每页显示记录数量   keywords查询关键字
     * @return: 包含查询结果的Map集合
     */ 
    public List<Map<String,Object>> getRegistLevelList( Integer page, Integer count,String keywords) {
         int start = (page - 1) * count;
         return registLevelMapper.getRegistLevelList(start, count, keywords);
     }

    /** 
     * 按条件统计记录数量  确定页数最大值时用的
     * @param keywords 查询关键字
     * @return 记录数量  整型
     */ 
     public int getRegistLevelCount( String keywords) {
         return registLevelMapper.getRegistLevelCount(keywords);
     }

    /** 
 	 * 删除挂号等级记录的方法  
 	 * @param ids ID字符串
 	 * @return 删除成功==true  删除失败==false
     */
     public boolean deleteRegistLevelByIds(String ids) {
         String[] split = ids.split(",");
         int result = registLevelMapper.deleteRegistLevelByIds(split);
         return result == split.length;
     }

    /** 
 	 * 更新挂号等级记录的方法
 	 * @param 包含更新信息的挂号等级对象
 	 * @return 更新成功==1  更新失败==0
     */
     public int updateRegistLevelById(Map<String,Object> registLevel) {
          return registLevelMapper.updateRegistLevelById(registLevel);
     }
     
    /** 
 	 * 添加挂号等级记录的方法  
 	 * @param 包含挂号等级信息的对象
 	 * @return 添加成功==1  添加失败==0
     */
     public int addRegistLevel(Map<String,Object> registLevel) {
         return registLevelMapper.addRegistLevel(registLevel);
     }
}