package com.neuedu.his.neureg.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.neuedu.his.neureg.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.neuedu.his.bean.Invoice;
import com.neuedu.his.mapper.InvoiceMapper;
import com.neuedu.his.mapper.PatientCostsMapper;

/** 
* 发票模块专属Service 
* @auther: 东软教师
* @date:   2019-3-25
*/ 
@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {
	    @Autowired
	    InvoiceMapper invoiceMapper;
	    
	    @Autowired
	    PatientCostsMapper patientCostsMapper;
	    
	    /** 
	     * 获取符合条件的记录数量
	     * @param keywords 查询条件
	     * @return 记录数量 
	     */ 
	     public int getInvoiceCount( String keywords) {
	         return invoiceMapper.getInvoiceCount(keywords);
	     }
	    
	    /** 
	    * 发票分页查询  发票补打模块调用 
	    * @param 当前页 每页显示记录数  查询条件
	    * @return 对象列表 
	    */ 
	    public List<Map<String,Object>> invoice_query( Integer page, Integer count,String keywords) {
	         int start = (page - 1) * count;
	         return invoiceMapper.getInvoiceList(start, count, keywords,3);//3-未打印
	    }
	    
	    /** 
	     * 发票补打更新 
	     * @param: 包含更新信息的发票对象
	     * @return: 1=更新成功  0=更新失败 
	     */ 
	     public int updateMakeup(Map<String,Object> invoice) {
	    	 invoice.put("State",4);//4-已打印
	    	 return invoiceMapper.updateState(invoice);
	     }
	    
	     /** 
	      * 发票查询  发票重打使用的
	      * @param: 当前页   每页显示记录数   keywords查询关键字
	      * @return: 符合查询条件的对象列表
	      */ 
	    public List<Map<String,Object>> invoice_query2( Integer page, Integer count,String keywords) {
	        int start = (page - 1) * count;
	        return invoiceMapper.getInvoiceList02(start, count, keywords,2,7);//2-作废 7-冲红
	    }
	    
	    /** 
	     * 发票重打更新
	     * @param: 包含更新信息的发票对象
	     * @return: 1=更新成功  0=更新失败 
	     */ 
	    public int updateReplay(Map<String,Object> invoice) {
	         //return invoiceMapper.updateInvoiceById03(invoice);
	    	invoice.put("State",2);//2-作废
	    	return invoiceMapper.updateState(invoice);
	    }
	    
	    /** 
	     * 添加发票对象   发票重打时使用
	     * @param: 包含更新信息的发票对象
	     * @return: 1=添加成功  0=添加失败 
	     */ 
	      public int addInvoice02(Map<String,Object> invoice) {
	    	  
	    	  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	   	      String date_str=df.format(new Date()).toString();
	   	      invoice.put("CreationTime", date_str);
	   	      invoice.put("State", 5);
	   	      //第一次插入  重打的新发票
	          int n= invoiceMapper.addInvoice(invoice);
	          //获得 上面操作记录的id(最大id)
	   	      int max_InvId = invoiceMapper.getMaxInvId();
	          String Money_str = (String)invoice.get("Money");
	          invoice.put("Money", "-"+Money_str);
	          invoice.put("State", 7);
	          invoice.put("Back", max_InvId);
	          
	          //第二次插入  重打的新发票对应的冲红发票
	          n= invoiceMapper.addInvoice(invoice);
	          return n;
	    }

	    /** 
	     * 取发票表里的拥有最大的发票号的发票对象
	     * @param: 无
	     * @return: 发票对象 
	    */ 
	    public Invoice getInvoiceBean()
	    {
	    	return invoiceMapper.getInvoiceBean();
	    }
	     
	    /** 
	     * 添加发票  交费时 使用
	     * @param: 包含更新信息的发票对象
	     * @return: 1=添加成功  0=添加失败 
	     */ 
	      public int addInvoice(Map<String,Object> invoice) {
	    	  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	   	      String date_str=df.format(new Date()).toString();
	   	      invoice.put("CreationTime", date_str);
	   	      invoice.put("State", 3);//3--交费未打印
	          int n= invoiceMapper.addInvoice(invoice);
	          //获得 上面操作记录的id(最大id)
	   	      int max_InvId = invoiceMapper.getMaxInvId();
	   	      
	   	      Object obj= invoice.get("aaa");
	    	  String ss = obj.toString();
	    	  //System.out.println("ss="+ss);
	    	  
	    	  Object obj2= invoice.get("bbb");
	    	  String ss2 = obj2.toString();
	    	  System.out.println("ss2="+ss2);
	    	  
	    	  //插入患者消费信息表
	          Map<String,Object> patientCosts = new HashMap<String,Object>();
	    	  patientCosts.put("InvoiceID",max_InvId);
		   	  patientCosts.put("DeptID", "22");
		   	  patientCosts.put("ItemID", 1);//1=挂号费        getGhf('GHF')  返回=1 //3
		      patientCosts.put("ItemType", 1);//1=非药品  2=药品                     //4
		   	  patientCosts.put("Createtime", date_str);//开立时间
		   	  patientCosts.put("CreateOperID", 301);//开立人员ID
		   	  patientCosts.put("PayTime",date_str);//收/退费时间
		   	  patientCosts.put("RegisterID", 301);//收/退费人员ID
		   	  patientCosts.put("FeeType", 51);//收费方式
		   	  String[] s1 = ss.split(",");
	     	  for(int i=0;i<s1.length;i++)
	     	  {
	     		//System.out.println("s1["+i+"]="+s1[i]);
	     		String[] s10 = s1[i].split("\\|");
	     		for(int j=0;j<s10.length;j++)
	         		System.out.print("s10["+j+"]="+s10[j]+" ");
	         	//System.out.println();
	         	//System.out.println("s10[6]="+s10[6]);
	         	//System.out.println("s10[3]="+s10[3]);
	         	patientCosts.put("RegistID", s10[6]);
	         	patientCosts.put("Name", s10[3]);
	         	patientCosts.put("Price",s10[2]);
	         	patientCosts.put("Amount",s10[4]);
	         	//插入患者消费表PatientCosts
	  	   	    patientCostsMapper.addPatientCosts(patientCosts);
	         	//System.out.println("---------------------------------");
	          }
	     	  
	     	  //更新处方表 和 检查表
	          Map<String,Object> prescription = new HashMap<String,Object>();
	          prescription.put("State", 3);//3-已交费
	     	  
	     	  //ss2="1|1,6|2"; //"prenID":"tabnum"
	     	  String[] s2 = ss2.split(",");
	     	  for(int i=0;i<s2.length;i++)
	     	  {
	     		System.out.println("s2["+i+"]="+s2[i]);
	     		String[] s10 = s2[i].split("\\|");
	     		//for(int j=0;j<s10.length;j++)
	         	//	System.out.print("s10["+j+"]="+s10[j]+" ");
	     		
	     	    ////s10[0]=6 id ;   s10[1]= 表
	     		prescription.put("ID", s10[0]);//0  "prenID"
	     		
	     		if(s10[1].equals("1"))
	     		    //更新处方表
		            patientCostsMapper.updatePrescriptionById(prescription);
	     		
	     		if(s10[1].equals("2"))
	     		    //更新CheckApply (患者检查/检验/处置申请表)表的 交费状态 3-已交费    6-已退费
		            patientCostsMapper.updateCheckApplyById(prescription);
	         }
	     	 return n;
	      }
	      
	      /** 
	       * 添加发票   退费时的冲红发票使用
	       * @param: 包含更新信息的发票对象
	       * @return: 1=添加成功  0=添加失败 
	       */ 
	      public int addInvoice03(Map<String,Object> invoice) {
	    	  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	   	      String date_str=df.format(new Date()).toString();
	   	      invoice.put("CreationTime", date_str);
	   	      invoice.put("State", 7);
	          return invoiceMapper.addInvoice(invoice);
	      }
	      
	      /** 
	       * 添加发票   退费时 使用
	       * @param: 包含更新信息的发票对象
	       * @return: 1=添加成功  0=添加失败 
	       */ 
	      public int addInvoice318(Map<String,Object> invoice) {
	    	  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	   	      String date_str=df.format(new Date()).toString();
	   	      
	   	      invoice.put("CreationTime", date_str);
	   	      invoice.put("State", 7);//7-红冲
	   	      invoice.put("FeeType",51);//51-现金
	   	      invoice.put("Money","-"+invoice.get("Money"));//
	          invoiceMapper.addInvoice(invoice);
	          //获得 上面操作记录的id(最大id)
	   	      int max_InvId = invoiceMapper.getMaxInvId();
	   	      Object obj= invoice.get("aaa");
	 	      String ss = obj.toString();
	 	      System.out.println("ss="+ss);
	 	      //插入患者消费信息表
	          Map<String,Object> patientCosts = new HashMap<String,Object>();
	 	      patientCosts.put("InvoiceID",max_InvId);
		   	  patientCosts.put("DeptID", "22");
		   	  patientCosts.put("ItemID", 1);//1=挂号费        getGhf('GHF')  返回=1 //3
		      patientCosts.put("ItemType", 1);//1=非药品  2=药品                     //4
		   	  patientCosts.put("Createtime", date_str);//开立时间
		   	  patientCosts.put("CreateOperID", 301);//开立人员ID
		   	  patientCosts.put("PayTime",date_str);//收/退费时间
		   	  patientCosts.put("RegisterID", 301);//收/退费人员ID
		   	  patientCosts.put("FeeType", 51);//收费方式
		   	  String[] s1 = ss.split(",");
	  	      for(int i=0;i<s1.length;i++)
	  	      {
	  		      System.out.println("s1["+i+"]="+s1[i]);
	  		
	  		      String[] s10 = s1[i].split("\\|");
	  		      for(int j=0;j<s10.length;j++)
	      		    System.out.print("s10["+j+"]="+s10[j]+" ");
	      	      System.out.println();
	      	
	      	      System.out.println("s10[6]="+s10[6]);
			      System.out.println("s10[3]="+s10[3]);
			      patientCosts.put("RegistID", s10[6]);
			      patientCosts.put("Name", s10[3]);
			      patientCosts.put("Price",s10[2]);
			      patientCosts.put("Amount","-"+s10[4]);
	      	     //插入患者消费表PatientCosts
		   	     patientCostsMapper.addPatientCosts(patientCosts);
	      	     System.out.println("---------------------------------");
	          }
	          
	          //更新处方表
	          Map<String,Object> prescription = new HashMap<String,Object>();
	          prescription.put("State", 6);//6-已退费
	          int n=0;
	          
	          //////////////////////
	          Object obj2= invoice.get("bbb");
	    	  String ss2 = obj2.toString();
	    	  System.out.println("ss2="+ss2);
	          //ss2="1|1,6|2"; //"prenID":"tabnum"
	     	  String[] s2 = ss2.split(",");
	     	  for(int i=0;i<s2.length;i++)
	     	  {
	     		 //System.out.println("s2["+i+"]="+s2[i]);
	     		 String[] s10 = s2[i].split("\\|");
	     		 //s10[0]=6 id ;   s10[1]= 表
	     		 prescription.put("ID", s10[0]);//0  "prenID"
	     		
	     		 if(s10[1].equals("1"))
	     		    //更新处方表
	     			n=patientCostsMapper.updatePrescriptionById(prescription);
	     		
	     		 if(s10[1].equals("2"))
	     		    //更新CheckApply (患者检查/检验/处置申请表)表的 交费状态 3-已交费    6-已退费
	     			n=patientCostsMapper.updateCheckApplyById(prescription);
	          }
	          return 1;//n
	      }
}