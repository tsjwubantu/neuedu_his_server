package com.neuedu.his.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface DepartmentMapper extends BaseMapper {

	List<Map<String, Object>> getDepartmentList(@Param("start") Integer start, @Param("count") Integer count,
                                                @Param("keywords") String keywords);

	int getDepartmentCount(@Param("keywords") String keywords);

	// 删除
	int deleteDepartmentByIds(@Param("ids") String[] ids);

	// 删除2
	int deleteDepartmentByIds2(@Param("ids") String[] ids);

	// 更新
	int updateDepartmentById(Map<String, Object> department);

	// 添加d
	int addDepartment(Map<String, Object> department);
}
