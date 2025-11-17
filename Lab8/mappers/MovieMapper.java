package kz.bitlab.springboot.springbootdemo.mappers;


import kz.bitlab.springboot.springbootdemo.entities.Movies;
import kz.bitlab.springboot.springbootdemo.dto.MoviesDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ActorMapper.class, ReviewMapper.class})
public interface MovieMapper {
    MoviesDto toDto(Movies movie);
    Movies toEntity(MoviesDto dto);
}

