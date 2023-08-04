package com.songify.songify.song.service;

import com.songify.songify.song.controller.dto.request.PartiallyUpdateRequestSongDTO;
import com.songify.songify.song.controller.dto.request.SongRequestDTO;
import com.songify.songify.song.controller.dto.request.UpdateRequestSongDTO;
import com.songify.songify.song.controller.dto.response.SingleSongResponseDTO;
import com.songify.songify.song.controller.dto.response.UpdateResponseSongDTO;
import com.songify.songify.song.model.SongEntity;

public class SongMapper {
    
    public static SongEntity mapFromSongRequestDTOToSongEntity(SongRequestDTO dto) {
        return new SongEntity(dto.songName(), dto.artist());
    }

     public static SingleSongResponseDTO mapFromSongEntityToSingleSongResponseDTO(SongEntity song) {
        return new SingleSongResponseDTO(song);
    }

     public static SongEntity mapFromUpdateRequestSongDTOToSongEntity(UpdateRequestSongDTO dto) {
        return new SongEntity(dto.songName(), dto.artist());
    }

    public static UpdateResponseSongDTO mapFromSongEntityToUpdateResponseSongDTO(SongEntity song) {
        return new UpdateResponseSongDTO(song);
    }

    public static SongEntity mapFromOldSongToUpdatedSong ( SongEntity song, PartiallyUpdateRequestSongDTO dto) {

        SongEntity.SongEntityBuilder builder = SongEntity.builder();
        
        if(dto.songName() != null){
            builder.songName(dto.songName());
        } else {
            builder.songName(song.songName());
        }
        if(dto.artist() != null){
            builder.artist(dto.artist());
        } else {
            builder.artist(song.artist());
        }
        SongEntity newSong = builder.build();
        return newSong;
    }

}
