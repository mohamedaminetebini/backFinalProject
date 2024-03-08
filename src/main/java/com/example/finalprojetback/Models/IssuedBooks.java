package com.example.finalprojetback.Models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name = "issued_books")
public class IssuedBooks {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String issuedDate;
    @Column(nullable = false)
    private String dueDate;
    @Column(nullable = false)
    private Boolean returned;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    private BookModel book;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserModel user;
    @Column(nullable = false,columnDefinition = "boolean default false")
    private Boolean issued;
}
