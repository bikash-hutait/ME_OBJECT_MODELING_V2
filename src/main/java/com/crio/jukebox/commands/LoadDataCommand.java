package com.crio.jukebox.commands;

import com.crio.jukebox.services.ISongService;

import java.util.List;

public class LoadDataCommand implements ICommand{

    private final ISongService songService;

    public LoadDataCommand(ISongService songService) {
        this.songService = songService;
    }

    @Override
    public void execute(List<String> tokens) {
        try{
            songService.loadSong(tokens.get(1));
            System.out.println("Songs Loaded successfully");
        }catch (Exception e){
            System.out.println("Error occurred while loading a song");
        }

    }
}