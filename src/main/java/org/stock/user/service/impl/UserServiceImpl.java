package org.stock.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stock.user.dao.UserDao;
import org.stock.user.model.User;
import org.stock.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserDao userDao;

    public User getUserByLoginName(String loginName) {
        return userDao.getUserByLoginName(loginName);
    }

}
