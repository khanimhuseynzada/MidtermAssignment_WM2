package edu.ada.service.library.model.repository;

import edu.ada.service.library.model.entity.Book;
import edu.ada.service.library.model.entity.Pickup;
import edu.ada.service.library.model.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PickupRepository extends CrudRepository<Pickup, Long> {
    Pickup findByBookAndDropOffFalse(Book book);
    Pickup findByBookAndUserAndDropOffFalse(Book book, User user);
}
