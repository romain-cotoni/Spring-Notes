package com.project.notes.controller;

import com.project.notes.dto.NoteDto;
import com.project.notes.dto.SharedNoteUserDto;
import com.project.notes.model.Note;
import com.project.notes.service.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@CrossOrigin(origins = {"https://black-tree-097fbec03.4.azurestaticapps.net", "https://www.getpostman.com", "http://localhost:4200/"}, allowCredentials = "true", maxAge=3600)
@RestController
@RequestMapping("api/note")
public class NoteController {

    private final NoteService noteService;

    public NoteController(final NoteService noteService) {
        this.noteService = noteService;
    }


    @GetMapping("/{id}")
    private Optional<Note> getNoteById(@PathVariable int id) throws Exception {
        try {
            return noteService.getNoteById(id);
        } catch(Exception exception) {
            throw new Exception("Error NotesController -> getNoteById() : " + exception.getMessage());
        }
    }


    @PostMapping("/user/{id_user}/create_note")
    private ResponseEntity<NoteDto> createNote(@PathVariable("id_user") int userId, @RequestBody Note note) throws Exception {
        try {
            NoteDto noteDto = noteService.createNote(userId, note);
            return ResponseEntity.ok().body(noteDto);
        } catch(Exception exception) {
            throw new Exception("Error NotesController -> createNote() : " + exception.getMessage());
        }
    }


    @PutMapping ("/update_note/{id_note}")
    private ResponseEntity<Note> updateNote(@PathVariable("id_note") int noteId, @RequestBody Note note) throws Exception {
        try {
            Note noteUpdated = noteService.updateNote(noteId, note);
            return ResponseEntity.ok().body(noteUpdated);
        } catch(Exception exception) {
            throw new Exception("Error NotesController -> updateNote() : " + exception.getMessage());
        }
    }


    @DeleteMapping ("/delete_note/{id}")
    private boolean deleteNote(@PathVariable("id") int id) throws Exception {
        try {
            noteService.deleteNote(id);
            return true;
        } catch(Exception exception) {
            throw new Exception("Error NotesController -> deleteNote() : " + exception.getMessage());
        }
    }


    @PostMapping("/share_note")
    private ResponseEntity<SharedNoteUserDto> shareNote(@RequestBody SharedNoteUserDto sharedNoteUserRequestDto) throws Exception {
        try {
            SharedNoteUserDto sharedNoteUserResponseDto = noteService.shareNote(sharedNoteUserRequestDto);
            return ResponseEntity.ok().body(sharedNoteUserResponseDto);
        } catch(Exception exception) {
            throw new Exception("Error NotesController -> shareNote() : " + exception.getMessage());
        }
    }


    @PostMapping("/unshare_note")
    private ResponseEntity<SharedNoteUserDto> unshareNote(@RequestBody SharedNoteUserDto sharedNoteUserRequestDto) throws Exception {
        try {
            SharedNoteUserDto sharedNoteUserResponseDto = noteService.unshareNote(sharedNoteUserRequestDto);
            return ResponseEntity.ok().body(sharedNoteUserResponseDto);
        } catch(Exception exception) {
            throw new Exception("Error NotesController -> unshare_note(note_id) : " + exception.getMessage());
        }
    }


    @GetMapping("getSharedNoteAccounts/{note_id}")
    private ResponseEntity<SharedNoteUserDto> getSharedNoteAccounts(@PathVariable("note_id") int note_id) throws Exception {
        try {
            SharedNoteUserDto sharedNoteUserResponseDto = noteService.getSharedNoteAccounts(note_id);
            return ResponseEntity.ok().body(sharedNoteUserResponseDto);
        } catch(Exception exception) {
            throw new Exception("Error NoteController -> getSharedNoteAccounts(note_id) : " + exception.getMessage());
        }
    }

}




//    @GetMapping("/add_user/{account_id}/to_note/{note_id}")
//    private ResponseEntity<Boolean> addUserToNote(@PathVariable("account_id") int account_id, @PathVariable("note_id") int note_id) {
//        Boolean result = noteService.addUserToNote(account_id, note_id);
//        return ResponseEntity.ok().body(result);
//    }

//    @PostMapping("/add_users_to_note/{note_id}")
//    private ResponseEntity<Boolean> addUsersToNote(@PathVariable("note_id") int note_id, @RequestBody List<String> userList) throws Exception {
//        try {
//            Boolean result = noteService.addUsersToNote(note_id, userList);
//            return ResponseEntity.ok().body(result);
//        } catch(Exception exception) {
//            throw new Exception("Error NotesController -> addUsersToNote() : " + exception.getMessage());
//        }
//    }
