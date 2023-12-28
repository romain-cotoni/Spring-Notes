package com.project.notes;

import com.project.notes.config.InitiateDBOnStartUp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NotesApplication {

    //final InitiateDBOnStartUp initiateDBOnStartUp;

//    public NotesApplication(final InitiateDBOnStartUp initiateDBOnStartUp) {
//        this.initiateDBOnStartUp = initiateDBOnStartUp;
//    }
    public static void main(String[] args) {
        SpringApplication.run(NotesApplication.class, args);
    }

}
