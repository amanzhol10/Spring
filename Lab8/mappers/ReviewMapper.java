package kz.bitlab.springboot.springbootdemo.mappers;

import kz.bitlab.springboot.springbootdemo.dto.ReviewDto;
import kz.bitlab.springboot.springbootdemo.entities.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    @Mapping(target = "movie", ignore = true)
    Review toEntity(ReviewDto dto);

    ReviewDto toDto(Review review);
}
