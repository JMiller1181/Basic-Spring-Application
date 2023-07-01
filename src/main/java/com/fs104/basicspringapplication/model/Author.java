package com.fs104.basicspringapplication.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    @OneToMany
    private List<Book> bookList;
    public Author(String name, List<Book> books) {
        this.name = name;
        this.bookList = books;
    }

    public Author(String name) {
        this.name = name;
    }

    public Author() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> books) {
        this.bookList = books;
    }
}
