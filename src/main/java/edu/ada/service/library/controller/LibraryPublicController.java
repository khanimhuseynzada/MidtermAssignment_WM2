package edu.ada.service.library.controller;

import edu.ada.service.library.model.request.SearchBookParams;
import edu.ada.service.library.model.response.BookResponseDto;
import edu.ada.service.library.model.response.CategoryResponseDto;
import edu.ada.service.library.service.LibraryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library/public")
public class LibraryPublicController {
    private LibraryService libraryService;

    public LibraryPublicController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("/categories")
    public List<CategoryResponseDto> listCategories() {
        return libraryService.listCategories();
    }

    @GetMapping("/books")
    public List<BookResponseDto> listBooks() {
        return libraryService.listBooks();
    }

    @GetMapping("/searchBooks")
    public List<BookResponseDto> searchBooks(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "categoryId", required = false) Long categoryId,
            @RequestParam(name = "author", required = false) String author
    ) {
        return libraryService.searchBooks(
                SearchBookParams
                        .builder()
                        .name(name)
                        .categoryId(categoryId)
                        .author(author)
                        .build()
        );
    }
}
