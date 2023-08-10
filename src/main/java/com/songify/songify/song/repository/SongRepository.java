package com.songify.songify.song.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.songify.songify.song.model.SongEntity;

public interface SongRepository extends Repository<SongEntity, Long> {

     SongEntity save(SongEntity song);

     List<SongEntity> findAll();

     boolean existsById(Long id);

     void deleteById(Long id); 

     @Modifying
     @Query("UPDATE SongEntity s SET s.name = :#{#newSong.name}, s.artist = :#{#newSong.artist} WHERE s.id = :id")
     void updateById(Long id, SongEntity newSong);
}
