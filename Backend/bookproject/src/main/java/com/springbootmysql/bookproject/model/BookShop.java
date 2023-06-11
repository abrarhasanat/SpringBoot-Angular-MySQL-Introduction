package com.springbootmysql.bookproject.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "bookshops")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookShop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "shop_number")
    private String shopNumber;

    @Column(name = "shop_name")
    private String shopName;

    private String location;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "bookshop_books",
            joinColumns = @JoinColumn(name = "bookshop_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> books;

    @Column(name = "contact_number")
    private String contactNumber;

    private String email;
}