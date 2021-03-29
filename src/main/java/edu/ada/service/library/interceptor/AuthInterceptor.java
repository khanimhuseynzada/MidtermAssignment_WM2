package edu.ada.service.library.interceptor;

import edu.ada.service.library.exception.UnauthorizedException;
import edu.ada.service.library.model.entity.User;
import edu.ada.service.library.model.repository.UserRepository;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthInterceptor implements HandlerInterceptor {
    private UserRepository userRepository;

    public AuthInterceptor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        if (token == null) {
            throw new UnauthorizedException("Unauthorized");
        }

        User user = userRepository.findByToken(token);
        if (user == null) {
            throw new UnauthorizedException("Unauthorized");
        }

        request.setAttribute("user", user);

        return true;
    }
}
