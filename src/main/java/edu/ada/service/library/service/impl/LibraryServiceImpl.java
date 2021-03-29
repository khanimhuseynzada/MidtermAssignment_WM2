package edu.ada.service.library.service.impl;

import edu.ada.service.library.controller.ErrorHandler;
import edu.ada.service.library.exception.InvalidPickupException;
import edu.ada.service.library.exception.NotExistsException;
import edu.ada.service.library.model.entity.Book;
import edu.ada.service.library.model.entity.Pickup;
import edu.ada.service.library.model.entity.User;
import edu.ada.service.library.model.mapper.BookMapper;
import edu.ada.service.library.model.mapper.CategoryMapper;
import edu.ada.service.library.model.mapper.PickupMapper;
import edu.ada.service.library.model.repository.BookRepository;
import edu.ada.service.library.model.repository.CategoryRepository;
import edu.ada.service.library.model.repository.PickupRepository;
import edu.ada.service.library.model.request.PickupRequestDto;
import edu.ada.service.library.model.request.SearchBookParams;
import edu.ada.service.library.model.response.BookResponseDto;
import edu.ada.service.library.model.response.CategoryResponseDto;
import edu.ada.service.library.model.response.PickupResponseDto;
import edu.ada.service.library.service.LibraryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryServiceImpl implements LibraryService {
    private Logger logger = LoggerFactory.getLogger(ErrorHandler.class);

    private BookRepository bookRepository;
    private CategoryRepository categoryRepository;
    private PickupRepository pickupRepository;

    public LibraryServiceImpl(
            BookRepository bookRepository,
            CategoryRepository categoryRepository,
            PickupRepository pickupRepository
    ) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
        this.pickupRepository = pickupRepository;
    }

    @Override
    public List<CategoryResponseDto> listCategories() {
        logger.info("Service List categories: started");

        var categories = categoryRepository.findAll();

        logger.info("Service List categories: ended");
        return CategoryMapper.mapEntitiesToDtos(categories);
    }

    @Override
    public List<BookResponseDto> listBooks() {
        logger.info("Service List books: started");

        var books = bookRepository.findAll();

        logger.info("Service List books: ended");
        return BookMapper.mapEntitiesToDtos(books);
    }

    @Override
    public List<BookResponseDto> searchBooks(SearchBookParams params) {
        logger.info("*** Search books started ***");

        List<Book> books;

            books = bookRepository.findAllByNameContainingAndCategory_idAndAuthorContaining(
                    params.getName(),
                    params.getCategoryId(),
                    params.getAuthor()
            );

        logger.info("*** Search books finished ***");
        return BookMapper.mapEntitiesToDtos(books);
    }


    @Override
    public PickupResponseDto pickupBook(PickupRequestDto requestDto, User user) {
        logger.info("Search Pick up book: started");

        Book book = bookRepository
                .findById(requestDto.getBookId())
                .orElseThrow(() -> new NotExistsException("*** Not Found ***"));

        Pickup pickup = pickupRepository.findByBookAndDropOffFalse(book);

        if (pickup != null) {
            if (pickup.getUser().getId().equals(user.getId())) {
                throw new InvalidPickupException("*** Book is picked up ***");
            }
            throw new InvalidPickupException("*** Unavailable book ***");
        }

        var newPickup = Pickup
                .builder()
                .book(book)
                .user(user)
                .build();

        pickupRepository.save(newPickup);

        logger.info("*** Search for pickup finished ***");
        return PickupMapper.mapEntityToDto(newPickup);
    }

    @Override
    public PickupResponseDto dropOffBook(PickupRequestDto requestDto, User user) {
        logger.info("*** Search for dropoff started ***");

        Book book = bookRepository
                .findById(requestDto.getBookId())
                .orElseThrow(() -> new NotExistsException("*** Book does not exist in the database ***"));

        Pickup pickup = pickupRepository.findByBookAndUserAndDropOffFalse(book, user);

        if (pickup == null) {
            throw new InvalidPickupException("*** Book does not belong to this user ***");
        }

        pickup.setDropOff(true);
        pickupRepository.save(pickup);

        logger.info("*** Search for dropoff finished ***");
        return PickupMapper.mapEntityToDto(pickup);
    }
}

