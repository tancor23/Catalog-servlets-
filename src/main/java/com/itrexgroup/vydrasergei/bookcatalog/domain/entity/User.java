package com.itrexgroup.vydrasergei.bookcatalog.domain.entity;

import java.util.Objects;

public class User {
    private long id = -1;
    private String firstName;
    private String lastName;
    private String createdAt;

    public User (){}

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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
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
        User user = (User) o;
        return id == user.id &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(createdAt, user.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, createdAt);
    }

    @Override
    public String toString() {
        return "User{" +
                "Id=" + id +
                ", name='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }
}

