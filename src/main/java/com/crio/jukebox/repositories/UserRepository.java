package com.crio.jukebox.repositories;

import com.crio.jukebox.entities.PlayList;
import com.crio.jukebox.entities.User;

import java.util.*;

public class UserRepository implements IUserRepository{

    private final IUserPlayListRepository iUserPlayListRepository;
    private final Map<String,User> listOfUsers=new HashMap<String,User>();
    private Integer autoIncrement=0;

    public UserRepository(IUserPlayListRepository iUserPlayListRepository) {
        this.iUserPlayListRepository = iUserPlayListRepository;
    }

    @Override
    public User save(User entity) {
        if(entity.getId()==null){
            autoIncrement++;
            User user=new User(Integer.toString(autoIncrement),entity.getName());
            listOfUsers.put(user.getId(),user);
            return user;
        }
        listOfUsers.put(entity.getId(),entity);
        return entity;
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(listOfUsers.values());
    }

    @Override
    public Optional<User> findById(String s) {
        return listOfUsers.values().stream().filter(u->u.getId().equals(s)).findFirst();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public List<PlayList> findAllPlayList(String userId) {
        return iUserPlayListRepository.findAllPlayListByUserId(userId);
    }
}