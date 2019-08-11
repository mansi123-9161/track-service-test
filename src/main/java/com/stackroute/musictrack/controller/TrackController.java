package com.stackroute.musictrack.controller;

import com.stackroute.musictrack.domain.Track;
import com.stackroute.musictrack.exceptions.TrackAlreadyExistsException;
import com.stackroute.musictrack.exceptions.TrackNotFoundException;
import com.stackroute.musictrack.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value ="api/v1/")
public class TrackController {
    private TrackService trackService;

    @Autowired
    public TrackController( TrackService trackService)
    {
        this.trackService = trackService;
    }

    //get track bt id method
    @GetMapping("track/{id}")
    public ResponseEntity<?> getTrackByTrackId(@PathVariable("id") int id) throws TrackNotFoundException{
        ResponseEntity responseEntity;
        Track track=trackService.getTrackByTrackId(id);
        if(track==null){
            throw new TrackNotFoundException("Track not found");
        }
        else {
            responseEntity=new ResponseEntity<String>("Track found",HttpStatus.OK);
        }
        return responseEntity;
    }
    @PostMapping("track")
    public ResponseEntity<?> saveTrack(@RequestBody Track track) throws TrackAlreadyExistsException {
        ResponseEntity responseEntity;
        trackService.saveTrack(track);
        responseEntity=new ResponseEntity<String>("Track successfully added", HttpStatus.CREATED);
        return responseEntity;
    }
    @GetMapping("tracks")
    public ResponseEntity<?> getAllTracks() throws TrackNotFoundException {
        ResponseEntity responseEntity;
        responseEntity=new ResponseEntity<List<Track>>(trackService.getAllTracks(),HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping("track/{id}")
    public ResponseEntity<?> deleteTrack(@PathVariable("id") int id) throws TrackNotFoundException{
        return new ResponseEntity<List<Track>>(trackService.deleteTrack(id),HttpStatus.OK);
    }
    @PutMapping("track/{id}")
    public ResponseEntity<?> updateComments(@RequestBody Track track) throws TrackNotFoundException{
        ResponseEntity responseEntity;
        trackService.updateComments(track);
        responseEntity = new ResponseEntity<String>("Track updated", HttpStatus.OK);
        return responseEntity;
    }
    //    //get track by name
    @GetMapping("tracks/{name}")
    public ResponseEntity<?> getTrackByName(@PathVariable("name") String name) throws TrackNotFoundException{
        ResponseEntity responseEntity;
        Track track=trackService.getTrackByName(name);
        if(track==null){
            throw new TrackNotFoundException("Track not found");
        }
        else {
            responseEntity=new ResponseEntity<String>("Track found",HttpStatus.OK);
        }
        return responseEntity;
    }
}
