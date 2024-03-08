package com.example.finalprojetback.RequestsDto;


import lombok.*;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class IssuedBooksRequest {
    private String dueDate;
    private String user;
    private String book;
}
