package kz.bitlab.springboot.springbootdemo.mappers;
import kz.bitlab.springboot.springbootdemo.dto.ActorDto;
import kz.bitlab.springboot.springbootdemo.entities.Actors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface ActorMapper {

    @Mapping(target = "movies", ignore = true)
    Actors toEntity(ActorDto dto);

    ActorDto toDto(Actors actor);
}

