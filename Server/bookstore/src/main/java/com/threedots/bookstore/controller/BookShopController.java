package com.threedots.bookstore.controller;

import com.threedots.bookstore.model.Book;
import com.threedots.bookstore.model.BookShop;
import com.threedots.bookstore.service.BookShopService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path="/api/bookstore")
@AllArgsConstructor
public class BookShopController {
    private final BookShopService bookShopService;

    @GetMapping("")
    public List<BookShop> getAllBookShops() {
        System.out.println("Get all bookshops");
        return bookShopService.getAllBookShops();
    }

    @PostMapping("")
    public BookShop addNewBookShop(@RequestBody BookShop newBookShop) {
        System.out.println("Add newBookShop: " + newBookShop);
        return bookShopService.addNewBookShop(newBookShop);
    }

    @GetMapping("/{id}")
    public BookShop getBookShopById(@PathVariable Long id) {
        System.out.println("Get bookshop with id: " + id);
        return bookShopService.getBookShopById(id);
    }

    @PutMapping("/{id}")
    public BookShop updateBookShop(@PathVariable Long id, @RequestBody BookShop newBookShop, HttpServletResponse response) throws IOException {
        //update the bookshop with the given id
        System.out.println("Update bookshop with id: " + id);
        BookShop bookShop = bookShopService.getBookShopById(id);
        if (bookShop== null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "BookShop with id " + id + " not found.");
            return null;
        }
        bookShop.setShopName(newBookShop.getShopName());
        bookShop.setLocation(newBookShop.getLocation());
        bookShop.setContact_no(newBookShop.getContact_no());
        return addNewBookShop(bookShop);
    }

    // delete bookshop by id
    @DeleteMapping("/{id}")
    public void deleteBookShopByID(@PathVariable Long id, HttpServletResponse response) throws IOException {
        System.out.println("Delete id: " + id);
        BookShop bookShop = bookShopService.getBookShopById(id);
        if (bookShop == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "BookShop with id " + id + " not found.");
            return;
        }
        bookShopService.deleteBookShopById(id);
    }

    // delete all books
    @DeleteMapping("")
    public void deleteAllBookShops() {
        System.out.println("Delete all bookshops");
        bookShopService.deleteAllBookShops();
    }

    @GetMapping("/title/{title}")
    public BookShop getBookShopByTitle(@PathVariable String title) {
        System.out.println("Get bookshop with title: " + title);
        return bookShopService.getBookShopByTitle(title);
    }

    @GetMapping("/addBook")
    public BookShop addNewBookToBookShopById(@RequestParam(value="bookId") Long bookId, @RequestParam(value="bookShopId") Long bookShopId) {
        System.out.println("Add book with id: " + bookId + " to bookshop with id: " + bookShopId);
        return bookShopService.addNewBookToBookShopById(bookId, bookShopId);
    }
}
