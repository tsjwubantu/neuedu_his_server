package com.neuedu.his.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface FmeditemMapper extends BaseMapper {
	/** 分页显示 医技信息
	 */
	public List<Map<String, Object>> getFmeList(@Param("start") int start, @Param("count") Integer count,
                                                @Param("keywords") String keywords);

	int getFmeCount(@Param("keywords") String keywords);

	/** 分页显示医技信息 根据“项目类型”RecordType 1-检查2-检验3-处置
	 */
	public List<Map<String, Object>> getFmeListByRecordtype(@Param("start") int start, @Param("count") Integer count,
                                                            @Param("MnemonicCode") String MnemonicCode, @Param("RecordType") String RecordType);

	int getFmeCountByRecordtype(@Param("MnemonicCode") String MnemonicCode, @Param("RecordType") String RecordType);
	
	// -----------------  高   -----------------------
	List<Map<String,Object>> getFmeditemList(@Param("start") Integer start,
                                             @Param("count") Integer count,
                                             @Param("keywords") String keywords);

	int getFmeditemCount(@Param("keywords") String keywords);
	 //删除
    int deleteFmeditemByIds(@Param("ids") String[] ids);
    //删除2
    int deleteFmeditemByIds2(@Param("ids") String[] ids);
    
    //更新
    int updateFmeditemById(Map<String, Object> fmeditem);
    //添加
    int addFmeditem(Map<String, Object> fmeditem);
	
}
