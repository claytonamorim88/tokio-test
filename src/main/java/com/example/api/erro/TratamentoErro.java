package com.example.api.erro;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.List;

@ControllerAdvice
public class TratamentoErro extends ResponseEntityExceptionHandler {



    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ex.printStackTrace();
        return super.handleHttpRequestMethodNotSupported(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ex.printStackTrace();
        ErroBean erroBean = new ErroBean(HttpStatus.NOT_FOUND, "URI nao encontrada", ex);
        return new ResponseEntity<>(erroBean, erroBean.getStatus());
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<Object> tratarNotFound(HttpClientErrorException ex){
        ex.printStackTrace();
        ErroBean erroBean = new ErroBean(HttpStatus.NOT_FOUND, "URI nao encontrada", ex);
        return new ResponseEntity<>(erroBean, erroBean.getStatus());
    }


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> tratarObjetoIncorreto(IllegalArgumentException ex){
        ex.printStackTrace();
        ErroBean erroBean = new ErroBean(HttpStatus.BAD_REQUEST, "Objeto contém dados incompletos ou incorretos", ex);
        return new ResponseEntity<>(erroBean, erroBean.getStatus());
    }

    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity<Object> tratarCampoInvalido(TransactionSystemException ex) {
        ErroBean erroBean;
        Throwable rootCause = ex.getRootCause();
        if(rootCause instanceof ConstraintViolationException){
             erroBean = new ErroBean(HttpStatus.BAD_REQUEST, "JSON mal formatado", rootCause);
        }else
            erroBean = new ErroBean(HttpStatus.BAD_REQUEST, "JSON mal formatado", ex);

        return new ResponseEntity<>(erroBean, erroBean.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErroBean erroBean = new ErroBean(HttpStatus.BAD_REQUEST, "JSON mal formatado", ex);
        return new ResponseEntity<>(erroBean, erroBean.getStatus());
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ex.printStackTrace();

        BindingResult result = ex.getBindingResult();
        StringBuilder builder = new StringBuilder();
        final List<FieldError> fieldErrors = result.getFieldErrors();
        for (FieldError error : fieldErrors) {
            builder.append(error.getField() + " : " + error.getDefaultMessage() + "|");
        }
        ErroBean erroEntity = new ErroBean(HttpStatus.BAD_REQUEST, "Parametro recebido inválido", new Throwable(builder.toString()));
        return new ResponseEntity<>(erroEntity, erroEntity.getStatus());
    }










}
