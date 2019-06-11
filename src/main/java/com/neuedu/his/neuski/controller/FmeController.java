package com.neuedu.his.neuski.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neuedu.his.bean.RespBean;
import com.neuedu.his.neuski.service.FmeService;

/**
 * 操作医技Controller
 * @auther: 东软教师
 * @date: 2018-11-29
 */
@RestController
@RequestMapping("/neuski/fme")
public class FmeController {
	@Autowired
	private FmeService fmeService;

	@RequestMapping(value = "/listPage", method = RequestMethod.GET)
	public RespBean getFmesPage(@RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize, String keywords01) {

		RespBean resp = fmeService.getFmeList(pageNumber, pageSize, keywords01);
		return resp;
	}
	@RequestMapping(value = "/listPageByRecordtype", method = RequestMethod.GET)
	public RespBean listPageByRecordtype(@RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize, String MnemonicCode,String RecordType) {

		RespBean resp = fmeService.getFmeListByRecordtype(pageNumber, pageSize, MnemonicCode,RecordType);
		return resp;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public RespBean addNewFme(@RequestParam Map<String, Object> map) {
		int result = fmeService.addFmeOne(map);
		if (result == 1) {
			return new RespBean("success", "添加成功!(医技信息)");
		}
		return new RespBean("error", "添加失败!");
	}

	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public RespBean updateFme(@RequestParam Map<String, Object> map) {
		int result = fmeService.updateFmeOne(map);
		if (result == 1) {
			return new RespBean("success", "修改成功!(医技信息)");
		}
		return new RespBean("error", "修改失败!");
	}

	@RequestMapping(value = "/{ids}", method = RequestMethod.DELETE)
	public RespBean delFmeOne(@PathVariable("ids") String ids) {
		boolean result = fmeService.delFme(ids);
		if (result) {
			return new RespBean("success", "删除成功!(医技信息)");
		}
		return new RespBean("error", "删除失败!");
	}
}
