package com.project.notes.service;

import com.project.notes.enumeration.Right;
import com.project.notes.model.Account;
import com.project.notes.model.AccountNoteAssociation;
import com.project.notes.model.AccountNoteCompositeKeyId;
import com.project.notes.model.Note;
import com.project.notes.repository.AccountNoteRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountNoteService {

    final AccountNoteRepository accountNoteRepository;

    public AccountNoteService(AccountNoteRepository accountNoteRepository) {
        this.accountNoteRepository = accountNoteRepository;
    }

    public Optional<AccountNoteAssociation> findById(AccountNoteCompositeKeyId compositeId) {
        return this.accountNoteRepository.findById(compositeId);
    }

//    public void addAccountNoteAssociation(Account account, Note note, Boolean owner) {
//        AccountNoteAssociation accountNoteAssociation = new AccountNoteAssociation(account, note, owner);
//        accountNoteRepository.save(accountNoteAssociation);
//    }
    public void addAccountNoteAssociation(Account account, Note note, Right right) {
        AccountNoteAssociation accountNoteAssociation = new AccountNoteAssociation(account, note, right);
        accountNoteRepository.save(accountNoteAssociation);
    }

    public void deleteAccountNoteAssociation(AccountNoteAssociation accountNoteAssociation) {
        accountNoteRepository.delete(accountNoteAssociation);
    }
}
