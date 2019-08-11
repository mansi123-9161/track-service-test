package com.stackroute.musictrack.repository;

import com.stackroute.musictrack.domain.Track;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackRepository extends MongoRepository<Track, Integer> {

    Track findByName(String name);
}
