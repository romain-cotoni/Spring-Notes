package com.project.notes.service;

import com.project.notes.dto.AuthResponseDto;
import com.project.notes.dto.NoteDto;
import com.project.notes.dto.UserDto;
import com.project.notes.model.Account;
import com.project.notes.model.exception.UsernameAlreadyExistsException;
import com.project.notes.repository.AccountRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class AccountService {
    private AccountRepository accountRepository;
    private BCryptPasswordEncoder bcryptEncoder;

    public AccountService(AccountRepository accountRepository, BCryptPasswordEncoder bcryptEncoder) {
        this.accountRepository = accountRepository;
        this.bcryptEncoder = bcryptEncoder;
    }


    public Account getAccountByUsername(String username) {
        return accountRepository.findAccountByUsername(username);
    }


    public Account createAccount(Account account) throws UsernameAlreadyExistsException {
        if(this.isUsernameUnique(account.getUsername())) {
            account.setPassword(bcryptEncoder.encode(account.getPassword()));
            return accountRepository.save(account);
        } else {
            throw new UsernameAlreadyExistsException("The username '" + account.getUsername() + "' is already used.");
        }
    }


    public void updateAccount(Account account) {}


    public void deleteAccount(int id) {}


    public AuthResponseDto fillAuthResponseDto(String jwtToken, String username) {
        UserDto user = new UserDto();
        List<NoteDto> notes = new ArrayList<>();
        Account account = getAccountByUsername(username);
        if(account != null) {
            account.getNotes().forEach( note -> {
                NoteDto noteDto = new NoteDto();
                noteDto.setId(note.getNote().getId());
                noteDto.setTitle(note.getNote().getTitle());
                noteDto.setContent(note.getNote().getContent());
                noteDto.setCreation(note.getNote().getCreation());
                noteDto.setOwner(note.getOwner());
                noteDto.setShared(note.getShared());
                noteDto.setRight(note.getRight());
                notes.add(noteDto);
            });
            user.setId(account.getId());
            user.setUsername(account.getUsername());
            user.setRole(account.getRole());
            user.setFirstname(account.getFirstname());
            user.setLastname(account.getLastname());
            user.setNotes(notes);
        }

        return new AuthResponseDto(jwtToken, user);
    }


    public Optional<Account> findById(int id) {
        return this.accountRepository.findById(id);
    }


    public void saveAccount(Account account) {
        this.accountRepository.save(account);
    }


    public List<UserDto> searchUsers(String str) {
        List<Account> accounts = accountRepository.findUsersByString(str);
        List<UserDto> userDtoList = new ArrayList<>();
        accounts.forEach(account -> {
            UserDto userDto = new UserDto();
            userDto.setId(account.getId());
            userDto.setUsername(account.getUsername());
            userDtoList.add(userDto);
        });
        return userDtoList;
    }


    public boolean isUsernameUnique(String username) {
        return accountRepository.findAccountByUsername(username) == null;
    }


    public Account findAccountByUsername(String username) {
        return this.accountRepository.findAccountByUsername(username);
    }

    public List<UserDto> getSharedAccountByNote(int noteId) {

        List<UserDto> users = new ArrayList<>();
        List<NoteDto> notes = new ArrayList<>();

        NoteDto noteDto = new NoteDto();
        List<Account> accounts = this.accountRepository.findAccountSharedByNotes(noteId);
        accounts.forEach(account -> {
            UserDto userDto = new UserDto();
            userDto.setId(account.getId());
            userDto.setUsername(account.getUsername());
            users.add(userDto);
        });
        return users;
    }
}



//    public AuthResponseDto getAccountByUsername(String username) {
//        Account account = accountRepository.findAccountByUsername(username);
//        AuthResponseDto authResponseDto = new AuthResponseDto();
//        authResponseDto.setUsername(account.getUsername());
//        authResponseDto.setFirstname(account.getFirstname());
//        authResponseDto.setEmail(account.getEmail());
//        authResponseDto.setRole(account.getRole());
//        authResponseDto.setNotes(account.getNotes());
//        return authResponseDto;
//    }



//    public AuthResponseDto setAuthResponseDto(String username) {
//        Account account = this.getAccountByUsername(username);
//        AuthResponseDto authResponseDto = new AuthResponseDto();
//        authResponseDto.setAccountId(account.getId());
//        authResponseDto.setUsername(account.getUsername());
//        authResponseDto.setRole(account.getRole());
//        authResponseDto.setJwtToken(token);
//        if(StringUtils.hasLength(account.getEmail())) authResponseDto.setEmail(account.getEmail());
//        if(StringUtils.hasLength(account.getFirstname())) authResponseDto.setFirstname(account.getFirstname());
//        if(StringUtils.hasLength(account.getLastname())) authResponseDto.setLastname(account.getLastname());
//
//    }

    //    public void addRoleToUser(int id) {}

    //    public void removeRoleFromUser(int id) {}

    //    public void updateUser(Account account) {
    //        LinkedHashSet<Note> notes = new LinkedHashSet<>();
    //        notes.add(new Note("title1","content1"));
    //        notes.add(new Note("title2","content2"));
    //        //userData.setNotes(notes);
    //    }

    //    public Optional<Account> getAccountById(int id) {
    //        Optional<Account> account = accountRepository.findById(id);
    //        return account;
    //    }
