package com.songify.songify.song.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.songify.songify.song.model.SongEntity;

public interface SongRepository extends Repository<SongEntity, Long> {

     SongEntity save(SongEntity song);

     List<SongEntity> findAll();

     boolean existsById(Long id);

     void deleteById(Long id); 
}
