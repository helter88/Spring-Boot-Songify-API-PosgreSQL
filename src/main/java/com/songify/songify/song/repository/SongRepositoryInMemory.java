package com.songify.songify.song.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.songify.songify.song.model.SongEntity;

public class SongRepositoryInMemory implements SongRepository {

     Map<Long, SongEntity> dataBase = new HashMap<>(Map.of(
        1L, new SongEntity("Americana", "The Offspring"),
        2L, new SongEntity("Smells like ten spirit", "Nirvana"),
        3L, new SongEntity("Enter Sandman", "Metallica")
        ));
    
    @Override
    public SongEntity save(SongEntity song) {
        dataBase.put((dataBase.size()+1L), song);
        return song;
    }

    @Override
    public List<SongEntity> findAll() {
        return dataBase.values().stream().toList();
    }

    public SongEntity findSongById(Long id) {
        return dataBase.get(id);
    }
    @Override
    public void deleteById (Long id) {
        dataBase.remove(id);
    }

    @Override
    public boolean existsById(Long id) {
        return dataBase.containsKey(id);
    }

    public SongEntity updateSong (Long id, SongEntity newSong) {
        return dataBase.put(id, newSong);
    }
}
