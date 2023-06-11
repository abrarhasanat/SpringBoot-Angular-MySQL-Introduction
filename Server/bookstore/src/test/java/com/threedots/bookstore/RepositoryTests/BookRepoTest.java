package com.threedots.bookstore.RepositoryTests;

import com.threedots.bookstore.model.Book;
import com.threedots.bookstore.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BookRepoTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testCreateAndDelete() {
        Book book = new Book();
        book.setTitle("The Alchemist");
        book.setAuthor("Paulo Coelho");
        book.setPublisher("HarperCollins");
        book.setPrice(10);
        Book savedBook = bookRepository.save(book);
        Long id = savedBook.getId();
        assertNotNull(savedBook);
        assertEquals(savedBook.getTitle(), book.getTitle());
        bookRepository.delete(savedBook);
        Optional<Book> book1 = bookRepository.findById(id);
        assertFalse(book1.isPresent());
    }

    @Test
    public void testFindAll() {
        // create two books, save them
        // find all Books check if they are present
        Book book1 = new Book();
        book1.setTitle("The Alchemist");
        book1.setAuthor("Paulo Coelho");
        book1.setPublisher("HarperCollins");
        book1.setPrice(10);

        Book book2 = new Book();
        book2.setTitle("Thousand Splendid Suns");
        book2.setAuthor("Paulo Coelho");
        book2.setPublisher("HarperCollins");
        book2.setPrice(12);

        Book savedBook1 = bookRepository.save(book1);
        Book savedBook2 = bookRepository.save(book2);

        List<Book> books = bookRepository.findAll();

        Boolean foundBook1 = false, foundBook2 =  false;
        for (Book b: books) {
            if (b.getId() == savedBook1.getId()) {
                foundBook1 = true;
            }
            if (b.getId() == savedBook2.getId()) {
                foundBook2 = true;
            }
        }

        assertTrue(foundBook1);
        assertTrue(foundBook2);

        bookRepository.delete(savedBook1);
        bookRepository.delete(savedBook2);

    }
}
