package org.stock.user.api.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.stock.fetch.api.dto.StockDataDto;
import org.stock.user.api.UserApi;
import org.stock.user.api.dto.UserDto;
import org.stock.user.model.User;
import org.stock.user.service.UserService;

import com.aeasycredit.commons.lang.constant.CommonConstant;
import com.aeasycredit.commons.lang.exception.SessionException;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserApiImpl implements UserApi {
    
    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private HttpServletRequest request;

    @Override
    @PostMapping("/login")
    public UserDto login(String loginName, String password) {
        User user = userService.getUserByLoginName(loginName);
        if(user == null || !password.equals(user.getPassword())) {
            throw new SessionException("登錄失敗!");
        }
        UserDto userDto = modelMapper.map(user, UserDto.class);
        HttpSession session = request.getSession();
        session.setAttribute(CommonConstant.SESSION_CONTEXT, userDto);
        return userDto;
    }
    
}
