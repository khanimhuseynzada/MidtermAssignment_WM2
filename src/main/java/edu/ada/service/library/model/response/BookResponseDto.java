package edu.ada.service.library.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookResponseDto {
    private Long id;
    private String name;
    private String author;
    private LocalDate publishDate;
    private CategoryResponseDto category;
    private boolean isAvailable;
    private String pickerEmail;
}
