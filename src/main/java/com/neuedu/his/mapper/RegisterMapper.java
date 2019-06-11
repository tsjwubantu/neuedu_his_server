package com.neuedu.his.mapper;

import java.util.List;
import java.util.Map;

import com.neuedu.his.bean.Register;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface RegisterMapper extends BaseMapper{

	/**
	 * 医生看诊，得到“1已挂号/2医生接诊”状态的患者,不分页
	 * @param: 医生ID 患者挂号信息的状态
	 * @return: 符合条件的患者挂号信息对象集合
	 */
    List<Map<String, Object>> getStateRegistersByDoc(@Param("UserID") String UserID, @Param("VisitState") String VisitState);
    
    // ------------ gao	 --------------------
    /** 
     * 查找病历编号最大的挂号信息对象
     * @param: 无
     * @return: 病历编号最大的挂号信息对象
     */ 
	Register getRegisterBean();
	
    /** 
     * 通过病历编号查找指定的挂号信息对象
     * @param: CaseNumber  病历编号
     * @return: 指定的挂号信息对象
     */ 
    Register getRegByCase(String CaseNumber);
    
    /** 
     * 获取挂号信息表中最大的挂号ID
     * @param: 无
     * @return: 最大的ID 整型值
     */
  	int getMaxRegId();
	
    /** 
     * 按条件分页查询挂号记录的方法 
     * @param: page当前页   count每页显示记录数量   keywords keywords2查询关键字
     * @return: 包含查询结果的Map集合
     */ 
    List<Map<String,Object>> getRegisterList(@Param("start") Integer start, @Param("count") Integer count
            , @Param("keywords") String keywords, @Param("keywords2") String keywords2);

    
    /** 
     * 按条件统计记录数量  确定页数最大值时用的
     * @param keywords keywords2查询关键字
     * @return 记录数量  整型
     */ 
    int getRegisterCount(@Param("keywords") String keywords, @Param("keywords2") String keywords2);
    
    
    /** 
     * 按条件统计记录数量  统计以挂号人数用的   已挂号数量统计   按照日期  午别 医生 进行统计
     * @param keywords keywords2 keywords3查询关键字
     * @return 记录数量  整型
     */ 
    int getAlreadyRegisterCount(@Param("keywords") String keywords,
                                @Param("keywords2") String keywords2,
                                @Param("keywords3") String keywords3,
                                @Param("state") int state);
    
          
    /** 
     * 更新挂号信息的方法   只更新 状态字段  患者退号时调用
     * @param: register 包含更新信息的Map集合
     * @return: 更新成功返回 1   更新失败返回 0
     */ 
    int updateRegisterStateById(Map<String, Object> register);
}
