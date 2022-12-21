package com.springboot.jpa.common;

import com.springboot.jpa.common.exception.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<Map<String,String>> handlerException(RuntimeException e,
                                                               HttpServletRequest request){
        HttpHeaders responseHeaders = new HttpHeaders();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        logger.error("Advice 내 handlerException호출,{},{}",request.getRequestURI(),e.getMessage());
        Map<String,String> map = new HashMap<>();
        map.put("error type",httpStatus.getReasonPhrase());
        map.put("code","400");
        map.put("message",e.getMessage());

        return new ResponseEntity<>(map,responseHeaders,httpStatus);
    }
    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<Map<String,String>> handleException(CustomException e,
                                                              HttpServletRequest request){
        HttpHeaders responseHeaders = new HttpHeaders();
        logger.error("ADVICE 내 HandlerException 호출,{},{},",request.getRequestURI(),
                e.getMessage());
        Map<String,String> map = new HashMap<>();
        map.put("error type",e.getHttpStatusType());
        map.put("code",Integer.toString(e.getHttpStatusCode()));
        map.put("message", e.getMessage());

        return new ResponseEntity<>(map,responseHeaders,e.getHttpStatus());
    }
}
