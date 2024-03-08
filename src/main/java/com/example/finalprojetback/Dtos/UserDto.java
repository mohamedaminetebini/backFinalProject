package com.example.finalprojetback.Dtos;

import com.example.finalprojetback.Enums.Status;
import lombok.*;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class UserDto {

    private String username;
    private Status status;
    private String fullName;
    private String joinDate;
    private String phoneNumber;

}
