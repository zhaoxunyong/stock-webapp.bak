package org.stock.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.stock.user.model.User;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    User selectByPrimaryKey(Long id);
    
    User getUserByLoginName(@Param("loginName") String loginName);

    List<User> selectAll();

    int updateByPrimaryKey(User record);
}