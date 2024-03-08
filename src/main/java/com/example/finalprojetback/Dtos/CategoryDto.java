package com.example.finalprojetback.Dtos;


import com.example.finalprojetback.Enums.Status;
import lombok.*;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class CategoryDto {

    private String name;
    private String code;

}
