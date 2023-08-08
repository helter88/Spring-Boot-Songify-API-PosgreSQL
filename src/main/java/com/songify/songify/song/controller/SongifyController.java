package com.songify.songify.song.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.songify.songify.song.controller.dto.request.PartiallyUpdateRequestSongDTO;
import com.songify.songify.song.controller.dto.request.SongRequestDTO;
import com.songify.songify.song.controller.dto.request.UpdateRequestSongDTO;
import com.songify.songify.song.controller.dto.response.PartiallyUpdateResponseSongDTO;
import com.songify.songify.song.controller.dto.response.SingleSongResponseDTO;
import com.songify.songify.song.controller.dto.response.SongResponseDTO;
import com.songify.songify.song.controller.dto.response.UpdateResponseSongDTO;
import com.songify.songify.song.model.SongEntity;
import com.songify.songify.song.model.SongNotFoundException;
import com.songify.songify.song.service.SongMapper;
import com.songify.songify.song.service.SongService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/songs")
public class SongifyController {
    
    private final SongService songService;

    public SongifyController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping
    public ResponseEntity<SongResponseDTO> getAllSongs(){
        SongResponseDTO response = new SongResponseDTO(songService.getSongs());
        return ResponseEntity.ok(response);
    }

    // @GetMapping("/{id}")
    // public ResponseEntity<SingleSongResponseDTO> getSongById(@PathVariable Integer id){
    //     SongEntity song = songService.getSongById(id);
    //     if (song == null) {
    //         throw new SongNotFoundException("find song with id: " + id);
    //     } else {
    //         SingleSongResponseDTO response = SongMapper.mapFromSongEntityToSingleSongResponseDTO(song);
    //         return ResponseEntity.ok(response); 
    //     }
    // }

    @PostMapping
    public ResponseEntity<SingleSongResponseDTO> postSong(@RequestBody @Valid SongRequestDTO request){
        SongEntity song = SongMapper.mapFromSongRequestDTOToSongEntity(request);
        songService.addSong(song);
        SingleSongResponseDTO body = SongMapper.mapFromSongEntityToSingleSongResponseDTO(song);
        return ResponseEntity.ok(body);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSong(@PathVariable Integer id){
        if(!songService.getSongs().contains(id)){
            throw new SongNotFoundException("Deleted song with id: " + id);
        }
        // songService.removeSong(id);
        return ResponseEntity.ok("Deleted song with id: " + id); 
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateResponseSongDTO> update(@PathVariable Integer id, @RequestBody @Valid UpdateRequestSongDTO request) {
        if(!songService.getSongs().contains(id)){
            throw new SongNotFoundException("Deleted song with id: " + id);
        }
        SongEntity song = SongMapper.mapFromUpdateRequestSongDTOToSongEntity(request);
        // songService.updateSong(id, song);
        UpdateResponseSongDTO body = SongMapper.mapFromSongEntityToUpdateResponseSongDTO(song);
        return ResponseEntity.ok(body);
    }

    // @PatchMapping("/{id}")
    // public ResponseEntity<PartiallyUpdateResponseSongDTO> partiallyUpdate(@PathVariable Integer id, @RequestBody PartiallyUpdateRequestSongDTO request) {
    //     if(!songService.getSongs().contains(id)){
    //         throw new SongNotFoundException("Deleted song with id: " + id);
    //     }

    //     SongEntity newSong = SongMapper.mapFromOldSongToUpdatedSong(songService.getSongById(id), request);

    //     songService.updateSong(id, newSong);
    //     return ResponseEntity.ok(new PartiallyUpdateResponseSongDTO("You updated song on id: " + id + " with new song: " + newSong));
    // }
}
