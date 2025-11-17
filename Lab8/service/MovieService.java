package kz.bitlab.springboot.springbootdemo.service;

import kz.bitlab.springboot.springbootdemo.dto.MoviesDto;

import java.util.List;

public interface MovieService {
    public List<MoviesDto> getList();

    public MoviesDto addApp(MoviesDto dto);

    public MoviesDto getApp(Long id);

    public MoviesDto updateApp(Long id,MoviesDto dto);

    public boolean deleteApp(Long id);
}

