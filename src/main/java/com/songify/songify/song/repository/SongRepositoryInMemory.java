package com.songify.songify.song.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.songify.songify.song.model.SongEntity;

@Repository
public class SongRepositoryInMemory implements SongRepository {

     Map<Integer, SongEntity> dataBase = new HashMap<>(Map.of(
        1, new SongEntity("Americana", "The Offspring"),
        2, new SongEntity("Smells like ten spirit", "Nirvana"),
        3, new SongEntity("Enter Sandman", "Metallica")
        ));
    
    @Override
    public SongEntity save(SongEntity song) {
        dataBase.put(dataBase.size()+1, song);
        return song;
    }

    @Override
    public List<SongEntity> findAll() {
        return dataBase.values().stream().toList();
    }

    public SongEntity findSongById(Integer id) {
        return dataBase.get(id);
    }

    public SongEntity removeSong (Integer id) {
        return dataBase.remove(id);
    }

    public SongEntity updateSong (Integer id, SongEntity newSong) {
        return dataBase.put(id, newSong);
    }
}
