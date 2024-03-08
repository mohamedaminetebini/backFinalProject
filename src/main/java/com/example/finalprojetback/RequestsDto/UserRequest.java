package com.example.finalprojetback.RequestsDto;


import com.example.finalprojetback.Enums.Status;
import lombok.*;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class UserRequest {
    private String username;
    private String password;
    private Status status;
    private String fullName;
    private  String joinDate;
    private String phoneNumber;
}
