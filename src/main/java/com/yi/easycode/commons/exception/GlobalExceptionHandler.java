package com.yi.easycode.commons.exception;

import com.yi.easycode.commons.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.NestedRuntimeException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author yizhicheng
 * @ClassName GlobalExceptionHandler
 * @Description 全局异常处理
 * @Date 2020/10/21 2:02 PM
 **/
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 针对参数校验 捕获MethodArgumentNotValidException
     * 处理请求参数格式错误 @RequestBody上@validate失败后抛出的异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result handleValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        String message = null;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                message = fieldError.getDefaultMessage();
            }
        }
        log.warn("valid msg : {}",message);
        return Result.fail(message);
    }

    /**
     * 针对参数校验 捕获BindException
     * 处理Get请求中 使用@Valid 验证请求实体校验失败后抛出的异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = BindException.class)
    public Result handleValidException(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        String message = null;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                message = fieldError.getDefaultMessage();
            }
        }
        return Result.fail(message);
    }

    /**
     * JSON解析
     * @param e
     * @return
     */
    @ExceptionHandler(value = NestedRuntimeException.class)
    public Result handlerNestedRuntimeException(NestedRuntimeException e) {
        log.error("NestedRuntimeException 错误信息为,{}",e.getMessage());
        return Result.fail(e.getMessage());
    }

    @ExceptionHandler(value = ApiException.class)
    public Result handleApiException(ApiException e) {
        log.error("ApiException 错误信息为,{}",e.getMessage());
        return Result.fail(e.getCode(),e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public Result exception(Exception e) {
        log.error("exception 错误信息为,{}",e.getMessage());
        return Result.fail(e.getMessage());
    }

}
