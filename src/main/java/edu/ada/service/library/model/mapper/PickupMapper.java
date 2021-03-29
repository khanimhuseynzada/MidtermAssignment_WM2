package edu.ada.service.library.model.mapper;

import edu.ada.service.library.model.entity.Pickup;
import edu.ada.service.library.model.response.PickupResponseDto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class PickupMapper {
    public static PickupResponseDto mapEntityToDto(Pickup pickup) {
        return PickupResponseDto
                .builder()
                .id(pickup.getId())
                .dropOff(pickup.isDropOff())
                .book(BookMapper.mapEntityToDto(pickup.getBook()))
                .createdAt(pickup.getCreatedAt())
                .updatedAt(pickup.getUpdatedAt())
                .build();
    }

    public static List<PickupResponseDto> mapEntitiesToDtos(Iterable<Pickup> pickups) {
        return StreamSupport.stream(pickups.spliterator(), false)
                .map(PickupMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }
}
