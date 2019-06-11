package com.neuedu.his.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Mapper
@Repository
public interface SettleCategoryMapper extends BaseMapper {
	
	//查询结算类别表SettleCategory的记录，无分页
	public List<Map<String, Object>> getSettleCategoryList();
	
	//根据 类别编码 或者 类别名称 查询结算类别表SettleCategory的记录，无分页
	public List<Map<String,Object>> getSettleCategoryList(@Param("keywords") String keywords);
	
//版本2---------------------------------------------------------------------------------------------}
	
    List<Map<String,Object>> getSettleCategoryList(@Param("start") Integer start, @Param("count") Integer count
            , @Param("keywords") String keywords);

    int getSettleCategoryCount(@Param("keywords") String keywords);
    
    //删除
    int deleteSettleCategoryByIds(@Param("ids") String[] ids);
    //删除2
    int deleteSettleCategoryByIds2(@Param("ids") String[] ids);
    
    //更新
    int updateSettleCategoryById(Map<String, Object> settleCategory);
    //添加d
    int addSettleCategory(Map<String, Object> settleCategory);
    
}
