package com.songify.songify.song.controller.dto.response;

import java.util.Map;

import com.songify.songify.song.model.SongEntity;

public record SongResponseDTO(Map<Integer, SongEntity> songEntity) {
}
