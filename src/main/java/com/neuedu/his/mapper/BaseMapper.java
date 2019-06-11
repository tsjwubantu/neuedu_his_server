package com.neuedu.his.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

public interface BaseMapper {
    /**
     * 根据ID主键查询数据
     */
    List<Map<String, Object>> selectByPrimaryKey(@Param("ID") String ID);
    
	/**
     * 根据选择的ID删除，可以多选
     */
    int deleteByPrimaryKey(@Param("ids") String[] ids);

    /**
     * 添加一条记录，不建议使用；
     * 建议使用 insertSelective 添加数据
     */
    int insert(Map<String, Object> map);

    /**
     * 添加一条记录，判断数据为空字段，不进行添加
     */
    int insertSelective(Map<String, Object> map);

    /**
     * 修改一条记录，判断数据为空字段，不进行修改
     */
    int updateByPrimaryKeySelective(Map<String, Object> map);

    /**
     * 修改一条记录，不建议使用；
     * 建议使用 updateByPrimaryKeySelective 修改数据
     */
    int updateByPrimaryKey(Map<String, Object> map);
}
