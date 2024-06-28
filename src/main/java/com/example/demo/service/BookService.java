package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.repository.IBookDAO;
import com.example.demo.repository.IBookService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookService {
    @Autowired
    private final IBookDAO bookDAO;
    public BookService(IBookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }
    @Override
    @Transactional
    public Optional<Book> getById(int id) {
        return this.bookDAO.getById(id);
    }
    @Override
    @Transactional
    public List<Book> getAll() {
        return this.bookDAO.getAll();
    }
    @Transactional
    public void saveOrUpdate(Book book) {
        this.bookDAO.saveOrUpdate(book);
    }
    @Override
    @Transactional
    public void delete(int id){
        bookDAO.delete(id);
    }
}