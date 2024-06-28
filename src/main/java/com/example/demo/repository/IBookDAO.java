package com.example.demo.repository;

import com.example.demo.model.Book;

import java.util.List;
import java.util.Optional;

public interface IBookDAO {
    Optional<Book> getById(int id);
    List<Book> getAll();
    void saveOrUpdate(Book book);
    void delete(int id);
}