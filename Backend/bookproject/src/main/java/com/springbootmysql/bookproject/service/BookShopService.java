package com.springbootmysql.bookproject.service;


import java.util.List;

import com.springbootmysql.bookproject.model.BookShop;

public interface BookShopService {
    List<BookShop> getAllBookShops();
    List<BookShop> getBookShopByShopName(String shopName);
    BookShop getBookShopById(Long id);
    BookShop addNewBookShop(BookShop bookShop);
    BookShop updateBookShop(Long id, BookShop bookShop);
    BookShop addBookByIdToBookShop(Long bookshop_id, Long book_id);
    BookShop deleteBookByIdToBookShop(Long bookshop_id, Long book_id);
    void deleteBookShop(Long id);
    void deleteAllBookShops();
}