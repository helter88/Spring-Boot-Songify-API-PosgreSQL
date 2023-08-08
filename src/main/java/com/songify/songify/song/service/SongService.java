package com.songify.songify.song.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.songify.songify.song.model.SongEntity;
import com.songify.songify.song.repository.SongRepositoryInMemory;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class SongService {
    private final SongRepositoryInMemory songRepository;

    public SongService(SongRepositoryInMemory songRepository) {
        this.songRepository = songRepository;
    }

     public Map<Integer, SongEntity> getSongs(){
        log.info("retreving all songs");
        return songRepository.findAll();
    }

    public SongEntity getSongById(Integer id) {
        log.info("Finded song on id: " + id);
        return songRepository.findSongById(id);
    }

    public SongEntity addSong(SongEntity song){
        log.info("adding new song: " + song);
        songRepository.saveToDatabase(song);
        return song;
    }

    public SongEntity removeSong(Integer id) {
        log.info("Removing song on id: " + id);
        return songRepository.removeSong(id);
    }

    public SongEntity updateSong(Integer id, SongEntity newSong ){
        log.info("Updating song on id: " + id);
        return songRepository.updateSong(id, newSong);
    }

}
