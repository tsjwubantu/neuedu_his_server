package com.neuedu.his.neudoc.controller;

import java.util.List;
import java.util.Map;

import com.neuedu.his.neupha.service.TemplateDrugsService;
import com.neuedu.his.neuski.service.TemplateFmesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neuedu.his.bean.RespBean;

/** 
* 模板详情管理Controller 
* @auther: 东软教师
* @date:   2018-11-29
*/ 
@RestController
@RequestMapping("/neupha/templateDetaile")
public class TemplateDetaileController {
    @Autowired
    private TemplateDrugsService templateDrugsService;
    @Autowired
    private TemplateFmesService templateFmesService;

	/**
	 * 成品药模板药品信息功能 --- 以下：增删改查 RESTful
	 */
	@RequestMapping(value = "/listTemplateDrugs", method = RequestMethod.GET)
    public List<Map<String, Object>> getTemplateDrugsPage( String keywords01 ) {
        List<Map<String, Object>> list = templateDrugsService.getTemplateDrugsList(keywords01);        
        return list;
    }
	
    @RequestMapping(value = "/drugs", method = RequestMethod.POST)
    public RespBean addTemplateDrugs(@RequestParam Map<String,Object> map) {
        int result = templateDrugsService.addTemplateDrugs(map);
        if (result == 1) {
            return new RespBean("success", "添加成功!(模板药品信息)");
        }
        return new RespBean("error", "添加失败!");
    }
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public RespBean updateTemplateDrugs(@RequestParam Map<String,Object> map) {
        int result = templateDrugsService.updateTemplateDrugsById(map);
        if (result == 1) {
            return new RespBean("success", "修改成功!(模板药品信息)");
        }
        return new RespBean("error", "修改失败!");
    }
    
    @RequestMapping(value = "/drugs/{ids}", method = RequestMethod.DELETE)
    public RespBean delTemplateDrugs(@PathVariable("ids") String ids) {
        boolean result = templateDrugsService.deleteTemplateDrugsByIds(ids);
        if (result) {
            return new RespBean("success", "删除成功!(模板药品信息)");
        }
        return new RespBean("error", "删除失败!");
    }
    
    /*
	 * 中草药模板药品信息功能
	 * @return
	 */
    
    
    
    /**
	 * 检查、检验、处置模板项目信息功能
	 * @return
	 */
    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public RespBean addTemplateChecks(@RequestParam Map<String,Object> map) {
        int result = templateFmesService.addTemplateChecks(map);
        if (result == 1) {
            return new RespBean("success", "添加成功!(模板药品信息)");
        }
        return new RespBean("error", "添加失败!");
    }
    @RequestMapping(value = "/listTemplateChecks", method = RequestMethod.GET)
    public RespBean listTemplateChecks(String keywords01) {
        List<Map<String, Object>> list = templateFmesService.getTemplateChecksList(keywords01);
        RespBean resp = new RespBean();
        resp.setList(list);
        return resp;
    }
    @RequestMapping(value = "/checks/{ids}", method = RequestMethod.DELETE)
    public RespBean delTemplateChecks(@PathVariable("ids") String ids) {
    	boolean result = templateFmesService.deleteTemplateChecksByIds(ids);
        if (result) {
            return new RespBean("success", "删除成功!(模板药品信息)");
        }
        return new RespBean("error", "删除失败!");
    }
}
