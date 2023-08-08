package com.songify.songify.song.repository;

import java.util.List;
import java.util.Map;

import com.songify.songify.song.model.SongEntity;

public interface SongRepository {
     SongEntity save(SongEntity song);

     List<SongEntity> findAll();
}
