package com.itrexgroup.vydrasergei.bookcatalog.domain.entity;

import java.util.Objects;

public class Book {
    private long id = -1;
    private String name;
    private long page;

    public Book(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book user = (Book) o;

        if (id != user.id) return false;
        if (!Objects.equals(name, user.name)) return false;
        return (!Objects.equals(page, user.page));
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (int)(page);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "Id=" + id +
                ", name='" + name + '\'' +
                ", page='" + page + '\'' +
                '}';
    }
}

