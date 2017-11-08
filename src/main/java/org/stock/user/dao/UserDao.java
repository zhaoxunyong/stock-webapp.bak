package org.stock.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.stock.user.model.User;

@Mapper
public interface UserDao {
    
    /**
     * 
     * 获取用户的授权码列表<br>
     *
     * @param userId 用户编号
     * @return List<String> 授权码列表
     * @version [版本号, May 17, 2017]
     * @author Weng.weng
     */
    List<String> getAuthCodeListByUserId(Long userId);
    
    /**
     * 
     * 获取用户所在职位对于的授权码列表<br>
     *
     * @param userId 用户编号
     * @return List<String> 授权码列表
     * @version [版本号, May 17, 2017]
     * @author Weng.weng
     */
    List<String> getPostAuthCodeListByUserId(Long userId);
    
    /**
     * 
     * 获取用户可访问的菜单编号列表<br>
     *
     * @param userId 用户编号
     * @return List<String> 菜单编号列表
     * @version [版本号, May 17, 2017]
     * @author Weng.weng
     */
    List<String> getMenuIdListByUserId(Long userId);
    
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    User selectByPrimaryKey(Long id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);
    
    /**
     * 
     * 根據部門、姓名查詢用戶信息列表<br>
     *
     * @param name 姓名
     * @param deptId 部門ID
     * @param startNo 開始索引
     * @param pageSize 每頁帶下
     * @return List<User> 用戶信息列表
     * @version [版本号, Aug 22, 2017]
     * @author Weng.weng
     */
    List<User> getUserList(@Param("name")String name, @Param("deptId")Long deptId, 
            @Param("startNo")Integer startNo, @Param("pageSize")Integer pageSize);

    /**
     * 
     * 根據部門、姓名查詢用戶信息列表的總記錄數<br>
     *
     * @param name 姓名
     * @param deptId 部門ID
     * @return int 總記錄數
     * @version [版本号, Aug 22, 2017]
     * @author Weng.weng
     */
    int getUserCount(@Param("name")String name, @Param("deptId")Long deptId);
    
    /**
     * 
     * 通過登錄名查詢用戶信息 <br>
     *
     * @param loginName 登錄名
     * @return User 用戶信息
     * @version [版本号, Aug 22, 2017]
     * @author Weng.weng
     */
    User getUserByLoginName(String loginName);
}
