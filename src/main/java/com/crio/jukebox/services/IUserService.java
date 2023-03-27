package com.crio.jukebox.services;

import java.util.List;
import com.crio.jukebox.entities.PlayList;
import com.crio.jukebox.entities.User;

public interface IUserService {
    public User create(String name);
    public List<PlayList> getAllPlayList(String userId);
    
}