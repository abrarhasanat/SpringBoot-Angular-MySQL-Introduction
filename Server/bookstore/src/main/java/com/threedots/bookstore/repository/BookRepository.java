package com.threedots.bookstore.repository;

import com.threedots.bookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContainsIgnoreCase(String title);

    @Transactional
    @Modifying
    @Query("UPDATE Book b SET b.title = ?2, b.author = ?3, b.price = ?4, b.genre = ?5," +
            " b.YearofPublication = ?6, b.publisher = ?7 WHERE b.id = ?1")
    int updateBook(Long id, String title, String author, int price,
                    String genre, int YearofPublication, String publisher);

}
