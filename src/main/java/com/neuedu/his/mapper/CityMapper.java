package com.neuedu.his.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Created by neuedu on 2018/11/22.
 */
@Mapper
@Repository
public interface CityMapper {
	
    List<Map<String,Object>> getAllCity();

}
