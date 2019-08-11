package com.stackroute.musictrack.service;

import com.stackroute.musictrack.domain.Track;
import com.stackroute.musictrack.exceptions.TrackAlreadyExistsException;
import com.stackroute.musictrack.exceptions.TrackNotFoundException;
import com.stackroute.musictrack.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class TrackServiceImpl implements TrackService {
    private TrackRepository trackRepository;

    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }
    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistsException {
        if(trackRepository.existsById(track.get_id())){
            throw new TrackAlreadyExistsException("track already exists");
        }

        Track savedTrack=trackRepository.save(track);
        if(savedTrack==null){
            throw new TrackAlreadyExistsException("track already exists");
        }
        return savedTrack;
    }

    @Override
    public List<Track> getAllTracks() {
        List<Track> list=(List<Track>)trackRepository.findAll();
        return list;
    }

    @Override
    public Track getTrackByTrackId(int id) throws TrackNotFoundException {
        Track foundTrack=null;
        if(trackRepository.existsById(id)){
            foundTrack=trackRepository.findById(id).get();
        }
        return foundTrack;
    }

    @Override
    public Track getTrackByName(String trackName) throws TrackNotFoundException {
        Track foundTrack=null;
        foundTrack=trackRepository.findByName(trackName);
        return foundTrack;
    }

    @Override
    public Track updateComments(Track track) throws TrackNotFoundException {
        Track track1=trackRepository.findByName(track.getName());
        if(trackRepository.existsById(track.get_id())){
            track1.setComments(track.getComments());
            track1=trackRepository.save(track1);
        }
        else {
            throw new TrackNotFoundException("track not found");
        }
        return track1;
    }

    @Override
    public List<Track> deleteTrack(int id) throws TrackNotFoundException {
        Optional optional=trackRepository.findById(id);
        if(optional.isPresent()){
            trackRepository.deleteById(id);
        }
        else {
            throw new TrackNotFoundException("track not found");
        }
        return trackRepository.findAll();
    }

}

