package com.itrexgroup.vydrasergei.bookcatalog.dao;

public class DAOException extends Exception {
    public DAOException(String msg, Exception e) {
        super(msg, e);
    }
}
