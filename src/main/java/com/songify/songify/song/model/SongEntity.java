package com.songify.songify.song.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Entity
@Getter
@Setter
@Table(name="song")
public class SongEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String artist;

    public SongEntity() {

    }

    public SongEntity(String name, String artist) {
        this.name = name;
        this.artist = artist;
    }

    public SongEntity(Long id, String name, String artist) {
        this.id = id;
        this.name = name;
        this.artist = artist;
    }
}
