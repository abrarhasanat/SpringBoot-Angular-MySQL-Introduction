package com.threedots.bookstore.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table (name="Books")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private int price;

    private int YearofPublication;

    private String author;

    private String publisher;

    private String genre;

}
