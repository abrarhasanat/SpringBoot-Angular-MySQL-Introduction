package com.springbootmysql.bookproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springbootmysql.bookproject.model.Book;
import com.springbootmysql.bookproject.model.BookShop;

public interface BookRepository extends JpaRepository<Book, Long>{
    List<Book> findByTitleContainsIgnoreCase(String title);
    
    @Query("SELECT bs FROM BookShop bs JOIN bs.books b WHERE b.id = :id")
    List<BookShop> findBookShopsByBookId(@Param("id") Long bookId);
    @Query("SELECT b.id FROM Book b")
    List<Long> findAllBookIds();
}
