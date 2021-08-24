package com.edanur.demo.exception;
import com.edanur.demo.web.response.ResponseMsg;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value= NotFoundException.class)
    public ResponseMsg handleNotFoundException(NotFoundException e){
        return new ResponseMsg(e.getMessage());

    }
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value= Exception.class)
    public ResponseMsg handleException(Exception e){
        return new ResponseMsg(e.getMessage());

    }

}
