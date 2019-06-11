package com.neuedu.his.neudoc.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.neuedu.his.neudoc.service.CheckApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neuedu.his.mapper.CheckapplyMapper;
import com.neuedu.his.mapper.ChecktemplateMapper;

/**
 * @auther: 东软教师
 * @date: 2018-11-29
 */
@Service
@Transactional
public class CheckApplyServiceImpl implements CheckApplyService {
	@Autowired
	private CheckapplyMapper checkapplyMapper;
	@Autowired
	private ChecktemplateMapper checktemplateMapper;

	@Override
	public int addCheckApply(Map<String, Object> map) {
		map.remove("ID");
		Object num = map.get("Num");
		if(num!=null && num.toString().length()>0){
			try{
				new Integer(num.toString());
			}catch(Exception e){
				map.put("Num", "1");
			}
		}else {
			map.put("Num", "1");
		}
		Object isUrgent = map.get("IsUrgent");		
		if(isUrgent!=null && isUrgent.toString().length()>0){
			if("true".equals(isUrgent)){
				map.put("IsUrgent", new Integer(1));
			}else{
				map.put("IsUrgent", new Integer(2));
			}
		}
		map.put("CreationTime", new java.util.Date());
		return checkapplyMapper.insertSelective(map);
	}
	
	// 根据 患者挂号ID 检查申请范围  类型为检查  进行查询申请
	@Override
	public List<Map<String, Object>> getCheckApplyList(String registID, String state, String recordType) {
		if(state==null || "".equals(state)) {
			state = "!=0";
		}else {
			state = "="+state;
		}
		return checkapplyMapper.getApplyByRegistAndStateAndType(registID, state, recordType);
	}	
	
	//根据ID，批量删除申请
	@Override
	@Transactional
	public boolean delCheckApply(String ids) {
        String[] split = ids.split(",");
        int result = checkapplyMapper.deleteByPrimaryKey(split);
        return result == split.length;
	}

	@Override
	public int upToSaved(String ids,String state) {
		int num = 0;
		String[] split = ids.split(",");
		Map<String,Object> map = new HashMap<>();
		for(int i=0;i<split.length;i++) {
			map.put("ID", split[i]);
			map.put("State", state);
			num += checkapplyMapper.updateByPrimaryKeySelective(map);
		}
		return num;
	}
}
