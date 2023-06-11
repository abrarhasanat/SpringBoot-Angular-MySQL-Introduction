package com.threedots.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="BookShop")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookShop implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shop_id;

    private String shopName;
    private String location;

    @ManyToMany()
    @JoinTable(
            name = "book_bookshop",
            inverseJoinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            joinColumns = @JoinColumn(name = "shop_id", referencedColumnName = "shop_id"))
    private Set<Book> bookList = new HashSet<>();
    private String contact_no;

}
