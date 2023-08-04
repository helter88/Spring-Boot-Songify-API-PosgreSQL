package com.songify.songify.song.controller.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.songify.songify.song.controller.SongifyController;
import com.songify.songify.song.model.SongNotFoundException;

import lombok.extern.log4j.Log4j2;

@ControllerAdvice(assignableTypes = SongifyController.class)
@Log4j2
public class SongErrorHandler {
    
    @ExceptionHandler(SongNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorSongResponseDTO handleException(SongNotFoundException exception){
        log.warn("Error while accessing song");
        return new ErrorSongResponseDTO(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
