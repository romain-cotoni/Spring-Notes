package com.project.notes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AccountNoteCompositeKeyId implements Serializable {

    private static final long serialVersionUID = 5646308986934304417L;

    @Column(name = "id_account")
    private Integer idAccount;

    @Column(name = "id_note")
    private Integer idNote;

    public AccountNoteCompositeKeyId() {
        super();
    }

    public AccountNoteCompositeKeyId(Integer idAccount, Integer idNote) {
        super();
        this.idAccount = idAccount;
        this.idNote = idNote;
    }

    public Integer getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(Integer idAccount) {
        this.idAccount = idAccount;
    }

    public Integer getIdNote() {
        return idNote;
    }

    public void setIdNote(Integer idNote) {
        this.idNote = idNote;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof AccountNoteCompositeKeyId that)) return false;
        AccountNoteCompositeKeyId other = (AccountNoteCompositeKeyId) obj;
        return getIdAccount().equals(other.getIdAccount()) && getIdNote().equals(other.getIdNote());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdAccount(), getIdNote());
    }
}
