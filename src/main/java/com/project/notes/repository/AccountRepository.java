package com.project.notes.repository;

import com.project.notes.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {

    Account findAccountByUsername(String username);

    @Query("SELECT new Account(a.id, a.username) FROM Account AS a WHERE a.username LIKE %?1%")
    List<Account> findUsersByString(String str);

    @Query("Select new Account(a.id, a.username) FROM Account AS a " +
            "INNER JOIN AccountNoteAssociation AS an ON a.id=an.account.id " +
            "INNER JOIN Note AS n ON n.id=an.note.id " +
            "WHERE n.id=?1")
    List<Account> findAccountSharedByNotes(int noteId);

//    @Query("SELECT new Account(a.id, a.username, a.role, a.email, a.firstname, a.lastname, a.notes) FROM Account AS a INNER JOIN Note AS n WHERE a.username=?1")
//    @Query(value = "SELECT a.id_account, a.username, a.role, a.email, a.firstname, a.lastname, n.id_note, n.title, n.content FROM Account AS a INNER JOIN Note AS n ON n.id_account = a.id_account WHERE a.username=?1", nativeQuery = true)
//    @Query(value = "SELECT a.id_account, a.username, a.role, a.email, a.firstname, a.lastname, n.id_note, n.title, n.content " +
//                   "FROM Account AS a " +
//                   "INNER JOIN AccountNoteAssociation AS an ON an.id_account = a.id_account" +
//                   "INNER JOIN Note AS n ON n.id_account = a.id_account WHERE a.username=?1", nativeQuery = true)
//    Account findAccountSelectionByUsername(String username);
}
