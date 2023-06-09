package com.crio.jukebox.repositories;

import java.util.List;
import com.crio.jukebox.entities.PlayList;
import com.crio.jukebox.entities.User;

public interface IUserRepository extends CRUDRepository<User,String> {
    public List<PlayList> findAllPlayList(String name);       
   
}
