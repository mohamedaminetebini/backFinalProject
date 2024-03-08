package com.example.finalprojetback.Dtos;


import lombok.*;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class BookDto {

    private String title;
    private String description;
    private String author;
    private String isbn;
    private String noCopies;
    private String category;
}
