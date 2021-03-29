package edu.ada.service.library.controller;

import edu.ada.service.library.model.request.AuthRequestDto;
import edu.ada.service.library.model.response.LoginResponseDto;
import edu.ada.service.library.model.response.RegisterResponseDto;
import edu.ada.service.library.service.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public LoginResponseDto login(@Valid @RequestBody AuthRequestDto requestDto) {
        return authenticationService.login(requestDto);
    }

    @PostMapping("/register")
    public RegisterResponseDto register(@Valid @RequestBody AuthRequestDto requestDto) {
        return authenticationService.register(requestDto);
    }
}
