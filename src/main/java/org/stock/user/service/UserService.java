package org.stock.user.service;

import org.stock.user.model.User;

/**
 * 
 * 用户信息相关操作业务接口<br>
 * 
 * 
 * @author Weng.weng
 * @version [版本号, May 17, 2017]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface UserService {
    
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
