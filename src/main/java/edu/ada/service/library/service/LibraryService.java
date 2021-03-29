package edu.ada.service.library.service;

import edu.ada.service.library.model.entity.User;
import edu.ada.service.library.model.request.PickupRequestDto;
import edu.ada.service.library.model.request.SearchBookParams;
import edu.ada.service.library.model.response.BookResponseDto;
import edu.ada.service.library.model.response.CategoryResponseDto;
import edu.ada.service.library.model.response.PickupResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LibraryService {
    List<CategoryResponseDto> listCategories();
    List<BookResponseDto> listBooks();
    List<BookResponseDto> searchBooks(SearchBookParams params);
    PickupResponseDto pickupBook(PickupRequestDto requestDto, User user);
    PickupResponseDto dropOffBook(PickupRequestDto requestDto, User user);
}
