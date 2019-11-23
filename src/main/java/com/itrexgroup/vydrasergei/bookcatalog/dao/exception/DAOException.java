package com.itrexgroup.vydrasergei.bookcatalog.dao.exception;

public class DAOException extends Exception {

    public DAOException(String msg, Exception e) {
        super(msg, e);
    }

    public DAOException(String msg) {
        super(msg);
    }

}
