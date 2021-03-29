package edu.ada.service.library.model.repository;

import edu.ada.service.library.model.entity.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> { }
