package com.example.finalprojetback.Models;


import com.example.finalprojetback.Enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Categories {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false,unique = true)
    private String name;
    @Column(nullable = false)
    private  String description;
    @Column(nullable = false)
    private Status status;
    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    private List<BookModel> books;
}
