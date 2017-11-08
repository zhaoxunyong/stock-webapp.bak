package org.stock.custom;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.aeasycredit.commons.lang.exception.SessionException;

@ControllerAdvice
class ApplicationControllerExceptionHandler {
    
    private final Log log = LogFactory.getLog(this.getClass());
    
    @ExceptionHandler(value = SessionException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public Map<String, Object> sessionLostError(HttpServletRequest request, HttpServletResponse response, SessionException exception) {
//        response.setStatus(900);
//        return ReturnResponse.builderFailed(e.getCode(), e.getMessage());
        
        log.info("session lost", exception);
        
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("result", "failure");
        resultMap.put("errorCode", "900001");
        return resultMap;
    }
    
}