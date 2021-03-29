package edu.ada.service.library.service;

import edu.ada.service.library.model.request.AuthRequestDto;
import edu.ada.service.library.model.response.LoginResponseDto;
import edu.ada.service.library.model.response.RegisterResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {
    RegisterResponseDto register(AuthRequestDto requestDto);
    LoginResponseDto login(AuthRequestDto requestDto);
}
