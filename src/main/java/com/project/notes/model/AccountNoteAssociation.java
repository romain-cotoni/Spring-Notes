package com.project.notes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.notes.enumeration.Right;
import jakarta.persistence.*;

@Entity
@Table(name = "account_note")
public class AccountNoteAssociation {

    //ATTRIBUTS
    @EmbeddedId
    @JsonIgnore
    private AccountNoteCompositeKeyId accountNoteCompositeKeyId;

    @ManyToOne//(fetch = FetchType.LAZY)
    @MapsId("idAccount")
    @JoinColumn(name = "id_account")
    @JsonIgnore
    private Account account;

    @ManyToOne//(fetch = FetchType.LAZY)
    @MapsId("idNote")
    @JoinColumn(name = "id_note")
    //@JsonIgnore
    private Note note;

    private Boolean owner = false;

    private Boolean shared = false;

    @Enumerated(EnumType.STRING)
    @Column(name="rights")
    private Right right;


    //CONSTRUCTORS
    public AccountNoteAssociation() {}

    public AccountNoteAssociation(Account account, Note note) {
        //super();
        this.account = account;
        this.note = note;
        this.accountNoteCompositeKeyId = new AccountNoteCompositeKeyId(account.getId(), note.getId());
    }

    public AccountNoteAssociation(Account account, Note note, Right right) {
        this.account = account;
        this.note = note;
        this.right = right;
        this.accountNoteCompositeKeyId = new AccountNoteCompositeKeyId(account.getId(), note.getId());
    }

//    public AccountNoteAssociation(Account account, Note note, Boolean owner) {
//        //super();
//        this.account = account;
//        this.note = note;
//        this.owner = owner;
//        this.accountNoteCompositeKeyId = new AccountNoteCompositeKeyId(account.getId(), note.getId());
//    }


//    public AccountNoteAssociation(Account account, Note note, Boolean owner, Right right) {
//        this.account = account;
//        this.note = note;
//        this.owner = owner;
//        this.right = right;
//        this.accountNoteCompositeKeyId = new AccountNoteCompositeKeyId(account.getId(), note.getId());
//    }

    //GETTERS & SETTERS
    public AccountNoteCompositeKeyId getAccountNoteCompositeKeyId() {
        return accountNoteCompositeKeyId;
    }

    public void setAccountNoteCompositeKeyId(AccountNoteCompositeKeyId accountNoteCompositeKeyId) {
        this.accountNoteCompositeKeyId = accountNoteCompositeKeyId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    public Boolean getOwner() {
        return owner;
    }

    public void setOwner(Boolean owner) {
        this.owner = owner;
    }

    public Boolean getShared() {
        return shared;
    }

    public void setShared(Boolean shared) {
        this.shared = shared;
    }

    public Right getRight() {
        return right;
    }

    public void setRight(Right right) {
        this.right = right;
    }
}
