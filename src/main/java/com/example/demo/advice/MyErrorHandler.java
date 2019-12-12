package com.example.demo.advice;

import com.example.demo.Error.MyError;
import okhttp3.Request;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class MyErrorHandler {
    @ExceptionHandler(Exception.class)
    ModelAndView handleError(HttpServletRequest httpServletRequest, Throwable e, Model model){
//        HttpStatus httpStatus=getStatus(httpServletRequest);
        System.out.println("start MyError");
        if(e instanceof MyError){
            model.addAttribute("Aerror",e.getMessage());
        }else {
            System.out.println("its not MyError");
            model.addAttribute("Aerror","哔哔哔");

        }
        return  new ModelAndView("error");
    }

//    private HttpStatus getStatus(HttpServletRequest httpServletRequest){
//        Integer statusCode  =(Integer) httpServletRequest.getAttribute("javax.servlet.error.status_code");
//        if(statusCode==null){
//            return HttpStatus.INTERNAL_SERVER_ERROR;
//        }
//        else return  HttpStatus.valueOf(statusCode);
//    }
}
