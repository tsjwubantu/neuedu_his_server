package com.neuedu.his.neusys.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.neuedu.his.neusys.service.FmeditemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.neuedu.his.mapper.FmeditemMapper;

/** 
* 非药品收费项目专属Service
* @auther: 东软教师
* @date:   2019-2-18
*/ 
@Service
@Transactional
public class FmeditemServiceImpl implements FmeditemService {
    @Autowired
    FmeditemMapper fmeditemMapper;
    
    /** 
     * 按条件分页查询非药品收费项目记录的方法 
     * @param: page当前页   count每页显示记录数量   keywords查询关键字
     * @return: 包含查询结果的Map集合
     */ 
    public List<Map<String,Object>> getFmeditemList( Integer page, Integer count,String keywords) {
         int start = (page - 1) * count;
         return fmeditemMapper.getFmeditemList(start, count, keywords);
     }

    
    /** 
     * 按条件统计记录数量  确定页数最大值时用的
     * @param keywords 查询关键字
     * @return 记录数量  整型
     */ 
     public int getFmeditemCount( String keywords) {
         return fmeditemMapper.getFmeditemCount(keywords);
     }

    /** 
 	 * 删除非药品收费项目记录的方法  
 	 * @param ids ID字符串
 	 * @return 删除成功==true  删除失败==false
     */
     public boolean deleteFmeditemByIds(String ids) {
         String[] split = ids.split(",");
         int result = fmeditemMapper.deleteFmeditemByIds(split);
         return result == split.length;
     }

    /** 
 	 * 更新非药品收费项目记录的方法
 	 * @param 包含更新信息的非药品收费项目对象
 	 * @return 更新成功==1  更新失败==0
     */
     public int updateFmeditemById(Map<String,Object> fmeditem) {
    	 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
     	 String date_str=df.format(new Date()).toString(); 
    	 fmeditem.put("LastUpdateDate", date_str); 
    	 return fmeditemMapper.updateFmeditemById(fmeditem);
     }
     
    /** 
 	 * 添加非药品收费项目记录的方法  
 	 * @param 包含非药品收费项目信息的对象
 	 * @return 添加成功==1  添加失败==0
     */
     public int addFmeditem(Map<String,Object> fmeditem) {
    	 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
     	 String date_str=df.format(new Date()).toString(); 
    	 fmeditem.put("CreationDate", date_str);
    	 fmeditem.put("RecordType", 1);
    	 
         return fmeditemMapper.addFmeditem(fmeditem);
     }
}