package com.threedots.bookstore.service;

import com.threedots.bookstore.model.Book;
import com.threedots.bookstore.repository.BookRepository;
import com.threedots.bookstore.repository.BookShopRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService {
    private BookRepository bookRepository;
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> addNewBook(List<Book> newBook) {
        return bookRepository.saveAll(newBook);
    }

    public Book findBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public List<Book> findBookByTitle(String title) {
        return bookRepository.findByTitleContainsIgnoreCase(title);
    }

    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    public void deleteAllBooks() {
        bookRepository.deleteAll();
    }

    public Book updateBook(Long id, Book newBook) {
        bookRepository.updateBook(id, newBook.getTitle(), newBook.getAuthor(), newBook.getPrice(), newBook.getGenre(), newBook.getYearofPublication(), newBook.getPublisher());
        return bookRepository.findById(id).orElse(null);
    }
}
