package com.springbootmysql.bookproject.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springbootmysql.bookproject.model.BookShop;

@Repository
public interface BookShopRepository extends JpaRepository<BookShop, Long> {
    List<BookShop> findByShopNameContainsIgnoreCase(String shopName);
    @Query("SELECT bs.id FROM BookShop bs")
    List<Long> findAllBookShopIds();
}