package com.neuedu.his.neusys.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.neuedu.his.neusys.service.SchedulingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.neuedu.his.mapper.SchedulingMapper;

/** 
* 排班管理专属Service
* @auther: 东软教师
* @date:   2019-2-18
*/ 
@Service
@Transactional
public class SchedulingServiceImpl implements SchedulingService {
    @Autowired
    SchedulingMapper schedulingMapper;
    
    /** 
     * 按条件分页查询排班的方法   
     * @param: page当前页   count每页显示记录数量   keywords keywords2 查询关键字
     * @return: 包含查询结果的Map集合
     */  
    public List<Map<String,Object>> getSchedulingList( Integer page, Integer count,String keywords,String keywords2) {
         int start = (page - 1) * count;
         return schedulingMapper.getSchedulingList(start, count, keywords ,keywords2);
    }
    
    /** 
     * 根据排班日期(看诊日期)查午别   按条件分页查询规则记录的方法    
     * @param: page当前页   count每页显示记录数量   keywords 查询关键字
     * @return: 包含查询结果的Map集合
     */ 
    public List<Map<String,Object>> getSchedulingList05( Integer page, Integer count,String keywords) {
        int start = (page - 1) * count;
        return schedulingMapper.getSchedulingList05(start, count, keywords);
    }
    
    /** 
     * 根据日期和午别查科室   按条件分页查询规则记录的方法 
     * @param: page当前页   count每页显示记录数量   keywords keywords2查询关键字
     * @return: 包含查询结果的Map集合
     */ 
    public List<Map<String,Object>> getSchedulingList03( Integer page, Integer count,String keywords
    		,String keywords2) {
        int start = (page - 1) * count;
        return schedulingMapper.getSchedulingList03(start, count, keywords, keywords2);
    }
    
    /** 
     * 根据日期 午别 科室 查号别    按条件分页查询规则记录的方法 
     * @param: page当前页   count每页显示记录数量   keywords keywords2 keywords3查询关键字
     * @return: 包含查询结果的Map集合
     */ 
    public List<Map<String,Object>> getSchedulingList04( Integer page, Integer count
    		,String keywords,String keywords2,String keywords3) {
        int start = (page - 1) * count;
        return schedulingMapper.getSchedulingList04(start, count, keywords, keywords2, keywords3);
    }
    
    /** 
     * 根据日期 午别 科室 号别 查医生  按条件分页查询规则记录的方法 
     * @param: page当前页   count每页显示记录数量   keywords keywords2 keywords3 keywords4查询关键字
     * @return: 包含查询结果的Map集合
     */ 
    public List<Map<String,Object>> getSchedulingList02( Integer page, Integer count,String keywords
    		,String keywords2,String keywords3,String keywords4) {
        int start = (page - 1) * count;
        return schedulingMapper.getSchedulingList02(start, count, keywords, keywords2, keywords3, keywords4);
    }
    
    
   /** 
     * 获取符合条件的记录数量
     * @param keywords keywords2 查询条件 
     * @return 记录数量
     */ 
     public int getSchedulingCount( String keywords, String keywords2) {
         return schedulingMapper.getSchedulingCount(keywords, keywords2);
     }
     
     public int getSchedulingCount02( String keywords) {
         return schedulingMapper.getSchedulingCount02(keywords);
     }
     
    /** 
      * 获取排班医生的 挂号额度
      * @param keywords keywords2 keywords3 查询关键字
      * @return 挂号额度  整型
      */ 
     public int getSchedulingCount06(String keywords,String keywords2,String keywords3) {
         return schedulingMapper.getSchedulingCount06(keywords,keywords2,keywords3);
     }
    
     
     /** 
      * 获取排班医生的 挂号费金额
      * @param keywords keywords2 keywords3 查询关键字
      * @return 挂号费金额  单精度浮点类型
      */ 
     public float getSchedulingCount07(String keywords,String keywords2,String keywords3) {
         return schedulingMapper.getSchedulingCount07(keywords,keywords2,keywords3);
     }
     
     /** 
 	 * 删除排班记录的方法  
 	 * @param ids ID字符串
 	 * @return 删除成功==true  删除失败==false
     */
     public boolean deleteSchedulingByIds(String ids) {
         String[] split = ids.split(",");
         int result = schedulingMapper.deleteByPrimaryKey(split);
         return result == split.length;
     }

    /** 
 	 * 更新排班记录的方法
 	 * @param 包含更新信息的排班对象
 	 * @return 更新成功==1  更新失败==0
     */
     public int updateSchedulingById(Map<String,Object> scheduling) {
          return schedulingMapper.updateSchedulingById(scheduling);
     }
     
     /** 
 	 * 添加排班记录的方法  
 	 * @param 包含排班信息的对象
 	 * @return 添加成功==1  添加失败==0
     */
     public int addScheduling(Map<String,Object> scheduling)  {
    	  String ksrq_str = (String)scheduling.get("ksrq");
    	  String jsrq_str = (String)scheduling.get("jsrq");
    	  String Week = (String)scheduling.get("Week");
    	  //String Userid_str = (String)scheduling.get("Userid");
    	  String Userid_str = (String)scheduling.get("UserId");
    	  System.out.println("Userid_str="+Userid_str);
    	  int n=0;
    	  Date d1;
    	  Date d2;
		  try {
				d1 = new SimpleDateFormat("yyyy-MM-dd").parse(ksrq_str);
				d2 = new SimpleDateFormat("yyyy-MM-dd").parse(jsrq_str);//定义结束日期
				Calendar cd1 = Calendar.getInstance();//定义日期实例
		  		Calendar cd2 = Calendar.getInstance();//定义日期实例
	  		
		  		cd2.setTime(d2);//设置日期起始时间
		  		cd2.add(Calendar.DATE, 1);
		  		d2=cd2.getTime();
                cd1.setTime(d1);//设置日期起始时间
	  		    while(cd1.getTime().before(d2)){//判断是否到结束日期
                  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                  String str = sdf.format(cd1.getTime());
	  			  scheduling.put("SchedDate", str);
	  			  
	  			  int h=dayForWeek(str);
	  			  if(Week.charAt((h-1)*2)=='1')
	  			  {
					//System.out.println(str+" 星期"+h+" 上午有班");
					scheduling.put("Noon", "上午");
					
					//schedulingMapper.deleteSchedulingByDate(str, Userid_str, ""+22);
					
					n= schedulingMapper.addScheduling(scheduling);
	  			  }
				  if(Week.charAt((h-1)*2+1)=='1')
				  {
					//System.out.println(str+" 星期"+h+" 下午有班");
					scheduling.put("Noon", "下午");
					//schedulingMapper.deleteSchedulingByDate(str, Userid_str, ""+23);
					n= schedulingMapper.addScheduling(scheduling);
				  }
	      	      
	      	      //System.out.println(str);//输出日期结果
                  cd1.add(Calendar.DATE, 1);//进行当前日期月份加1 
	  			  //dd.add(Calendar.MONTH, 1);//进行当前日期月份加1
	  		}
	    } catch (Exception e) {
			e.printStackTrace();
		}//定义起始日期
        return n;
     }
      
   /**
    * 判断当前日期是星期几
    * 
    * @param pTime 修要判断的时间
    * @return dayForWeek 判断结果
    * @Exception 发生异常
    */
  	public static int dayForWeek(String pTime) throws Exception {
  		  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
  		  Calendar c = Calendar.getInstance();
  		  c.setTime(format.parse(pTime));
  		  int dayForWeek = 0;
  		  if(c.get(Calendar.DAY_OF_WEEK) == 1){
  		   dayForWeek = 7;
  		  }else{
  		   dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
  		  }
  		  return dayForWeek;
   }	
  	
  	/*
    //获取排班医生的 挂号级别
    public int getSchedulingCount08(String keywords,String keywords2,String keywords3) {
        return schedulingMapper.getSchedulingCount08(keywords,keywords2,keywords3);
    }
    
    //获取排班医生的 科室id
    public int getSchedulingCount09(String keywords,String keywords2,String keywords3) {
        return schedulingMapper.getSchedulingCount09(keywords,keywords2,keywords3);
    }
    */
}