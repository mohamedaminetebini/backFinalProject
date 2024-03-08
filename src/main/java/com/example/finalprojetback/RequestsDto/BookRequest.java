package com.example.finalprojetback.RequestsDto;

import lombok.*;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class BookRequest {

    private String title;
    private String description;
    private String author;
    private String isbn;
    private String noCopies;
    private String category;
    private String price;
}
