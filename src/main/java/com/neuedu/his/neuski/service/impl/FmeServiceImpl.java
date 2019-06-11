package com.neuedu.his.neuski.service.impl;

import java.util.Map;

import com.neuedu.his.neuski.service.FmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neuedu.his.bean.RespBean;
import com.neuedu.his.mapper.FmeditemMapper;

@Service
public class FmeServiceImpl implements FmeService {
	
	@Autowired
	private FmeditemMapper FmeditemMapper;

	@Override
	@Transactional(readOnly=true)
	public RespBean getFmeList( Integer page, Integer count,String keywords) {
		RespBean resp = new RespBean();
		if(count == 10 || count<=1){
			count = resp.defaultSize;
		}
	    int start = (page - 1) * count;
	    resp.setList(FmeditemMapper.getFmeList(start, count, keywords));
	    resp.setTotalCount(FmeditemMapper.getFmeCount(keywords));	        
	    return resp;
	}
	@Override
	@Transactional(readOnly=true)
	public RespBean getFmeListByRecordtype( Integer page, Integer count,String MnemonicCode,String RecordType) {
		RespBean resp = new RespBean();
		if(count == 10 || count<=1){
			count = resp.defaultSize;
		}
	    int start = (page - 1) * count;
	    resp.setList(FmeditemMapper.getFmeListByRecordtype(start, count, MnemonicCode,RecordType));
	    resp.setTotalCount(FmeditemMapper.getFmeCountByRecordtype(MnemonicCode,RecordType));	        
	    return resp;
	}

	@Override
	@Transactional
	public int addFmeOne(Map<String, Object> map) {
		return FmeditemMapper.insertSelective(map);
	}

	@Override
	@Transactional
	public int updateFmeOne(Map<String, Object> map) {
		return FmeditemMapper.updateByPrimaryKeySelective(map);
	}

	@Override
	@Transactional
	public boolean delFme(String ids) {
        String[] split = ids.split(",");
        int result = FmeditemMapper.deleteByPrimaryKey(split);
        return result == split.length;
	}
}
