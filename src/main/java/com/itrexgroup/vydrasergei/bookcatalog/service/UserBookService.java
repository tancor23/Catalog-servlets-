package com.itrexgroup.vydrasergei.bookcatalog.service;

import com.itrexgroup.vydrasergei.bookcatalog.domain.entity.Book;
import com.itrexgroup.vydrasergei.bookcatalog.domain.entity.User;
import com.itrexgroup.vydrasergei.bookcatalog.domain.entity.UserBook;
import com.itrexgroup.vydrasergei.bookcatalog.service.exception.ServiceException;

public interface UserBookService {

    UserBook create(User user, Book book) throws ServiceException;

    UserBook create(Long userId, Long bookId) throws ServiceException;

}
