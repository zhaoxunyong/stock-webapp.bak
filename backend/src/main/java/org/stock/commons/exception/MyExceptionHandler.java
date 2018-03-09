/*
 * 描述： <描述>
 * 修改人： Dave.zhao
 * 修改时间： May 24, 2017
 * 项目： app-core
 */
package org.stock.commons.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.aeasycredit.commons.lang.exception.BaseException;

/**
 * MyExceptionHandler
 * 
 * @author Dave.zhao
 * @version [版本号, May 24, 2017]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@ControllerAdvice
@RestController
class MyExceptionHandler {
    
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String BaseError(HttpServletRequest request, HttpServletResponse response, Exception e) {
        logger.error(e.getMessage(), e);
        return e.toString();
    }
    
}