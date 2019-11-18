package com.itrexgroup.vydrasergei.bookcatalog.domain.entity;

import java.util.Objects;

public class Author {
    private long id = -1;
    private String firstName;
    private String lastName;

    public Author(){}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

        Author user = (Author) o;

        if (id != user.id) return false;
        if (!Objects.equals(firstName, user.firstName)) return false;
        return (!Objects.equals(lastName, user.lastName));
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "Id=" + id +
                ", name='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}

