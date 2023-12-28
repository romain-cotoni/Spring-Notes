package com.project.notes.model;

import com.project.notes.enumeration.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="account")
public class Account {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_account")
    private Integer id;
    @NotNull(message = "Username cannot be null")
    @Column(name="username", unique = true)
    private String username;
    @Column(name="password")
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name="role")
    private Role role;
    @Column(name="firstname")
    private String firstname;
    @Column(name="lastname")
    private String lastname;
    @Column(name="email")
    private String email;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private Set<AccountNoteAssociation> notes = new HashSet<>();


//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name="id_account") //To prevent third table for association and use column for association instead
//    @OrderBy("id ASC")
//    private Set<Note> notes = new HashSet<>();


//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "account_note",
//               joinColumns = @JoinColumn(name = "id_account", referencedColumnName = "id_account"),
//               inverseJoinColumns = @JoinColumn(name = "id_note", referencedColumnName = "id_note"))
//    @OrderBy("id ASC")
//    private Set<Note> notes = new HashSet<>();


    //CONSTRUCTORS

    public Account() {}

    public Account(Integer id, String username) {
        this.id = id;
        this.username = username;
    }

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Account(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Account(Integer id, String username, Role role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

    public Account(Integer id, String username, Set<AccountNoteAssociation> notes) {
        this.id = id;
        this.username = username;
        this.notes = notes;
    }



//    public void addNote(Note note, Boolean owner) {
//        AccountNoteAssociation accountNoteAssociation = new AccountNoteAssociation(this, note, owner);
//        notes.add(accountNoteAssociation);
//    }


//    public void removeNote(AccountNoteAssociation accountNoteAssociation) {
//        this.notes.remove(accountNoteAssociation);
//    }


    /**
     * SHARE NOTE METHOD
     * @param note
     */
    public void shareNote(Note note) {
        AccountNoteAssociation accountNoteAssociation = new AccountNoteAssociation(this, note);
        if(accountNoteAssociation.getOwner() == true && accountNoteAssociation.getShared() == false) {
            accountNoteAssociation.setShared(true);
        }
    }

    /**
     * UNSHARE NOTE METHOD
     * @param note
     */
    public void unshareNote(Note note) {
        AccountNoteAssociation accountNoteAssociation = new AccountNoteAssociation(this, note);
        if(accountNoteAssociation.getOwner() == true && accountNoteAssociation.getShared() == true) {
            accountNoteAssociation.setShared(false);
        }
    }

    //GETTERS & SETTERS
    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
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

    public Set<AccountNoteAssociation> getNotes() {
        return notes;
    }

    public void setNotes(Set<AccountNoteAssociation> notes) {
        this.notes = notes;
    }


}
