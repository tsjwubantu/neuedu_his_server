package com.neuedu.his.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.neuedu.his.bean.Register;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface PatientCostsMapper extends BaseMapper {
	
	//记录数量统计  交费/退费  时用的
    int getPatientCostsCount(@Param("keywords") String keywords, @Param("state") int state);
    
    //记录数量统计  交费/退费  时用的
    int getPatientCostsCount02(@Param("keywords") String keywords, @Param("state") int state);
	
	//记录查询  交费/退费  时用的
    List<Map<String,Object>> getPatientCostsList(@Param("start") Integer start,
                                                 @Param("count") Integer count,
                                                 @Param("keywords") String keywords,
                                                 @Param("state") int state);
    
    //记录查询  交费/退费  时用的
    List<Map<String,Object>> getTab(@Param("keywords") String keywords,
                                    @Param("state") int state);

    
    //患者退费时 确认身份查询用的
    Register getPatient(@Param("keywords") String keywords);
    
    //记录数量统计  患者查询用的
    int getPatientQueryCount(@Param("keywords") String keywords, @Param("keywords2") String keywords2,
                             @Param("keywords3") String keywords3);
    
    //患者查询用的
    List<Map<String,Object>> getPatientQuery(@Param("start") Integer start, @Param("count") Integer count
            , @Param("keywords") String keywords, @Param("keywords2") String keywords2, @Param("keywords3") String keywords3);

    
    
    //消费金额统计  患者查询时用的
    float getPatientSumFee(@Param("keywords") String keywords, @Param("keywords2") String keywords2,
                           @Param("keywords3") String keywords3);
    
    //添加
    int addPatientCosts(Map<String, Object> patientCosts);
    
    //更新处方表   交费时使用
    int updatePrescriptionById(Map<String, Object> prescription);
    
    //更新CheckApply (患者检查/检验/处置申请表)表的 交费状态 3-已交费    6-已退费   交费时使用
    int updateCheckApplyById(Map<String, Object> prescription);
    
}
