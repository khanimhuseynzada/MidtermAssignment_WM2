package edu.ada.service.library.model.repository;

import edu.ada.service.library.model.entity.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findAllByNameContainingAndCategory_idAndAuthorContaining(String name, Long id, String author);
}
