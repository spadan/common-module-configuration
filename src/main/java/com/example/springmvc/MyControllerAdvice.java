package com.example.springmvc;

import com.example.springmvc.Bean.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * 控制器增强
 *
 * @author xiongLiang
 * @date 2018/7/29 16:22
 */
@RestControllerAdvice
public class MyControllerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(MyControllerAdvice.class);

    @ExceptionHandler(BindException.class)
    public JsonResult exceptionHandler(BindException e) {
        logger.info("未捕获异常", e);
        return JsonResult.newBuilder()
                         .code(-1)
                         .errorMessage(e.getBindingResult().getFieldError().getDefaultMessage())
                         .build();
    }


}
