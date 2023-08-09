package com.songify.songify.song.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.songify.songify.song.model.SongEntity;
import com.songify.songify.song.repository.SongRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class SongService {
    private final SongRepository songRepository;

    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

     public List<SongEntity> getSongs(){
        log.info("retreving all songs");
        return songRepository.findAll();
    }

    // public SongEntity getSongById(Integer id) {
    //     log.info("Finded song on id: " + id);
    //     return songRepository.findSongById(id);
    // }

    public SongEntity addSong(SongEntity song){
        log.info("adding new song: " + song);
        return songRepository.save(song);
    }

    public void removeSong(Long id) {
        log.info("Removing song on id: " + id);
        songRepository.deleteById(id);
        
    }

    public boolean existSongById(Long id){
        log.info("Cheacking if exist song on id: " + id);
        return songRepository.existsById(id);
    }

    // public SongEntity updateSong(Integer id, SongEntity newSong ){
    //     log.info("Updating song on id: " + id);
    //     return songRepository.updateSong(id, newSong);
    // }

}
