package com.songify.songify.song.model;

import lombok.Builder;

@Builder
public record SongEntity(String songName, String artist) {
    
}
