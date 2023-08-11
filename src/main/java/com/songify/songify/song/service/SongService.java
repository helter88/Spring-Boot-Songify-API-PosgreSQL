package com.songify.songify.song.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.songify.songify.song.controller.dto.request.PartiallyUpdateRequestSongDTO;
import com.songify.songify.song.controller.dto.response.PartiallyUpdateResponseSongDTO;
import com.songify.songify.song.model.SongEntity;
import com.songify.songify.song.model.SongNotFoundException;
import com.songify.songify.song.repository.SongRepository;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class SongService {
    private final SongRepository songRepository;

    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

     public List<SongEntity> getSongs(Pageable pageable){
        log.info("retreving all songs");
        return songRepository.findAll(pageable);
    }

    public SongEntity getSongById(Long id){
        return songRepository.findById(id)
                .orElseThrow(() -> new SongNotFoundException("Song on id: " + id + " not found"));
    }

    public SongEntity addSong(SongEntity song){
        log.info("adding new song: " + song);
        return songRepository.save(song);
    }

    public void removeSong(Long id) {
        existSongById(id);
        log.info("Removing song on id: " + id);
        songRepository.deleteById(id);
        
    }

    public void existSongById(Long id){
        log.info("Cheacking if exist song on id: " + id);
        if(!songRepository.existsById(id)){
            throw new SongNotFoundException("Song with id: " + id + " not found");
        }
    }

    @Transactional
    public void updateSong(Long id, SongEntity newSong ){
        existSongById(id);
        log.info("Updating song on id: " + id);
        songRepository.updateById(id, newSong);
    }

    @Transactional
    public PartiallyUpdateResponseSongDTO partiallyUpdateSong(Long id, PartiallyUpdateRequestSongDTO request) {
        SongEntity songToUpdate = getSongById(id);
        SongEntity.SongEntityBuilder builder = SongEntity.builder();

        if(request.songName() != null){
            builder.name(request.songName());
        } else {
            builder.name(songToUpdate.getName());
        }

        if(request.artist() != null){
            builder.artist(request.artist());
        } else {
            builder.artist(songToUpdate.getArtist());
        }

        SongEntity newSong = builder.build();
        updateSong(id, newSong);

        return new PartiallyUpdateResponseSongDTO(newSong.getName(), newSong.getArtist());
    }

}