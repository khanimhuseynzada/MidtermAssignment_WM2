package edu.ada.service.library.service.impl;

import edu.ada.service.library.controller.ErrorHandler;
import edu.ada.service.library.exception.InvalidCredentialsException;
import edu.ada.service.library.model.entity.User;
import edu.ada.service.library.model.repository.UserRepository;
import edu.ada.service.library.model.request.AuthRequestDto;
import edu.ada.service.library.model.response.LoginResponseDto;
import edu.ada.service.library.model.response.RegisterResponseDto;
import edu.ada.service.library.service.AuthenticationService;
import edu.ada.service.library.utils.Crypt;
import edu.ada.service.library.utils.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private Logger logger = LoggerFactory.getLogger(ErrorHandler.class);

    private UserRepository userRepository;

    public AuthenticationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public LoginResponseDto login(AuthRequestDto requestDto) {
        logger.info("*** Started service login ***");

        User user = userRepository.findByEmail(requestDto.getEmail());

        if (user == null) {
            throw new InvalidCredentialsException("*** User not found ***");
        }

        String encrypted = Crypt.encrypt(requestDto.getPassword());
        if (!user.getPassword().equals(encrypted)) {
            throw new InvalidCredentialsException("*** You have entered wrong password ***");
        }

        user.setToken(Token.generateRandomToken());
        userRepository.save(user);

        logger.info("*** Finished Service Login ***");
        return LoginResponseDto
                .builder()
                .token(user.getToken())
                .build();
    }

    @Override
    public RegisterResponseDto register(AuthRequestDto requestDto) {
        logger.info("*** Started Service Login ***");

        String encrypted = Crypt.encrypt(requestDto.getPassword());
        User user = User
                .builder()
                .email(requestDto.getEmail())
                .password(encrypted)
                .build();

        try {
            userRepository.save(user);

        } catch (Exception e) {
            throw new InvalidCredentialsException("*** User exists in the database ***");
        }

        logger.info("*** Finished Service Login ***");
        return RegisterResponseDto
                .builder()
                .message("Success")
                .build();
    }
}
