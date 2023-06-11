package com.threedots.bookstore.controller;

import com.threedots.bookstore.model.Book;
import com.threedots.bookstore.service.BookService;
import com.threedots.bookstore.service.BookShopService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path="/api/books")
@AllArgsConstructor
public class BookController {
    private final BookService bookService;
    private final BookShopService bookShopService;

    @GetMapping("")
    public List<Book> getAllBooks() {
        System.out.println("Get all books");
        return bookService.getAllBooks();
    }

    @PostMapping("")
    public List<Book> addNewBook(@RequestBody List<Book> newBook) {
        System.out.println("Add newBook: " + newBook);
        return bookService.addNewBook(newBook);
    }

    @GetMapping("/title/{title}")
    public List<Book> findBookByTitle(@PathVariable String title) {
        System.out.println("Find title: " + title);
        return bookService.findBookByTitle(title);
    }

    @GetMapping("/{id}")
    public Book findBookById(@RequestParam Long id) {
        System.out.println("Find id: " + id);
        Book book = bookService.findBookById(id);
        if (book == null) {
            return null;
        }
        return book;
    }

    @DeleteMapping("/{id}")
    public void deleteBookByID(@PathVariable Long id, HttpServletResponse response) throws IOException {
        System.out.println("Delete id: " + id);
        Book book = bookService.findBookById(id);
        if (book == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Book with id " + id + " not found.");
            return;
        }
        bookShopService.deleteBookByID(id);
        bookService.deleteBookById(id);
    }

    @DeleteMapping("")
    public void deleteAllBooks() {
        System.out.println("Delete all books");
        bookService.deleteAllBooks();
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book newBook, HttpServletResponse response) throws IOException {
        System.out.println("Update book: " + newBook);
        Book book = bookService.findBookById(id);
        if (book == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Book with id " + id + " not found.");
            return null;
        }
        return bookService.updateBook(id, newBook);
    }
}
