package com.stackroute.musictrack.seeddata;

//import com.stackroute.trackservice.domain.Track;
//import com.stackroute.trackservice.repository.TrackRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.context.ApplicationEvent;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.stereotype.Component;
//
//@Component
//@PropertySource("classpath:application.properties")
//public class ApplicationRunnerImpl implements ApplicationListener, ApplicationRunner {
//    private TrackRepository trackRepository;
//    @Value("${track1.id}")
//    private int id;
//    @Value("${track1.name}")
//    private String name;
//    @Value("${track1.comments}")
//    private String comments;
//    Track track1 = new Track();
//@Autowired
//    public ApplicationRunnerImpl(TrackRepository trackRepository) {
//        this.trackRepository = trackRepository;
//    }
////runs before server running to check connectivity
//    @Override
//    public void onApplicationEvent(ApplicationEvent applicationEvent) {
//        track1.setId(id);
//        track1.setName(name);
//        track1.setComments(comments);
//        trackRepository.save(track1);
//    }
//    // it starts just after application context created
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//
//    }
//}
