package com.example.finalprojetback.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name = "books")
public class BookModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false,unique = true)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String isbn;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String noCopies;
    @Column(nullable = false)
    private String price;
    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<IssuedBooks> issuedBooks;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties("books")
    private Categories category;
}
