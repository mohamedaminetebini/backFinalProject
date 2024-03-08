package com.example.finalprojetback.RequestsDto;

import lombok.*;

@Builder
@Getter
@Data
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    private String username;
    private String password;
}