package com.neuedu.his.neupha.service;

import java.util.List;
import java.util.Map;

import com.neuedu.his.bean.RespBean;

public interface DrugService {

	/** 
	 * 添加excel导入的药品信息
	 * @param 药品信息 
	 * @return 增加数据条数 
	 * @auther: 东软教师
	 * @date:   2018-11-7
	 */
	int addDrug(List<Map<String, Object>> drugList);

	/** 
	* 获取药品信息数据列表 
	* @param 当前页 总页数  药品助记码条件 
	* @return 药品信息对象列表 
	* @auther: 东软教师
	* @date:   2018-11-7
	*/
	RespBean getDrugList(Integer page, Integer count, String keywords);

	/** 
	 * 获取符合条件的药品数量
	 * @param 药品助记码 
	 * @return 药品数量 
	 * @auther: 东软教师
	 * @date:   2018-11-7
	 */
	int getDrugCount(String keywords);

	/** 
	 * 添加药品类型
	 * @param 药品信息 
	 * @return 数据库改变行数
	 * @auther: 东软教师
	 * @date:   2018-11-7
	 */
	int addDrugOne(Map<String, Object> map);

	/** 
	 * 修改药品类型
	 * @param 药品信息 
	 * @return 数据库改变行数
	 * @auther: 东软教师
	 * @date:   2018-11-7
	 */
	int updateDrugOne(Map<String, Object> map);

	/** 
	 * 删除药品
	 * @param 药品id
	 * @return 数据库改变行数和传入id数相同为成功
	 * @auther: 东软教师
	 * @date:   2018-11-7
	 */
	boolean delDrug(String ids);

}