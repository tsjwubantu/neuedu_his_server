package com.neuedu.his.neupha.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.neuedu.his.bean.RespBean;
import com.neuedu.his.neupha.service.DrugService;
import com.neuedu.his.util.ExcelUtil;

/** 
* 操作药品Controller 
* @auther: 东软教师
* @date:   2018-11-29
*/ 
@RestController
@RequestMapping("/neupha/drug")
public class DrugController {
    @Autowired
    private DrugService phaService; 
    
    @RequestMapping(value = "/listPage", method = RequestMethod.GET)
    public RespBean getDrugsPage( @RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber, 
    		@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,String keywords01 ) {

        RespBean resp = phaService.getDrugList( pageNumber, pageSize,keywords01);       
        return resp;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public RespBean addNewDrug(@RequestParam Map<String,Object> map) {
        int result = phaService.addDrugOne(map);
        if (result == 1) {
            return new RespBean("success", "添加成功!(药品信息)");
        }
        return new RespBean("error", "添加失败!");
    }
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public RespBean updateDrug(@RequestParam Map<String,Object> map) {
        int result = phaService.updateDrugOne(map);
        if (result == 1) {
            return new RespBean("success", "修改成功!(药品信息)");
        }
        return new RespBean("error", "修改失败!");
    }
    
    @RequestMapping(value = "/{ids}", method = RequestMethod.DELETE)
    public RespBean delDrugOne(@PathVariable("ids") String ids) {
        boolean result = phaService.delDrug(ids);
        if (result) {
            return new RespBean("success", "删除成功!(药品信息)");
        }
        return new RespBean("error", "删除失败!");
    }
    
    @CrossOrigin(origins = "http://localhost:8080")
    @RequestMapping("/upExcel")
    public RespBean getAllDrugs(@RequestParam MultipartFile file) throws IOException {
		//System.out.println("文件开始接收。。。"+file.getOriginalFilename()+":"+file.getSize());
    	// 同时支持Excel 2003、2007
		if(file==null) {
			return this.getDrugsPage(1,10,null);
		}
		try {
			List<Map<String, Object>> drugList = ExcelUtil.readExcel(file);
			phaService.addDrug(drugList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int totalCount = phaService.getDrugCount(null);
        return phaService.getDrugList(1, 10,null);
       
    }
	
//	@RequestMapping(value = "/getDrugTypeByConst", method = RequestMethod.GET)
//    public List<Map<String, Object>> getDrugsType(  ) {
//        List<Map<String, Object>> list = phaService.getDrugTypeByConst();        
//        return list;
//    }
//	@RequestMapping(value = "/getDrugDosageByConst", method = RequestMethod.GET)
//    public List<Map<String, Object>> getDrugDosageByConst(  ) {
//        List<Map<String, Object>> list = phaService.getDrugDosageByConst();        
//        return list;
//    }
	
//	@RequestMapping(value = "/{ids}", method = RequestMethod.DELETE)
//    public RespBean deleteById(@PathVariable String ids) {
//        boolean result = phaService.deletePersonByIds(ids);
//        if (result) {
//            return new RespBean("success", "删除成功!");
//        }
//        return new RespBean("error", "删除失败!");
//    }

}
