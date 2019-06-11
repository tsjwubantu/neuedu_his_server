package com.neuedu.his.neureg.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.neuedu.his.bean.Register;
import com.neuedu.his.mapper.RegisterMapper;
import com.neuedu.his.neureg.service.RegisterService;
import com.neuedu.his.util.ParamUtil;
import com.neuedu.his.mapper.InvoiceMapper;
import com.neuedu.his.mapper.PatientCostsMapper;

/** 
* 挂号模块专属Service 
* @auther: 东软教师
* @date:   2019-3-25
*/ 
@Service
@Transactional
public class RegisterServiceImpl implements RegisterService {
	
	 @Autowired
	 RegisterMapper registerMapper;
	
	 @Autowired
	 InvoiceMapper invoiceMapper;
	    
	 @Autowired
	 PatientCostsMapper patientCostsMapper;
   
	/**
	 * 根据医生id和状态，取得患者数据，不分页
	 * @param: 医生ID 患者挂号信息的状态
	 * @return: 符合条件的患者挂号信息对象集合
	 */
	@Override
	public List<Map<String, Object>> getStateRegistersByDoc(String docId,String state) {
		String userId = ParamUtil.stringToInt(docId, 1);
		return registerMapper.getStateRegistersByDoc(userId,state);
	}
	
	/** 
     * 查找病历编号最大的挂号信息对象
     * @param: 无
     * @return: 病历编号最大的挂号信息对象
     */ 
    public Register getRegisterBean()
    {
    	return registerMapper.getRegisterBean();
    }
    
    /** 
     * 通过病历编号查找指定的挂号信息对象
     * @param: CaseNumber  病历编号
     * @return: 指定的挂号信息对象
     */ 
    public Register getRegByCase(String CaseNumber)
    {
    	return registerMapper.getRegByCase(CaseNumber);
    }
    
    /** 
     * 添加挂号信息的方法 
     * @param: register 挂号信息对象
     * @return: 添加成功返回 1   添加失败返回 0
     */ 
     public int addRegister(Map<String,Object> register) {
   	  
   	      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
     	  String date_str=df.format(new Date()).toString();
     	  register.put("RegistTime", date_str);//挂号时间
     	  register.put("VisitState", 1);//本次看诊状态
     	  
          //先插入 挂号表  BaseMapper类方法
     	  int n = registerMapper.insert(register);
     	  
     	  //获得 上面操作记录的id(最大id)
     	  int max_RegId = registerMapper.getMaxRegId();
     	  
     	  //发票表 数据来源
   	      Map<String,Object> invoice = new HashMap<String,Object>();
   	      invoice.put("InvoiceNum", register.get("InvoiceNumber"));
          invoice.put("Money", ""+register.get("Fee"));
   	      invoice.put("State", 3);//3=未打印  4=已打印   
   	      invoice.put("CreationTime", ""+register.get("RegistTime"));
   	      invoice.put("RegisterID", ""+register.get("RegisterID"));//挂号员id
   	      invoice.put("RegistID", max_RegId);  
   	      invoice.put("FeeType", ""+register.get("FeeType"));//收费方式
   	      
   	      //插入发票表
   	      invoiceMapper.addInvoice(invoice);
   	      //获得 上面操作记录的id(最大id)
   	      int max_InvId = invoiceMapper.getMaxInvId();
   	  
   	      //患者消费表  数据来源   PatientCosts  patientCosts
	   	  Map<String,Object> patientCosts = new HashMap<String,Object>();
	   	  patientCosts.put("RegistID", max_RegId);
	   	  patientCosts.put("InvoiceID",max_InvId);
	   	  patientCosts.put("Name", "挂号费");
	   	  patientCosts.put("Price",""+register.get("Fee"));
	   	  patientCosts.put("DeptID", ""+register.get("DeptID"));
	   	  patientCosts.put("ItemID", 1);//1=挂号费
  	      patientCosts.put("ItemType", 1);//1=非药品  2=药品 
	   	  patientCosts.put("Amount", 1);
	   	  patientCosts.put("Createtime", ""+register.get("RegistTime"));//开立时间
	   	  patientCosts.put("CreateOperID", ""+register.get("RegisterID"));//开立人员ID
	   	  patientCosts.put("PayTime",""+register.get("RegistTime"));//收/退费时间
	   	  patientCosts.put("RegisterID", ""+register.get("RegisterID"));//收/退费人员ID
	   	  patientCosts.put("FeeType", ""+register.get("FeeType"));//收费方式
	   	  
	   	  //插入患者消费表PatientCosts
	   	  patientCostsMapper.addPatientCosts(patientCosts);
	   	  return n;
   }
    
    /** 
      * 按条件分页查询挂号记录的方法 
      * @param: page当前页   count每页显示记录数量   keywords keywords2查询关键字
      * @return: 包含查询结果的Map集合
      */ 
    public List<Map<String,Object>> getRegisterList( Integer page, Integer count,String keywords,String keywords2) {
         int start = (page - 1) * count;
         return registerMapper.getRegisterList(start, count, keywords, keywords2);
     }

     /** 
     * 按条件统计记录数量  确定页数最大值时用的
     * @param keywords keywords2查询关键字
     * @return 记录数量  整型
     */ 
     public int getRegisterCount( String keywords,String keywords2) {
         return registerMapper.getRegisterCount(keywords,keywords2);
     }
     
     /** 
      * 按条件统计记录数量  统计已经挂号人数用的
      * @param keywords keywords2 keywords3查询关键字
      * @return 记录数量  整型
      */ 
      public int getAlreadyRegisterCount( String keywords,String keywords2,String keywords3, int n) {
         return registerMapper.getAlreadyRegisterCount(keywords,keywords2,keywords3, n);
      }
     
    
     /** 
      * 更新挂号信息的方法  包含退号业务逻辑   患者退号时调用
      * @param: register 包含更新信息的Map集合
      * @return: 更新成功返回 1   更新失败返回 0
      */ 
     public int updateRegisterStateById(Map<String,Object> register) {
         
    	 //插入发票表退号数据
    	 Map<String,Object> invoice = new HashMap<String,Object>();
    	 invoice.put("Money", "-"+register.get("Money"));
   	     invoice.put("State", 7);//发票冲红状态==7
   	     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
  	     String date_str=df.format(new Date()).toString();
   	     invoice.put("CreationTime", date_str);
   	     //挂号员id
   	     invoice.put("RegisterID", ""+register.get("RegisterID"));//挂号员id
   	     //挂号记录id
   	     invoice.put("RegistID", ""+register.get("RegistID"));//挂号记录id
   	     invoice.put("InvoiceNum", ""+register.get("InvoiceNum"));
   	     invoice.put("FeeType", ""+register.get("FeeType"));//收费方式
   	     invoice.put("Back", ""+register.get("invID"));
   	     //插入发票表
   	     invoiceMapper.addInvoice(invoice);
   	     //获得 上面操作记录的id(最大id)
   	     int max_InvId = invoiceMapper.getMaxInvId();
   	     
   	     //患者消费表  数据来源   更新操作
   	     Map<String,Object> patientCosts = new HashMap<String,Object>();
   	     patientCosts.put("RegistID", register.get("RegistID"));//1
   	     patientCosts.put("InvoiceID", ""+max_InvId);
   	     patientCosts.put("ItemID", 1);//1=挂号费
   	     patientCosts.put("ItemType", 1);//1=非药品  2=药品 
   	     patientCosts.put("Name", "挂号费");
   	     patientCosts.put("Price",register.get("Money"));
   	     patientCosts.put("Amount", "-1");
   	     patientCosts.put("DeptID", ""+register.get("DeptID"));
   	     patientCosts.put("Createtime", ""+register.get("Createteime"));
   	     patientCosts.put("CreateOperID", ""+register.get("CreateOperID"));
   	     patientCosts.put("PayTime", date_str);//收/退费时间 
   	     patientCosts.put("RegisterID", register.get("RegisterID"));
   	     patientCosts.put("FeeType", ""+register.get("FeeType"));//收费方式
   	     patientCosts.put("BackID", ""+register.get("pid"));
   	     //插入患者信息表
   	     patientCostsMapper.addPatientCosts(patientCosts);
   	     
   	     register.put("State", 4);//4-已退号
   	     return registerMapper.updateRegisterStateById(register);
     }
}