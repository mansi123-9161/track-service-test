package com.stackroute.musictrack.seeddata;

//import com.stackroute.trackservice.domain.Track;
//import com.stackroute.trackservice.repository.TrackRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.stereotype.Component;
//
//@Component
//@PropertySource("classpath:application.properties")
//public class CommandLineListnerImpl implements CommandLineRunner {
//    private TrackRepository trackRepository;
//    //    Value annotation is used for injecting values into fields in Spring-managed beans
//    @Value("${track2.id}")
//    private int id;
//    @Value("${track2.name}")
//    private String name;
//    @Value("${track2.comments}")
//    private String comments;
//    Track track = new Track();
//
//@Autowired
//// it just created after tomcat server starts
//
//    public CommandLineListnerImpl(TrackRepository trackRepository) {
//        this.trackRepository = trackRepository;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        track.setId(id);
//        track.setName(name);
//        track.setComments(comments);
//        trackRepository.save(track);
//    }
//}
