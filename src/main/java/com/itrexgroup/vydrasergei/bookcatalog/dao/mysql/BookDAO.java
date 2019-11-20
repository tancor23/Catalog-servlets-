package com.itrexgroup.vydrasergei.bookcatalog.dao.mysql;

import com.itrexgroup.vydrasergei.bookcatalog.dao.DAOException;
import com.itrexgroup.vydrasergei.bookcatalog.dao.GenericDAO;
import com.itrexgroup.vydrasergei.bookcatalog.dao.dbconfig.Datasource;
import com.itrexgroup.vydrasergei.bookcatalog.domain.entity.Book;

public abstract class BookDAO extends GenericDAO<Book, Long> {

    public BookDAO(Datasource datasource) {
        super(datasource);
    }

    public abstract Book getBookInDB(String name, String author) throws DAOException;

    public abstract boolean createBook(String name, String author, int countOfPage) throws DAOException;

}