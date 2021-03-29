package edu.ada.service.library.model.repository;

import edu.ada.service.library.model.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
    User findByToken(String token);
}
