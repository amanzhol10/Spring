package kz.bitlab.springboot.springbootdemo.service;

import kz.bitlab.springboot.springbootdemo.entities.Movies;
import kz.bitlab.springboot.springbootdemo.dto.MoviesDto;
import kz.bitlab.springboot.springbootdemo.mappers.MovieMapper;
import kz.bitlab.springboot.springbootdemo.repositories.MoviesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MoviesRepository moviesRepository;
    private final MovieMapper movieMapper;

    @Override
    public List<MoviesDto> getList() {
        return moviesRepository
                .findAll()
                .stream()
                .map(movieMapper::toDto)
                .toList();
    }

    @Override
    public MoviesDto addApp(MoviesDto dto) {
        Movies movie = movieMapper.toEntity(dto);
        return movieMapper.toDto(moviesRepository.save(movie));
    }

    @Override
    public MoviesDto getApp(Long id) {
        Movies movie = moviesRepository.findById(id).orElse(null);
        return movie == null ? null : movieMapper.toDto(movie);
    }

    @Override
    public MoviesDto updateApp(Long id, MoviesDto dto) {
        if (!moviesRepository.existsById(id)) {
            return null;
        }
        Movies movie = movieMapper.toEntity(dto);
        movie.setId(id);
        return movieMapper.toDto(moviesRepository.save(movie));
    }

    @Override
    public boolean deleteApp(Long id) {
        if (!moviesRepository.existsById(id)) return false;
        moviesRepository.deleteById(id);
        return true;
    }
}

