package com.crio.jukebox.commands;

import com.crio.jukebox.dtos.UserPlayedSongDto;
import com.crio.jukebox.exceptions.PlayListNotFoundException;
import com.crio.jukebox.exceptions.UserNotFoundException;
import com.crio.jukebox.services.IUserPlayListService;

import java.util.List;
import java.util.StringJoiner;

public class PlayPlayListCommand implements  ICommand{
    private final IUserPlayListService userPlayListService;

    public PlayPlayListCommand(IUserPlayListService userPlayListService) {
        this.userPlayListService = userPlayListService;
    }
    
    @Override
    public void execute(List<String> tokens) {
        try{
            UserPlayedSongDto playedSongDto = userPlayListService.setCurrentPlayList(tokens.get(1),tokens.get(2));
            
            
            System.out.println("Current Song Playing");
            System.out.println("Song -" +" "+ playedSongDto.getSongname());
            System.out.println("Album -" +" "+ playedSongDto.getAlbumname());
            System.out.println("Artists -" +" "+ playedSongDto.getArtistname().toString());


        }catch (UserNotFoundException e){
            System.out.println("User Not Found");
        }catch (PlayListNotFoundException e){
            System.out.println("Playlist is empty");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}