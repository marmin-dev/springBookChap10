package com.springboot.jpa.Controller;

import com.springboot.jpa.common.Constants;
import com.springboot.jpa.common.exception.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/exception")
public class ExceptionController {

    private final Logger logger = LoggerFactory.getLogger(ExceptionController.class);
    @GetMapping
    public void getRuntimeException(){
        throw new RuntimeException("getRuntime 메서드 호출!");
    }
    @GetMapping("/custom")
    public void getCustomException() throws CustomException{
        throw new CustomException(Constants.ExceptionClass.PRODUCT,HttpStatus.BAD_REQUEST,"getCustomException" +
                "메서드 호출");
    }
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<Map<String,String>> handleException(RuntimeException e,
                                                              HttpServletRequest request){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpStatus status = HttpStatus.BAD_REQUEST;

        logger.error("클래스 내 HandlerException 호출,{},{}",request.getRequestURI()
                ,e.getMessage());
        Map<String,String> map =new HashMap<>();
        map.put("errorType",status.getReasonPhrase());
        map.put("code","400");
        map.put("message",e.getMessage());

        return new ResponseEntity<>(map,responseHeaders,status);
    }

}
