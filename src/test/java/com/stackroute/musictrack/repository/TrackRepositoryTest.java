package com.stackroute.musictrack.repository;


import com.stackroute.musictrack.domain.Track;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataMongoTest
public class TrackRepositoryTest {

    @Autowired
    private TrackRepository trackRepository;

    private Track track;

    @Before
    public void setup(){
        track=new Track();
        track.set_id(1);
        track.setName("song1");
        track.setComments("comment1");
    }

    @After
    public void tearDown(){
        trackRepository.deleteAll();
    }

    @Test
    public void givenMethodShouldSaveTrack(){
        trackRepository.save(track);
        Track foundTrack=trackRepository.findById(track.get_id()).get();
        Assert.assertEquals(1,foundTrack.get_id());
    }

    @Test
    public void givenMethodShouldNotSaveTrack(){
        Track testTrack = new Track(2,"song2","abcd");
        trackRepository.save(track);
        Track fetchTrack = trackRepository.findById(track.get_id()).get();
        Assert.assertNotSame(testTrack,track);
    }

    @Test
    public void givenMethodShouldReturnAllTracks(){
        Track track1=new Track(3,"wakhara","mental");
        Track track2=new Track(4,"saiyan","saho");

        trackRepository.save(track1);
        trackRepository.save(track2);

        List<Track> list=trackRepository.findAll();
        Assert.assertEquals(3,list.get(0).get_id());
        System.out.println(list);
    }

    @Test
    public void givenMethodShouldNotReturnAllTracks(){
        Track track1=new Track(3,"wakhara","mental");
        Track track2=new Track(4,"saiyan","saho");

        trackRepository.save(track1);
        trackRepository.save(track2);

        List<Track> list=trackRepository.findAll();
        Assert.assertNotEquals(4,list.get(0).get_id());
    }

    @Test
    public void givenMethodShouldReturnTrackById(){
        trackRepository.save(track);

        Track foundTrack=(Track) trackRepository.findById(1).get();
        Assert.assertEquals(track,foundTrack);
    }

    @Test
    public void givenMethodShouldNotReturnTrackById(){
        trackRepository.save(track);

        Track foundTrack=trackRepository.findById(1).get();
        Assert.assertNotEquals(2,foundTrack.get_id());
    }

    @Test
    public void givenMethodShouldReturnTrackByName(){
        trackRepository.save(track);
        Track foundTrack=trackRepository.findByName("song1");

        Assert.assertEquals(track,foundTrack);
    }

    @Test
    public void givenMethodShouldNotReturnTrackByName(){
        trackRepository.save(track);
        Track foundTrack=trackRepository.findByName("song1");
        Assert.assertNotEquals(2,foundTrack.get_id());
    }

    @Test
    public void givenMethodShouldDeleteTrack(){
        trackRepository.delete(track);
        List<Track> list=trackRepository.findAll();

        Assert.assertEquals(false,list.contains(track));
    }

    @Test
    public void givenMethodShouldNotDeleteTrack(){
        trackRepository.delete(track);
        List<Track> list=trackRepository.findAll();

        Assert.assertNotEquals(true,list.contains(track));
    }
}