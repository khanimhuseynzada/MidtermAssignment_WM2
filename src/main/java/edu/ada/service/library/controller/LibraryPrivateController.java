package edu.ada.service.library.controller;

import edu.ada.service.library.model.entity.User;
import edu.ada.service.library.model.request.PickupRequestDto;
import edu.ada.service.library.model.response.PickupResponseDto;
import edu.ada.service.library.service.LibraryService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/library/private")
public class LibraryPrivateController {

    private LibraryService libraryService;
    public LibraryPrivateController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @PostMapping("/pickup")
    public PickupResponseDto pickupBook(
            @Valid @RequestBody PickupRequestDto requestDto,
            @RequestAttribute(name = "user") User user //replaces token
    ) {
        return libraryService.pickupBook(requestDto, user);
    }

    @PostMapping("/dropoff")
    public PickupResponseDto dropOffBook(
            @Valid @RequestBody PickupRequestDto requestDto,
            @RequestAttribute(name = "user") User user
    ) {
        return libraryService.dropOffBook(requestDto, user);
    }
}
