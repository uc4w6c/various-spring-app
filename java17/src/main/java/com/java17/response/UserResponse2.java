package com.java17.response;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserResponse2 {
    private String name;
    private boolean hasBook;
    private boolean isStudent;

    public UserResponse2(String name, boolean hasBook, boolean isStudent) {
        this.name = name;
        this.hasBook = hasBook;
        this.isStudent = isStudent;
    }

    public String getName() {
        return name;
    }

    public boolean isHasBook() {
        return hasBook;
    }

    @JsonIgnore
    public boolean isStudent() {
        return isStudent;
    }
}
