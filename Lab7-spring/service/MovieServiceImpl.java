package kz.bitlab.springboot.springbootdemo.service;

import kz.bitlab.springboot.springbootdemo.entities.Movies;
import kz.bitlab.springboot.springbootdemo.entities.MoviesDto;
import kz.bitlab.springboot.springbootdemo.repositories.MoviesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class MovieServiceImpl implements MovieService{
    private  final MoviesRepository moviesRepository;

    public List<MoviesDto> getList(){
        List<Movies> movies=moviesRepository.findAll();

        List<MoviesDto> moviesDtoList=new ArrayList<>();

        movies.forEach(movie->{
            MoviesDto movieDto=toDto(movie);
            moviesDtoList.add(movieDto);
        });
        return moviesDtoList;
    }

    public MoviesDto addApp(MoviesDto dto){
        Movies movie=toEntity(dto);
        Movies createdMovie=moviesRepository.save(movie);

        MoviesDto movieDto=toDto(createdMovie);
        return movieDto;

    }

    public MoviesDto getApp(Long id){
        Movies checkMovie=moviesRepository.findById(id).orElse(null);
        if (Objects.isNull(checkMovie)){
            return null;
        }
        MoviesDto movieDto=toDto(checkMovie);
        return movieDto;

    }

    public MoviesDto updateApp(Long id,MoviesDto dto){
        MoviesDto checkMovie=getApp(id);
        if (Objects.isNull(checkMovie)){
            return null;
        }
        Movies movie=toEntity(dto);
        Movies updatedMovie=moviesRepository.save(movie);
        MoviesDto movieDto=toDto(updatedMovie);
        return movieDto;
    }

    public boolean deleteApp(Long id){
        MoviesDto checkMovie=getApp(id);
        if (Objects.isNull(checkMovie)){
            return false;
        }
        moviesRepository.deleteById(id);
        return true;
    }

    private MoviesDto toDto(Movies movie){
        MoviesDto movieDto=MoviesDto
                .builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .year(movie.getYear())
                .genre(movie.getGenre())
                .build();
        return movieDto;
    }

    private Movies toEntity(MoviesDto moviesDto){
        Movies movies=new Movies();
        movies.setId(moviesDto.getId());
        movies.setTitle(moviesDto.getTitle());
        movies.setDescription(moviesDto.getDescription());
        movies.setYear(moviesDto.getYear());
        movies.setGenre(moviesDto.getGenre());
        return movies;
    }




}
