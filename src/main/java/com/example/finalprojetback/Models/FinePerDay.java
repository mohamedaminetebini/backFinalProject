package com.example.finalprojetback.Models;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name = "fpd")
public class FinePerDay {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false)
    private String finePerDay;
    @Column(nullable = false)
    private Date date;
}
