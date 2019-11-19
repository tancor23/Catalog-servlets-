package com.itrexgroup.vydrasergei.bookcatalog.service.exception;

public class ServiceException extends Exception {
    public ServiceException(){
        super();
    }

    public ServiceException(String msg){
        super(msg);
    }

    public ServiceException(Exception e){
        super(e);
    }

    public ServiceException(String msg, Exception e){
        super(msg, e);
    }
}
