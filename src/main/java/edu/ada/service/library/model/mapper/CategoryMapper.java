package edu.ada.service.library.model.mapper;

import edu.ada.service.library.model.entity.Category;
import edu.ada.service.library.model.response.CategoryResponseDto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CategoryMapper {
    public static CategoryResponseDto mapEntityToDto(Category category) {
        return CategoryResponseDto
                .builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    public static List<CategoryResponseDto> mapEntitiesToDtos(Iterable<Category> categories) {
        return StreamSupport.stream(categories.spliterator(), false)
                .map(CategoryMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }
}
