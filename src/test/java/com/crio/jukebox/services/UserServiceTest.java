package com.crio.jukebox.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import com.crio.jukebox.entities.User;
import com.crio.jukebox.repositories.ISongRepository;
import com.crio.jukebox.repositories.UserRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("UserServiceTestForJukebox")
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepositoryMock;

    @Mock
    private ISongRepository songRepositoryMock;

    @InjectMocks
    private UserService userService;

    @Test
    @DisplayName("Test create User method")
    public void create_ShouldReturnUser(){
        //Arrange
        User expectedUser = new User("Kiran");
        //when(userRepositoryMock.save(any(User.class))).thenReturn(expectedUser);
        when(userRepositoryMock.save(any(User.class))).thenReturn(expectedUser);

        //Act
        User actualUser = userService.create("Kiran");

        //Assert
        Assertions.assertEquals(expectedUser,actualUser);
        verify(userRepositoryMock,times(1)).save(any(User.class));
    }
    
    

}
