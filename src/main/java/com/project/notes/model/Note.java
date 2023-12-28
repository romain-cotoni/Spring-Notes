package com.project.notes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.notes.enumeration.Right;
import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name="note")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_note")
    private Integer id;

    @Column(name="title")
    private String title;

    @Column(name="creation")
    private Date creation = new Date();

    @Column(name="content", columnDefinition="TEXT")
    private String content;

    @JsonIgnore
    @OneToMany(mappedBy = "note", cascade = CascadeType.ALL)
    private Set<AccountNoteAssociation> accounts = new HashSet<>();


    //CONSTRUCTORS

    public Note() {}

    public Note(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    public Note(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Note(Integer id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Note(String title, String content, Set<AccountNoteAssociation> accounts) {
        this.title = title;
        this.content = content;
        this.accounts = accounts;
    }



//    public void addAccount(Account account, Boolean owner) {
//        AccountNoteAssociation accountNoteAssociation = new AccountNoteAssociation(account, this, owner);
//        accounts.add(accountNoteAssociation);
//    }

//    public void addAccount(Account account, Boolean owner, Right right) {
//        AccountNoteAssociation accountNoteAssociation = new AccountNoteAssociation(account, this, owner, right);
//        accounts.add(accountNoteAssociation);
//    }

//    public void removeAccount(AccountNoteAssociation accountNoteAssociation) {
//        this.accounts.remove(accountNoteAssociation);
//    }


//    public void shareNote(Account account) {
//        AccountNoteAssociation accountNoteAssociation = new AccountNoteAssociation(account, this);
//        if(accountNoteAssociation.getOwner() && !accountNoteAssociation.getShared()) {
//            accountNoteAssociation.setShared(true);
//        }
//    }

    /**
     * UNSHARE NOTE METHOD
     * @param account
     */
    public void unshareNote(Account account) {
        AccountNoteAssociation accountNoteAssociation = new AccountNoteAssociation(account, this);
        if(accountNoteAssociation.getOwner() && accountNoteAssociation.getShared()) {
            accountNoteAssociation.setShared(false);
        }
    }

    //GETTERS & SETTERS
    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Set<AccountNoteAssociation> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<AccountNoteAssociation> accounts) {
        this.accounts = accounts;
    }

}
