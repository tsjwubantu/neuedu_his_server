package com.neuedu.his.mapper;

import java.util.List;
import java.util.Map;

import com.neuedu.his.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface UserMapper extends BaseMapper {
	
    List<Map<String,Object>> getUserList(@Param("start") Integer start, @Param("count") Integer count
            , @Param("keywords") String keywords);

    //新增排班规则时的医生查询
    List<Map<String,Object>> getUserList02(@Param("start") Integer start, @Param("count") Integer count
            , @Param("keywords") String keywords, @Param("keywords2") String keywords2, @Param("keywords3") String keywords3);
    
    int getUserCount(@Param("keywords") String keywords);
    
    int getUserCount02(@Param("keywords") String keywords, @Param("keywords2") String keywords2, @Param("keywords3") String keywords3);
    
    //删除
    int deleteUserByIds(@Param("ids") String[] ids);
    //删除2
    int deleteUserByIds2(@Param("ids") String[] ids);
    //更新
    int updateUserById(Map<String, Object> user);
    //添加
    int addUser(Map<String, Object> user);
    
    //登录
    int login(Map<String, Object> user);
    
    User getUserBean(@Param("keywords") String keywords, @Param("keywords2") String keywords2);
    
}
