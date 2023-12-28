package com.project.notes.dto;

import com.project.notes.enumeration.Role;
import java.util.*;

public class UserDto {

    private Integer id;

    private String username;

    private Role role;

    private String firstname;

    private String lastname;

    private String email;

    private List<NoteDto> notes = new ArrayList<>();



    //CONSTRUCTORS

    public UserDto() {}



    //GETTERS & SETTERS

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<NoteDto> getNotes() {
        return notes;
    }

    public void setNotes(List<NoteDto> notes) {
        Collections.sort(notes);
        this.notes = notes;
    }

}
