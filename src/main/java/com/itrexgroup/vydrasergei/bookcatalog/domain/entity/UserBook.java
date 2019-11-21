package com.itrexgroup.vydrasergei.bookcatalog.domain.entity;

import java.util.Objects;

public class UserBook {
    private long id = -1;
    private long userId;
    private long bookId;

    public UserBook() {
    }

    public UserBook(long userId, long bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserBook userBook = (UserBook) o;
        return id == userBook.id &&
                userId == userBook.userId &&
                bookId == userBook.bookId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, bookId);
    }

    @Override
    public String toString() {
        return "UserBook{" +
                "id=" + id +
                ", userId=" + userId +
                ", bookId=" + bookId +
                '}';
    }
}