package com.stackroute.musictrack.service;

import com.stackroute.musictrack.domain.Track;
import com.stackroute.musictrack.exceptions.TrackAlreadyExistsException;
import com.stackroute.musictrack.exceptions.TrackNotFoundException;
import com.stackroute.musictrack.repository.TrackRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TrackServiceTest {
    private Track track;

    @Mock
    private TrackRepository trackRepository;

    @InjectMocks
    private TrackServiceImpl trackService;
    List<Track> tracks=null;

    Optional optional;
    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        track=new Track();
        track.set_id(1);
        track.setName("song1");
        track.setComments("comment1");

        tracks=new ArrayList<>();
        tracks.add(track);

        optional=Optional.of(track);
    }
    @After
    public void tearDown(){
        trackRepository.deleteAll();
    }

    @Test
    public void givenMethodShouldSaveTrack() throws TrackAlreadyExistsException {
        when(trackRepository.save((Track) any())).thenReturn(track);
        Track savedTrack=trackService.saveTrack(track);
        Assert.assertEquals(track,savedTrack);

        verify(trackRepository,times(1)).save(track);
    }

    @Test(expected = TrackAlreadyExistsException.class)
    public void givenMethodShouldNotSaveTrack() throws TrackAlreadyExistsException {
        when(trackRepository.save((Track) any())).thenReturn(null);
        Track savedTrack = trackService.saveTrack(track);
        System.out.println("savedUser" + savedTrack);
        Assert.assertEquals(track, savedTrack);

        doThrow(new TrackAlreadyExistsException("track already exists")).when(trackRepository).findById(eq(1));
        trackService.saveTrack(track);
    }

    @Test
    public void givenMethodShouldReturnAllTracks(){

        trackRepository.save(track);
        when(trackRepository.findAll()).thenReturn(tracks);

        List<Track> list=trackService.getAllTracks();

        Assert.assertEquals(tracks,list);
    }

    @Test
    public void givenMethodShouldNotReturnAllTracks(){
        trackRepository.save(track);
        when(trackRepository.findAll()).thenReturn(null);
        List<Track> list=trackService.getAllTracks();
        Assert.assertNotEquals(tracks,list);
    }

    @Test
    public void givenMethodShouldReturnTrackById() throws TrackNotFoundException {
        trackRepository.save(track);
        Track track1=null;
        when(trackRepository.findById(1)).thenReturn(optional);
        Track getTrack=trackService.getTrackByTrackId(track.get_id());
        Assert.assertEquals(track1,getTrack);
    }
    @Test
    public void givenMethodShouldNotReturnTrackById() throws TrackNotFoundException {
        trackRepository.save(track);
        Track track1=null;
        when(trackRepository.findById(2)).thenReturn(optional);
        Track getTrack=trackService.getTrackByTrackId(track.get_id());
        Assert.assertEquals(track1,getTrack);
    }


    @Test
    public void givenMethodShouldReturnTrackByName() throws TrackNotFoundException {
        when(trackRepository.findByName("song1")).thenReturn(track);
        Track foundTrack=trackService.getTrackByName("song1");
        Assert.assertEquals(track,foundTrack);
    }

    @Test
    public void givenMethodShouldNotReturnTrackByName() throws TrackNotFoundException {
        when(trackRepository.findByName("song2")).thenReturn(track);
        Track foundTrack=trackService.getTrackByName("song1");
        Assert.assertNotEquals(track,foundTrack);
    }

    @Test
    public void givenMethodShouldDeleteTrack() throws TrackNotFoundException {

        when(trackRepository.findById(1)).thenReturn(optional);
        List<Track> actualList=trackService.deleteTrack(track.get_id());
        Assert.assertEquals(false,actualList.contains(track));
    }

    @Test
    public void givenMethodShouldNotDeleteTrack() throws TrackNotFoundException {
        when(trackRepository.findById(1)).thenReturn(optional);
        List<Track> actualList=trackService.deleteTrack(track.get_id());
        Assert.assertNotEquals(true,actualList.contains(track));
    }

    @Test
    public void givenMethodShouldUpdateComments() throws TrackNotFoundException {
       when(trackRepository.findByName("song1")).thenReturn(track);
        when(trackRepository.existsById(1)).thenReturn(true);
        when(trackRepository.save(track)).thenReturn(track);
        Track updateTrack=new Track(1,"song1","abcd");
        Track actualTrack=trackService.updateComments(updateTrack);
        Assert.assertEquals(updateTrack,actualTrack);
    }

    @Test
    public void givenMethodShouldNotUpdateComments() throws TrackNotFoundException {
        when(trackRepository.findByName("song1")).thenReturn(track);
        when(trackRepository.existsById(1)).thenReturn(true);
        when(trackRepository.save(track)).thenReturn(track);
        Track updateTrack=new Track(1,"song1","abc qq");
        //Track actualTrack=trackService.updateComments(updateTrack);
        Assert.assertNotEquals(updateTrack,tracks);
    }
}

