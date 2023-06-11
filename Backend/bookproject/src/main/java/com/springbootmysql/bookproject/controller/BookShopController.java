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

import com.springbootmysql.bookproject.model.BookShop;
import com.springbootmysql.bookproject.service.BookShopService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class BookShopController {

    @Autowired
    private BookShopService bookShopService;

    public BookShopController(BookShopService bookShopService) {
        this.bookShopService = bookShopService;
    }

    @GetMapping("/bookshops")
    public List<BookShop> getAllBookShops() {
        return bookShopService.getAllBookShops();
    }

    @PostMapping("/bookshops")
    public BookShop addNewBookShop(@RequestBody BookShop bookShop) {
        return bookShopService.addNewBookShop(bookShop);
    }

    @GetMapping("/bookshops/shopName")
    public List<BookShop> getBookShopByShopName(@RequestParam("shopName") String shopName) {
        return bookShopService.getBookShopByShopName(shopName);
    }

    @GetMapping("/bookshops/{id}")
    public BookShop getBookShopById(@PathVariable Long id) {
        return bookShopService.getBookShopById(id);
    }

    @PutMapping("/bookshops/{id}")
    public BookShop updateBookShop(@PathVariable Long id, @RequestBody BookShop bookShop) {
        return bookShopService.updateBookShop(id, bookShop);
    }

    @PutMapping("/bookshops/addBook")
    public BookShop addBookByIdToBookShop(@RequestParam("bookshop_id") Long bookshop_id, @RequestParam("book_id") Long book_id) {
        return bookShopService.addBookByIdToBookShop(bookshop_id, book_id);
    }

    
    @PutMapping("/bookshops/removeBook")
    public BookShop deleteBookByIdToBookShop(@RequestParam("bookshop_id") Long bookshop_id, @RequestParam("book_id") Long book_id) {
        return bookShopService.deleteBookByIdToBookShop(bookshop_id, book_id);
    }


    @DeleteMapping("/bookshops/{id}")
    public void deleteBookShop(@PathVariable Long id) {
        bookShopService.deleteBookShop(id);
    }

    @DeleteMapping("/bookshops")
    public void deleteAllBookShops() {
        bookShopService.deleteAllBookShops();
    }
}