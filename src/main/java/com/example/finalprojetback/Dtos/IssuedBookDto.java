package com.example.finalprojetback.Dtos;


import lombok.*;

import java.util.Date;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class IssuedBookDto {

    private String issuedBookId;
    private String issuedDate;
    private String dueDate;


    private String bookTitle;
    private String bookAuthor;
    private String bookIsbn;
    private String bookDescription;
    private String bookNoCopies;

    private String username;
    private String fullName;
    private Boolean issued;
    private String phoneNumber;
    private Boolean returned;
}
