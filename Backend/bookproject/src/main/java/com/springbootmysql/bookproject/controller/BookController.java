package com.springbootmysql.bookproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springbootmysql.bookproject.model.Book;
import com.springbootmysql.bookproject.service.BookService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")


public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping("/books")
    public List<Book> addNewBook(@RequestBody List<Book> newBooks) {
        return bookService.addNewBook(newBooks);
    }

    @GetMapping("/books/title")
    public List<Book> findBookByTitle(@RequestParam("title") String title) {
        return bookService.findBookByTitle(title);
    }
    
    @GetMapping("/books/{id}")
    public Book findBookByID(@PathVariable Long id) {
        return bookService.findBookByID(id);
    }

    @PutMapping("/books/{id}")
    public Book updateBookByID(@PathVariable Long id, @RequestBody Book book) {
        return bookService.updateBookByID(id, book);
    }

    @DeleteMapping("/books/{id}")
    public void deleteBookByID(@PathVariable Long id) {
        bookService.deleteBookByID(id);
    }

    @DeleteMapping("/books")
    public void deleteAllBooks() {
        bookService.deleteAllBooks();
    }
}