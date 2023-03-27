package com.crio.jukebox.commands;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import com.crio.jukebox.exceptions.InvalidOperationException;
import com.crio.jukebox.services.IUserPlayListService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("DeletePlayListCommandTest")
@ExtendWith(MockitoExtension.class)
public class DeletePlayListCommandTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    
    @Mock
    IUserPlayListService userPlayListServiceMock;  

    @InjectMocks
    DeletePlayListCommand deletePlayListCommand;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }


    @Test
    @DisplayName("execute method of DeletePlayListCommand Should Print Error Message To Console If PlayListId Not found.")
    public void execute_ShouldPrintErrorMessage_PlayListNotFound() {
        //Arrange
        String playListId = "10";
        String userId="1";

        String expectedOutput = "PlayList is Not Found";
        doThrow(new InvalidOperationException(expectedOutput)).when(userPlayListServiceMock).deletePlayList(userId,playListId);

        //Act
        deletePlayListCommand.execute(List.of("DELETE-PLAYLIST",userId,playListId));

        //Assert
        Assertions.assertEquals(expectedOutput, outputStreamCaptor.toString().trim());

        verify(userPlayListServiceMock,times(1)).deletePlayList(userId,playListId);
    }



    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
 
}