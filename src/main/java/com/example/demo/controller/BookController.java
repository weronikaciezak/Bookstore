package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "/book")
public class BookController {
    @Autowired
    BookService bookService;

    @RequestMapping(path = {"/main", "/", "/index"}, method = RequestMethod.GET)
    public String main(Model model) {
        model.addAttribute("books", this.bookService.getAll());
        return "index";
    }

    @RequestMapping(path = "/add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("book", new Book());
        return "book-form-add";
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute Book book) {
        this.bookService.saveOrUpdate(book);
        return "redirect:/book/main";
    }

    @RequestMapping(path = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable int id, Model model) {
        Optional<Book> bookOpt = this.bookService.getById(id);
        if (bookOpt.isEmpty()) {
            return "redirect:/book/main";
        }
        model.addAttribute("book", bookOpt.get());
        return "book-form-edit";
    }

    @RequestMapping(path = "/update/{id}", method = RequestMethod.POST)
    public String update(@PathVariable int id, @ModelAttribute Book book) {
        //book.setId(id);
        this.bookService.saveOrUpdate(book);
        return "redirect:/book/main";
    }

    @PostMapping("/delete")
    public String deleteBook(@RequestParam int id) {
        bookService.delete(id);
        return "redirect:/book/main";
    }
}

