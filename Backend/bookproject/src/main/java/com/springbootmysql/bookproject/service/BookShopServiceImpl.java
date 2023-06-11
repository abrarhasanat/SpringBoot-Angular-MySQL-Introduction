package com.springbootmysql.bookproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootmysql.bookproject.model.Book;
import com.springbootmysql.bookproject.model.BookShop;
import com.springbootmysql.bookproject.repository.BookRepository;
import com.springbootmysql.bookproject.repository.BookShopRepository;

@Service
public class BookShopServiceImpl implements BookShopService {

    @Autowired
    private BookShopRepository bookShopRepository;

    @Autowired
    private BookRepository bookRepository;

    public BookShopServiceImpl(BookShopRepository bookShopRepository) {
        this.bookShopRepository = bookShopRepository;
    }

    @Override
    public List<BookShop> getAllBookShops() {
        return bookShopRepository.findAll();
    }
    @Override
    public List<BookShop> getBookShopByShopName(String shopName){
        return bookShopRepository.findByShopNameContainsIgnoreCase(shopName);
    }

    @Override
    public BookShop getBookShopById(Long id) {
        return bookShopRepository.findById(id).orElse(null);
    }

    @Override
    public BookShop addNewBookShop(BookShop bookShop) {
        return bookShopRepository.save(bookShop);
    }

    @Override
    public BookShop updateBookShop(Long id, BookShop bookShop) {
        BookShop existingBookShop = bookShopRepository.findById(id).orElse(null);
        if (existingBookShop != null) {
            if(bookShop.getShopNumber() != null){
                existingBookShop.setShopNumber(bookShop.getShopNumber());
            }
            if(bookShop.getShopName() != null){
                existingBookShop.setShopName(bookShop.getShopName());
            }
            if(bookShop.getLocation() != null){
                existingBookShop.setLocation(bookShop.getLocation());
            }
            if(bookShop.getBooks() != null){
                existingBookShop.setBooks(bookShop.getBooks());
            }
            if(bookShop.getContactNumber() != null){
                existingBookShop.setContactNumber(bookShop.getContactNumber());
            }
            if(bookShop.getEmail() != null){
                existingBookShop.setEmail(bookShop.getEmail());
            }
            return bookShopRepository.save(existingBookShop);
        }
        return null;
    }

    @Override
    public BookShop addBookByIdToBookShop(Long bookshop_id, Long book_id){
        Book book = bookRepository.findById(book_id).orElse(null);

        BookShop bookShop = bookShopRepository.findById(bookshop_id).orElse(null);
        
        if(!bookShop.getBooks().contains(book)){
            bookShop.getBooks().add(book);
        }

        return bookShopRepository.save(bookShop);
    }

    @Override
    public BookShop deleteBookByIdToBookShop(Long bookshop_id, Long book_id){
        Book book = bookRepository.findById(book_id).orElse(null);

        BookShop bookShop = bookShopRepository.findById(bookshop_id).orElse(null);

        if(bookShop.getBooks().contains(book)){
            bookShop.getBooks().remove(book);
        }
        
        return bookShopRepository.save(bookShop);       
    }

    @Override
    public void deleteBookShop(Long id) {
        BookShop bookShop = bookShopRepository.findById(id).orElse(null);
        bookShop.getBooks().clear();
        bookShopRepository.deleteById(id);
    }

    @Override
    public void deleteAllBookShops(){
        List<Long> ids = bookShopRepository.findAllBookShopIds();

        for (Long id : ids) {
            BookShop bookShop = bookShopRepository.findById(id).orElse(null);
            bookShop.getBooks().clear();
        }

        bookShopRepository.deleteAll();

    }

}