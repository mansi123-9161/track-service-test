package com.stackroute.musictrack.service;

import com.stackroute.musictrack.domain.Track;
import com.stackroute.musictrack.exceptions.TrackAlreadyExistsException;
import com.stackroute.musictrack.exceptions.TrackNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TrackServiceDemoImpl implements TrackService {
    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistsException {
        return null;
    }

    @Override
    public List<Track> getAllTracks() {
        return null;
    }

    @Override
    public Track getTrackByTrackId(int id) throws TrackNotFoundException {
        return null;
    }

    @Override
    public Track getTrackByName(String trackName) throws TrackNotFoundException {
        return null;
    }

    @Override
    public Track updateComments(Track track) throws TrackNotFoundException {
        return null;
    }

    @Override
    public List<Track> deleteTrack(int id) throws TrackNotFoundException {
        return null;
    }
}
