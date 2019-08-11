package com.stackroute.musictrack.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.musictrack.domain.Track;
import com.stackroute.musictrack.exceptions.GlobalException;
import com.stackroute.musictrack.exceptions.TrackAlreadyExistsException;
import com.stackroute.musictrack.exceptions.TrackNotFoundException;
import com.stackroute.musictrack.service.TrackService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest
public class TrackControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private Track track;
    @MockBean
    private TrackService trackService;
    @InjectMocks
    private TrackController trackController;

    private List<Track> list =null;

    @Before
    public void setUp(){

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(trackController).setControllerAdvice(new GlobalException()).build();
        track = new Track();
        track.set_id(1);
        track.setName("song1");
        track.setComments("comment1");

        list = new ArrayList();
        list.add(track);
    }
    @After
    public void tearDown(){
        list=null;
    }

    @Test
    public void givenMethodShouldSaveTrack() throws Exception {
        when(trackService.saveTrack(any())).thenReturn(track);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/track")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());

    }
    @Test
    public void givenMethodShouldNotSaveTrack() throws Exception {
        when(trackService.saveTrack(track)).thenThrow(TrackAlreadyExistsException.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/track")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void givenMethodShouldReturnAllTracks() throws Exception {
        when(trackService.getAllTracks()).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/tracks")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void givenMethodShouldNotReturnAllTracks() throws Exception {
        when(trackService.getAllTracks()).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/tracks")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void givenMethodShouldReturnTrackById() throws Exception{
        when(trackService.getTrackByTrackId(1)).thenReturn(track);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/track/1")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void givenMethodShouldNotReturnTrackById() throws Exception{
        when(trackService.getTrackByTrackId(2)).thenThrow(TrackNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/track/2")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void givenMethodShouldReturnTrackByName() throws Exception{
        when(trackService.getTrackByName("song1")).thenReturn(track);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/tracks/song1")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void givenMethodShouldNotReturnTrackByName() throws Exception{
        when(trackService.getTrackByName("song2")).thenThrow(TrackNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/tracks/song1")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void givenMethodShouldDeleteTrack() throws Exception{
        when(trackService.deleteTrack(1)).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/track/1")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void givenMethodShouldNotDeleteTrack() throws Exception{
        when(trackService.deleteTrack(3)).thenThrow(TrackNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/track/3")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void givenMethodShouldUpdateComments() throws Exception{
        when(trackService.updateComments(track)).thenReturn(track);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/track/1")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void givenMethodShouldNotUpdateComments() throws Exception{
        Track track1=new Track(8,"sorry","abcd");
        when(trackService.updateComments(track1)).thenThrow(TrackNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/track/1")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track1)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());
    }
    private static String asJsonString(final Object obj)
    {
        try{
            return new ObjectMapper().writeValueAsString(obj);

        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
