package com.project.notes.service;

import com.project.notes.dto.*;
import com.project.notes.enumeration.Right;
import com.project.notes.model.Account;
import com.project.notes.model.AccountNoteAssociation;
import com.project.notes.model.AccountNoteCompositeKeyId;
import com.project.notes.model.Note;
import com.project.notes.repository.NoteRepository;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class NoteService {

    final NoteRepository noteRepository;

    final AccountService accountService;

    final AccountNoteService accountNoteService;

    final EntityManager entityManager;

    public NoteService(final NoteRepository noteRepository,
                       final AccountService accountService,
                       final AccountNoteService accountNoteService,
                       final EntityManager entityManager) {
        this.noteRepository = noteRepository;
        this.accountService = accountService;
        this.accountNoteService = accountNoteService;
        this.entityManager = entityManager;
    }


    public Optional<Note> getNoteById(int id) {
        Optional<Note> note = this.noteRepository.findById(id);
        return note;
    }


    public NoteDto createNote(int userId, Note note) {
        Right right = Right.OWNER;
        if (note.getTitle().isEmpty() || note.getTitle().isBlank()) note.setTitle("New note");
        Note noteCreated = this.noteRepository.save(new Note(note.getTitle(), note.getContent()));
        Account account = this.accountService.findById(userId).orElseThrow();
        accountNoteService.addAccountNoteAssociation(account, noteCreated, right);
        NoteDto noteDto = new NoteDto();
        noteDto.setId(noteCreated.getId());
        noteDto.setTitle(noteCreated.getTitle());
        noteDto.setContent(noteCreated.getContent());
        noteDto.setCreation(noteCreated.getCreation());
        noteDto.setRight(right);
        return noteDto;
    }


    public Note updateNote(int noteId, Note noteRequest) {
        Note noteResponse = noteRepository.findById(noteId).orElseThrow();
        noteResponse.setTitle(noteRequest.getTitle());
        noteResponse.setContent(noteRequest.getContent());
        return noteRepository.save(noteResponse);
    }


    public void deleteNote(int noteId) {
        noteRepository.deleteById(noteId);
    }


    public SharedNoteUserDto shareNote(SharedNoteUserDto sharedNoteUserRequestDto) throws Exception {
        try {
            SharedNoteUserDto sharedNoteUserResponseDto = new SharedNoteUserDto();
            List<SharedUserDto> sharedUsers = new ArrayList<>();
            Optional<Note> optionalNote = this.getNoteById(sharedNoteUserRequestDto.getId());
            if (optionalNote.isPresent()) {
                Note note = optionalNote.get();
                //SHARE THE NOTE WITH THE USERS IN THE REQUEST
                sharedNoteUserRequestDto.getSharedUsers().forEach(sharedUser -> {
                    Account account = accountService.findAccountByUsername(sharedUser.getUsername());
                    accountNoteService.addAccountNoteAssociation(account, note, sharedNoteUserRequestDto.getRight());
                });
                //GET ALL USERS SHARING THE NOTE
                sharedUsers = this.getAllUsersSharingNote(note);
                sharedNoteUserResponseDto.setRight(sharedNoteUserRequestDto.getRight());
                sharedNoteUserResponseDto.setSharedUsers(sharedUsers);
            }
            return sharedNoteUserResponseDto;
        } catch (Exception exception) {
            throw new Exception ("Error NoteService -> shareNote() : " + exception.getMessage());
        }
    }


    public SharedNoteUserDto unshareNote(SharedNoteUserDto sharedNoteUserRequestDto) throws Exception {
        try {
            SharedNoteUserDto sharedNoteUserResponseDto = new SharedNoteUserDto();
            List<SharedUserDto> sharedUsers;
            Optional<Note> optionalNote = this.getNoteById(sharedNoteUserRequestDto.getId());
            if (optionalNote.isPresent()) {
                Note note = optionalNote.get();
                sharedNoteUserRequestDto.getSharedUsers().forEach(user -> {
                    Account account = accountService.findAccountByUsername(user.getUsername());   //.findById(user.getId());
                    Optional<AccountNoteAssociation> optionalAccountNoteAssociation
                            = accountNoteService.findById(new AccountNoteCompositeKeyId(account.getId(), note.getId()));
                    if (optionalAccountNoteAssociation.isPresent()) {
                        AccountNoteAssociation accountNoteAssociation = optionalAccountNoteAssociation.get();
                        accountNoteService.deleteAccountNoteAssociation(accountNoteAssociation);
                    }
                });
                sharedUsers = this.getAllUsersSharingNote(note);
                sharedNoteUserResponseDto.setSharedUsers(sharedUsers);
            }
            return sharedNoteUserResponseDto;
        } catch (Exception exception) {
            throw new Exception ("Error NoteService -> unshareNote() : " + exception.getMessage());
        }
    }


    /**
     * GET ALL USERS SHARING THE NOTE
     */
    public List<SharedUserDto> getAllUsersSharingNote(Note note) {
        List<SharedUserDto> sharedUsers = new ArrayList<>();
        Set<AccountNoteAssociation> accountNoteAssociations = note.getAccounts();
        accountNoteAssociations.forEach(accountNote -> {
            SharedUserDto sharedUser = new SharedUserDto();
            sharedUser.setId(accountNote.getAccount().getId());
            sharedUser.setUsername(accountNote.getAccount().getUsername());
            sharedUser.setRight(accountNote.getRight());
            sharedUsers.add(sharedUser);
        });
        return sharedUsers;
    }


    public SharedNoteUserDto getSharedNoteAccounts(int noteId) {
        SharedNoteUserDto sharedNoteUserResponseDto = new SharedNoteUserDto();
        Optional<Note> optionalNote = noteRepository.findById(noteId);
        if(optionalNote.isPresent()) {
            List<SharedUserDto> sharedUsers = this.getAllUsersSharingNote(optionalNote.get());
            sharedNoteUserResponseDto.setSharedUsers(sharedUsers);
        }
        return sharedNoteUserResponseDto;
    }

}




//    public Boolean addUserToNote(int accountId, int noteId) {
//        Optional<Note> note = this.getNoteById(noteId);
//        Optional<Account> account = accountService.findById(accountId);
//        if(note.isPresent() & account.isPresent()) {
//            note.get().addAccount(account.get(), false);
//            try {
//                noteRepository.save(note.get());
//                return true;
//            } catch(Exception exception) {
//                return false;
//            }
//        }
//        return false;
//    }
//
//    public Boolean addUsersToNote(int noteId, List<String> userList) throws Exception {
//        List<String> userListOk = new ArrayList<>();
//        Optional<Note> optionalNote = this.getNoteById(noteId);
//        if(optionalNote.isPresent()) {
//            Note note = optionalNote.get();
//            userList.forEach(username -> {
//                try {
//                    Account account = accountService.findByUsername(username); //TODO: change return type to optional 'findByUsername(username)'
//                    //account.addNote(note.get(),false);
//                    //accountService.saveAccount(account);
//                    note.addAccount(account, false);
//                    noteRepository.save(note);
//                    userListOk.add(username);
//                } catch(Exception exception) {
//                    //throw new Exception ("Error NoteService -> addUsersToNote() : " + exception.getMessage()); //TODO: resolve this line of code pb
//                }
//            });
//        }
//        if(userListOk.size() > 0) return true; //TODO: improve the return logic in front and back
//        else return false;
//    }
