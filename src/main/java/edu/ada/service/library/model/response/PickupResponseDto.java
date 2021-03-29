package edu.ada.service.library.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PickupResponseDto {
    private Long id;
    private boolean dropOff;
    private BookResponseDto book;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
