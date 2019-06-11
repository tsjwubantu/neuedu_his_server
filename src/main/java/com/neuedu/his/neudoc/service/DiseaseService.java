package com.neuedu.his.neudoc.service;

import com.neuedu.his.bean.RespBean;

public interface DiseaseService {

	/**
	 * 根据"中医/西医"得到所有疾病，分页
	 * 1-西医
	 * 2-中医
	 */
	public RespBean getDiseaseByType(Integer page, Integer count, String DicaType) ;

	RespBean getDiseaseByNameOrIcd(String DicaType, String keywords01);

}