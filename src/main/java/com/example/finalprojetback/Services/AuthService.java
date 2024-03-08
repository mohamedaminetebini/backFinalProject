package com.example.finalprojetback.Services;

import com.example.finalprojetback.Enums.Roles;
import com.example.finalprojetback.Enums.Status;
import com.example.finalprojetback.Models.UserModel;
import com.example.finalprojetback.Reposetories.UserRpo;
import com.example.finalprojetback.RequestsDto.AuthenticationResponse;
import com.example.finalprojetback.RequestsDto.LoginRequest;
import com.example.finalprojetback.RequestsDto.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRpo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(UserRequest request) {
        System.out.println(request.getPassword());
        UserModel user = UserModel.builder().profileStatus(Status.ACTIVE).joinDate(request.getJoinDate()).fullName(request.getFullName()).phoneNumber(request.getPhoneNumber()).username(request.getUsername()).password(passwordEncoder.encode(request.getPassword())).roles(Roles.ROLE_STUDENT).build();
        userRepo.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = userRepo.findByUsername(request.getUsername()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}