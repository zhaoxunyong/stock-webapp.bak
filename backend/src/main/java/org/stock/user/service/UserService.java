package org.stock.user.service;

import org.stock.user.model.User;

public interface UserService {
    
    User getUserByLoginName(String loginName);
    
}
