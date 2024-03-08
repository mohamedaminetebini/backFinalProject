package com.example.finalprojetback.RequestsDto;


import lombok.*;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class PasswordRequest {
    private String username;
    private String oldPassword;
    private String newPassword;
}
