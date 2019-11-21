package com.itrexgroup.vydrasergei.bookcatalog.domain.entity;

import java.util.Objects;

public class UserBook {
    private Long userId;
    private Long bookId;

    public UserBook(){}

    public UserBook(Long userId, Long bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserBook userBook = (UserBook) o;
        return Objects.equals(userId, userBook.userId) &&
                Objects.equals(bookId, userBook.bookId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, bookId);
    }

    @Override
    public String toString() {
        return "UserBook{" +
                "userId=" + userId +
                ", bookId=" + bookId +
                '}';
    }
}

