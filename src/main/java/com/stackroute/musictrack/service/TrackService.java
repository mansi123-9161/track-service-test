package com.stackroute.musictrack.service;

import com.stackroute.musictrack.domain.Track;
import com.stackroute.musictrack.exceptions.TrackAlreadyExistsException;
import com.stackroute.musictrack.exceptions.TrackNotFoundException;

import java.util.List;

public interface TrackService {
    public Track saveTrack(Track track) throws TrackAlreadyExistsException;
    public List<Track> getAllTracks() ;
    public Track getTrackByTrackId(int id) throws TrackNotFoundException;
    public Track getTrackByName(String trackName) throws TrackNotFoundException;
    public Track updateComments(Track track) throws TrackNotFoundException;
    public List<Track> deleteTrack(int id) throws TrackNotFoundException;
}
