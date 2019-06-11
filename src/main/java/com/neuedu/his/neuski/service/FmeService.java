package com.neuedu.his.neuski.service;

import java.util.Map;

import com.neuedu.his.bean.RespBean;

public interface FmeService {

	/** 
	* 获取医技信息数据列表 
	* @param 当前页 总页数  药品助记码条件 
	* @return 医技信息对象列表 
	* @auther: 东软教师
	* @date:   2018-11-7
	*/
	RespBean getFmeList(Integer page, Integer count, String keywords);
	

	/** 
	 * 添加医技类型
	 * @param 医技信息 
	 * @return 数据库改变行数
	 * @auther: 东软教师
	 * @date:   2018-11-7
	 */
	int addFmeOne(Map<String, Object> map);

	/** 
	 * 修改医技类型
	 * @param 医技信息 
	 * @return 数据库改变行数
	 * @auther: 东软教师
	 * @date:   2018-11-7
	 */
	int updateFmeOne(Map<String, Object> map);

	/** 
	 * 删除医技
	 * @param 医技id
	 * @return 数据库改变行数和传入id数相同为成功
	 * @auther: 东软教师
	 * @date:   2018-11-7
	 */
	boolean delFme(String ids);

	/** 
	* 获取医技信息数据列表 
	* @param 当前页 总页数  药品助记码条件  项目类型
	* @return 医技信息对象列表 
	* @auther: 东软教师
	* @date:   2018-11-7
	*/
	RespBean getFmeListByRecordtype(Integer page, Integer count, String MnemonicCode, String RecordType);

}