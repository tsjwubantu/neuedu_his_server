package com.neuedu.his.neureg.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.neuedu.his.bean.Invoice;
import com.neuedu.his.bean.RespBean;
import com.neuedu.his.neureg.service.InvoiceService;


/** 
* 发票管理专属Controller 
* @auther: 东软教师
* @date:   2019-3-25
*/ 
@RestController
@RequestMapping("/neureg/invoice")
public class InvoiceController {
    @Autowired
    InvoiceService invoiceService;
    
    /** 
     * 按条件分页查询发票的方法    发票补打使用的
     * @param: page当前页   count每页显示记录数量   keywords查询关键字
     * @return: 包含查询结果的Map集合
     */ 
    @RequestMapping(value = "/invoice_query", method = RequestMethod.GET)
    public Map<String, Object> invoice_query( @RequestParam(value = "page", defaultValue = "1") Integer page, 
    		@RequestParam(value = "count", defaultValue = "10") Integer count,String keywords) {
        int totalCount = invoiceService.getInvoiceCount(keywords);//1
        List<Map<String,Object>> list = invoiceService.invoice_query( page, count,keywords);//2
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);
        map.put("list", list);
        
        return map;
    }
    
    /** 
     * 更新发票的方法    发票补打使用
     * @param: invoice 包含更新信息的Map集合
     * @return: 包含响应信息的对象
     */ 
    @RequestMapping(value = "/invoice_makeup", method = RequestMethod.PUT)
    public RespBean invoice_makeup(@RequestParam Map<String,Object> invoice) {
        int i = invoiceService.updateMakeup(invoice);
        if (i == 1) {
            return new RespBean("success", "发票补打成功!");
        }
        return new RespBean("error", "发票补打失败!");
    }
    
    /** 
     * 按条件分页查询发票的方法    发票重打使用的
     * @param: page当前页   count每页显示记录数量   keywords查询关键字
     * @return: 包含查询结果的Map集合
     */ 
    @RequestMapping(value = "/invoice_query2", method = RequestMethod.GET)
    public Map<String, Object> invoice_query2( @RequestParam(value = "page", defaultValue = "1") Integer page, 
    		@RequestParam(value = "count", defaultValue = "6") Integer count,String keywords) {
        int totalCount = invoiceService.getInvoiceCount(keywords);
        List<Map<String,Object>> list = invoiceService.invoice_query2( page, count,keywords);
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);
        map.put("list", list);
        
        return map;
    }
    
    /**
     * 添加发票的方法   发票重打时使用
     * @param: invoice 包含发票信息的Map集合
     * @return: 包含响应信息的对象
     */ 
    @RequestMapping(value = "/invoice_replay", method = RequestMethod.POST)
    public RespBean invoice_replay(@RequestParam Map<String,Object> invoice) {
        //发票重打更新
    	invoiceService.updateReplay(invoice);
    	
    	int result = invoiceService.addInvoice02(invoice);
        if (result == 1) {
            return new RespBean("success", "操作成功!");
        }
        return new RespBean("error", "操作失败!");
    }
    
    /** 
     * 查找发票编号最大的发票对象
     * @param: page当前页   count每页显示记录数量   keywords查询关键字
     * @return: 发票对象
     */ 
    @RequestMapping(value = "/bean01", method = RequestMethod.GET)
    public Invoice getInvoiceBean() 
    {
    	Invoice invoice = invoiceService.getInvoiceBean();
        return invoice;
    }
    
    /** 
     * 添加发票的方法    挂号 和 收费功能在使用
     * @param: invoice 包含发票信息的Map集合
     * @return: 包含响应信息的对象
     */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public RespBean addNewInvoice(@RequestParam Map<String,Object> invoice) {
        int result = invoiceService.addInvoice(invoice);
        if (result == 1) {
            return new RespBean("success", "收费成功!(发票)");
        }
        return new RespBean("error", "收费失败!(发票)");
    }
    
    /** 
     * 添加发票的方法   退费时  冲红发票使用
     * @param: invoice 包含发票信息的Map集合
     * @return: 包含响应信息的对象
     */ 
    @RequestMapping(value = "/add03", method = RequestMethod.POST)
    public RespBean addNewInvoice03(@RequestParam Map<String,Object> invoice) {
        int result = invoiceService.addInvoice03(invoice);
        if (result == 1) {
            return new RespBean("success", "退费成功!(发票)");
        }
        return new RespBean("error", "退费失败!(发票)");
    }
    
    
    /** 
     * 添加发票的方法   退费时使用的
     * @param: invoice 包含发票信息的Map集合
     * @return: 包含响应信息的对象
     */ 
    @RequestMapping(value = "/add318", method = RequestMethod.POST)
    public RespBean addNewInvoice318(@RequestParam Map<String,Object> invoice) {
        int result = invoiceService.addInvoice318(invoice);
        if (result == 1) {
            return new RespBean("success", "退费成功!");
        }
        return new RespBean("error", "退费失败!");
    }
}