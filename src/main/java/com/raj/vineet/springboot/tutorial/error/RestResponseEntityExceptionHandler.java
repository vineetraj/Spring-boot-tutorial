package com.raj.vineet.springboot.tutorial.error;

import com.raj.vineet.springboot.tutorial.entity.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
/* This class is responsible for sending the response back based on the exception thrown.
* So,this class will handle all the exceptions that we want to send back as response entity
*/
@ControllerAdvice //it will run when exception occurred on controllers
@ResponseStatus //this class will send response status too like 400, 201 etc
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
/* we had created departmentNotfoundException.. so this method is to handle
that particular exception itself. We will write in this method what we want to
give back as response when that exception occurs
*/
    @ExceptionHandler(DepartmentNotFoundException.class)//mark that this method will handle exception of type DepartmentNotFoundException
    //we wrap around this error msg as the ResponseEntity
    public ResponseEntity<ErrorMessage> departmentNotFoundException(DepartmentNotFoundException exception, WebRequest request) {
        //creating error message that we want to send back
        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        //NOTE:We're not doing anything with the 'request' for now.. but can use later if we need to take anything from request
    }
}
