package com.crio.jukebox.commands;

import com.crio.jukebox.entities.User;
import com.crio.jukebox.services.IUserService;

import java.util.List;

public class CreateUserCommand implements ICommand{
    private final IUserService userService;

    public CreateUserCommand(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(List<String> tokens) {
        try{
            User user=userService.create(tokens.get(1));
            System.out.println(user.getId() +" "+ user.getName());
        }catch (Exception e){
            System.out.println("Something Wrong While Creating User");
        }
    }
}