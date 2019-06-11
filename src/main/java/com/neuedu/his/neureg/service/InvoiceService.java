package com.neuedu.his.neureg.service;

import java.util.List;
import java.util.Map;
import com.neuedu.his.bean.Invoice;

/** 
* 发票管理专属Service接口 
* @auther: 东软教师
* @date:   2019-3-25
*/ 
public interface InvoiceService {
	
	/** 
     * 得到符合查询条件的记录数量
     * @param: keywords查询关键字
     * @return: 记录数量 整型值
     */ 
	int getInvoiceCount(String keywords);

    
	/** 
     * 分页查询发票信息
     * @param: 当前页   每页显示记录数   keywords查询关键字
     * @return: 符合查询条件的对象列表
     */ 
	List<Map<String, Object>> invoice_query(Integer page, Integer count, String keywords);
	
	
	/** 
     * 发票补打更新 
     * @param: 包含更新信息的发票对象
     * @return: 1=更新成功  0=更新失败 
     */ 
	int updateMakeup(Map<String, Object> invoice);
	
	
	/** 
     * 发票查询  发票重打使用的
     * @param: 当前页   每页显示记录数   keywords查询关键字
     * @return: 符合查询条件的对象列表
     */ 
	List<Map<String, Object>> invoice_query2(Integer page, Integer count, String keywords);
	
	
	/** 
     * 发票重打更新
     * @param: 包含更新信息的发票对象
     * @return: 1=更新成功  0=更新失败 
     */ 
	int updateReplay(Map<String, Object> invoice);
	
	
	/** 
     * 添加发票对象   发票重打时使用
     * @param: 包含更新信息的发票对象
     * @return: 1=添加成功  0=添加失败 
     */ 
	int addInvoice02(Map<String, Object> invoice);
	
	
	/** 
     * 取发票表里的拥有最大的发票号的发票对象
     * @param: 无
     * @return: 发票对象 
     */ 
    Invoice getInvoiceBean();
	
    /** 
     * 添加发票  交费时 使用
     * @param: 包含更新信息的发票对象
     * @return: 1=添加成功  0=添加失败 
     */ 
	int addInvoice(Map<String, Object> invoice);
	
	
	/** 
     * 添加发票   退费时的冲红发票使用
     * @param: 包含更新信息的发票对象
     * @return: 1=添加成功  0=添加失败 
     */ 
	int addInvoice03(Map<String, Object> invoice);
    
	
	/** 
     * 添加发票   退费时 使用
     * @param: 包含更新信息的发票对象
     * @return: 1=添加成功  0=添加失败 
     */ 
	int addInvoice318(Map<String, Object> invoice);
}