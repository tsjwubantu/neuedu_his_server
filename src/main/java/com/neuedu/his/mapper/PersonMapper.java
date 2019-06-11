package com.neuedu.his.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.neuedu.his.bean.Person;
import org.springframework.stereotype.Repository;

/**
 * Created by neuedu on 2018/11/22.
 */
@Mapper
@Repository
public interface PersonMapper {
	
	List<Person> getPersonList(@Param("start") Integer start, @Param("count") Integer count
            , @Param("keywords") String keywords, @Param("keywords2") String keywords2);
	
	int getPersonCount(@Param("keywords") String keywords, @Param("keywords2") String keywords2);
	
	List<Map<String,Object>> getAllPerson();

    int deletePersonByIds(@Param("ids") String[] ids);

    int updatePersonById(Person person);

    int addPerson(Person person);
}
