package com.springbootmysql.bookproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootmysql.bookproject.model.Book;
import com.springbootmysql.bookproject.model.BookShop;
import com.springbootmysql.bookproject.repository.BookRepository;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> addNewBook(List<Book> newBooks) {
        return bookRepository.saveAll(newBooks);
    }

    public List<Book> findBookByTitle(String title) {
        return bookRepository.findByTitleContainsIgnoreCase(title);
    }

    public Book findBookByID(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public Book updateBookByID(Long id, Book book) {
        Book existingBook = bookRepository.findById(id).orElse(null);
        
        if (existingBook != null) {
            if(book.getTitle() != null){
                existingBook.setTitle(book.getTitle());
            }
            if(book.getAuthor() != null){
                existingBook.setAuthor(book.getAuthor());
            }
            if(book.getCountry() != null){
                existingBook.setCountry(book.getCountry());
            }
            if(book.getLanguage() != null){
                existingBook.setLanguage(book.getLanguage());
            }
            if(book.getLink() != null){
                existingBook.setLink(book.getLink());
            }
            if(book.getPages() != 0){
                existingBook.setPages(book.getPages());
            }
            if(book.getYear() != 0){
                existingBook.setYear(book.getYear());
            }
            if(book.getPrice() != 0){
                existingBook.setPrice(book.getPrice());
            }
            return bookRepository.save(existingBook);
        }
        return null;
    }

    public void deleteBookByID(Long id) {
        List<BookShop> bookshops = bookRepository.findBookShopsByBookId(id);
        
        for (BookShop bookShop : bookshops) {
            bookShop.getBooks().removeIf(book -> book.getId().equals(id));
        }
        
        bookRepository.deleteById(id);
    }

    public void deleteAllBooks() {
        List<Long> ids = bookRepository.findAllBookIds();
        
        for (Long id : ids) {
            List<BookShop> bookshops = bookRepository.findBookShopsByBookId(id);
        
            for (BookShop bookShop : bookshops) {
                bookShop.getBooks().removeIf(book -> book.getId().equals(id));
            }
        }

        bookRepository.deleteAll();
    }
}