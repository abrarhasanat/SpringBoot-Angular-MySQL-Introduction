package com.threedots.bookstore.repository;

import com.threedots.bookstore.model.BookShop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface BookShopRepository extends JpaRepository<BookShop, Long> {
    BookShop findBookShopByShopNameIgnoreCase(String shop_name);
}
