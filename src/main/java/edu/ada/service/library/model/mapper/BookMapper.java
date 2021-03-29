package edu.ada.service.library.model.mapper;

import edu.ada.service.library.model.entity.Book;
import edu.ada.service.library.model.entity.Pickup;
import edu.ada.service.library.model.response.BookResponseDto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class BookMapper {
    public static BookResponseDto mapEntityToDto(Book book) {
        Pickup pickup = book.getCurrentPickup();

        return BookResponseDto
                .builder()
                .id(book.getId())
                .name(book.getName())
                .author(book.getAuthor())
                .publishDate(book.getPublishDate())
                .category(CategoryMapper.mapEntityToDto(book.getCategory()))
                .isAvailable(pickup == null)
                .pickerEmail(pickup != null ? pickup.getUser().getEmail() : null)
                .build();
    }

    public static List<BookResponseDto> mapEntitiesToDtos(Iterable<Book> categories) {
        return StreamSupport.stream(categories.spliterator(), false)
                .map(BookMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }
}
