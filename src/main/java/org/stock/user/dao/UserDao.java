package org.stock.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.stock.user.model.User;

@Mapper
public interface UserDao {
    
    List<String> getAuthCodeListByUserId(Long userId);
    
    List<String> getPostAuthCodeListByUserId(Long userId);
    
    List<String> getMenuIdListByUserId(Long userId);
    
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    User selectByPrimaryKey(Long id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);
    
    List<User> getUserList(@Param("name")String name, @Param("deptId")Long deptId, 
            @Param("startNo")Integer startNo, @Param("pageSize")Integer pageSize);

    int getUserCount(@Param("name")String name, @Param("deptId")Long deptId);
    
    User getUserByLoginName(String loginName);
}
