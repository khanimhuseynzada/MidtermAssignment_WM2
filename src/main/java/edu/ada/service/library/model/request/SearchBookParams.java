package edu.ada.service.library.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchBookParams {
    private String name;
    private Long categoryId;
    private String author;
}
