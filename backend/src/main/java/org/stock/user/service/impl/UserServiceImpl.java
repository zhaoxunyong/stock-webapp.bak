package org.stock.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stock.user.dao.UserMapper;
import org.stock.user.model.User;
import org.stock.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserMapper userMapper;

    public User getUserByLoginName(String loginName) {
        return userMapper.getUserByLoginName(loginName);
    }

}
