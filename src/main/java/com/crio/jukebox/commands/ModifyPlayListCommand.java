package com.crio.jukebox.commands;

import com.crio.jukebox.entities.PlayList;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.exceptions.InvalidOperationException;
import com.crio.jukebox.exceptions.PlayListNotFoundException;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.exceptions.UserNotFoundException;
import com.crio.jukebox.services.IUserPlayListService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModifyPlayListCommand implements ICommand{
    private final IUserPlayListService userPlayListService;

    public ModifyPlayListCommand(IUserPlayListService userPlayListService) {
        this.userPlayListService = userPlayListService;
    }

    @Override
    public void execute(List<String> tokens) {
        List<String> csong=new ArrayList<String>();
        for(int i=4;i<tokens.size();i++)
            csong.add(tokens.get(i));
        PlayList returnPlayList;
        if(tokens.get(1).equals("ADD-SONG")){
            try {
                returnPlayList = userPlayListService.addSongToPlayList(tokens.get(2), tokens.get(3), csong);
                List<Song> songsID=returnPlayList.getSongs();
                StringBuilder sb = new StringBuilder();
                String delimiter = "";
                for (Song s : songsID) {
                    sb.append(delimiter).append(s.getId());
                    delimiter = " ";
                }              

                System.out.println("Playlist ID -" +" "+ returnPlayList.getId());
                System.out.println("Playlist Name -" +" "+ returnPlayList.getName());
                System.out.println("Song IDs -" +" "+ sb.toString());              
                

            }catch (UserNotFoundException e){
                System.out.println("User Not Found");
            }catch (SongNotFoundException e){
                System.out.println("Song Not Found");
            }catch (PlayListNotFoundException e){
                System.out.println("PlayList Not Found");
            }catch (InvalidOperationException e){
                System.out.println("Some Requested Songs Not Available. Please try again.");
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }else{
            try{
                returnPlayList =userPlayListService.deleteSongFromPlayList(tokens.get(2),tokens.get(3),csong);
                
                List<Song> songsID=returnPlayList.getSongs();
                StringBuilder sb = new StringBuilder();
                String delimiter = "";
                for (Song s : songsID) {
                    sb.append(delimiter).append(s.getId());
                    delimiter = " ";
                }              

                System.out.println("Playlist ID -" +" "+ returnPlayList.getId());
                System.out.println("Playlist Name -" +" "+ returnPlayList.getName());
                System.out.println("Song IDs -" +" "+ sb.toString());              
                
            }catch (UserNotFoundException e){
                System.out.println("User Not Found");
            }catch (SongNotFoundException e){
                System.out.println("Some Requested Songs for Deletion are not present in the playlist. Please try again.");
            }catch (PlayListNotFoundException e){
                System.out.println("PlayList Not Found");
            }catch (InvalidOperationException e){
                System.out.println("Some Requested Songs Not Available. Please try again.");
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}