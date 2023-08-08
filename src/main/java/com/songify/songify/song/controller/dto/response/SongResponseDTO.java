package com.songify.songify.song.controller.dto.response;

import java.util.List;

import com.songify.songify.song.model.SongEntity;

public record SongResponseDTO(List<SongEntity> songEntity) {
}
