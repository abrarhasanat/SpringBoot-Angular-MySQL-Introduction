package com.threedots.bookstore.service;

import com.threedots.bookstore.model.Book;
import com.threedots.bookstore.model.BookShop;
import com.threedots.bookstore.repository.BookRepository;
import com.threedots.bookstore.repository.BookShopRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookShopService {
    private BookShopRepository bookShopRepository;
    private BookRepository bookRepository;

    // get all bookshops
    public List<BookShop> getAllBookShops() {
        return bookShopRepository.findAll();
    }

    // get bookshop by id
    public BookShop getBookShopById(Long id) {
        return bookShopRepository.findById(id).orElse(null);
    }

    // add new bookshop
    public BookShop addNewBookShop(BookShop newBookShop) {
        return bookShopRepository.save(newBookShop);
    }

    // add new book with id to bookshop by id
    public BookShop addNewBookToBookShopById(Long bookId, Long bookShopId) {
        BookShop bookShop = bookShopRepository.findById(bookShopId).orElse(null);
        Book book = bookRepository.findById(bookId).orElse(null);
        System.out.println(bookShop);
        System.out.println(book);
        if (bookShop != null && book != null) {
            bookShop.getBookList().add(book);
            return bookShopRepository.save(bookShop);
        }
        System.out.println("BookShop or Book not found");
        return null;
    }

    public void deleteBookShopById(Long id) {
        bookShopRepository.deleteById(id);
    }

    public void deleteAllBookShops() {
        bookShopRepository.deleteAll();
    }

    public void deleteBookByID(Long bookId) {
        // get all bookshops
        // for each bookshop remove this bookid
        // save bookshop
        List<BookShop> bookShopList = bookShopRepository.findAll();
        for (BookShop bookShop : bookShopList) {
            bookShop.getBookList().removeIf(book -> book.getId().equals(bookId));
            bookShopRepository.save(bookShop);
        }
    }
    public BookShop getBookShopByTitle(String shopName) {
        return bookShopRepository.findBookShopByShopNameIgnoreCase(shopName);
    }
}
