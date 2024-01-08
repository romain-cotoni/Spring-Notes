package com.project.notes.config;

import com.project.notes.enumeration.Right;
import com.project.notes.enumeration.Role;
import com.project.notes.model.Account;
import com.project.notes.model.AccountNoteAssociation;
import com.project.notes.model.Note;
import com.project.notes.repository.AccountNoteRepository;
import com.project.notes.repository.NoteRepository;
import com.project.notes.repository.AccountRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class InitiateDBOnStartUp {

    private final NoteRepository noteRepository;

    private final AccountRepository accountRepository;

    private final AccountNoteRepository accountNoteRepository;

    private final BCryptPasswordEncoder bcryptEncoder;

    public InitiateDBOnStartUp(final NoteRepository noteRepository,
                               final AccountRepository accountRepository,
                               final AccountNoteRepository accountNoteRepository,
                               final BCryptPasswordEncoder bcryptEncoder) {
        this.noteRepository = noteRepository;
        this.accountRepository = accountRepository;
        this.accountNoteRepository = accountNoteRepository;
        this.bcryptEncoder  = bcryptEncoder;
    }
    //@PostConstruct
    private void runAfterStartup() {
        //add datas to db
        System.out.println("Class InitiateDBOnStartUp - runAfterStartup() - creation of fixtures");

        Account account;
        Note note;
        for(int i=1; i<=1; i++) {
            account = accountRepository.save(new Account("rom"+i, bcryptEncoder.encode("ssap"), Role.USER));
            account.setFirstname("romain");
            account.setLastname("cotoni");
            accountRepository.save(account);
            for(int j=1; j<=1; j++) {
                if(i==1 && j==1)
                    note = noteRepository.save(new Note("hello"+j, "<font size=\"5\" color=\"#f50a0a\"><b>HELLO WORLD</b></font><div><b style=\"\"><font size=\"5\">&nbsp; &nbsp;&nbsp;</font><font size=\"4\">HELLO 1</font></b></div><div><b style=\"font-size: large;\">&nbsp; &nbsp; &nbsp;</b><font size=\"4\">bla bla bla 1</font></div><div><br></div><div>&nbsp; &nbsp; &nbsp; <font size=\"4\"><b>HELLO 2</b></font></div><div><div><b style=\"font-size: x-large;\">&nbsp; &nbsp; </b><font size=\"4\">bla bla bla 2</font></div><div><font size=\"5\"><br></font><b><font size=\"5\"><br></font></b></div><div><b></b></div></div>"));
                else
                    note = noteRepository.save(new Note("title"+j, "<font size=\"5\" color=\"#f50a0a\"><b>HELLO WORLD</b></font><div><b style=\"\"><font size=\"5\">&nbsp; &nbsp;&nbsp;</font><font size=\"4\">HELLO 1</font></b></div><div><b style=\"font-size: large;\">&nbsp; &nbsp; &nbsp;</b><font size=\"4\">bla bla bla 1</font></div><div><br></div><div>&nbsp; &nbsp; &nbsp; <font size=\"4\"><b>HELLO 2</b></font></div><div><div><b style=\"font-size: x-large;\">&nbsp; &nbsp; </b><font size=\"4\">bla bla bla 2</font></div><div><font size=\"5\"><br></font><b><font size=\"5\"><br></font></b></div><div><b></b></div></div>"));
                accountNoteRepository.save(new AccountNoteAssociation(account, note, true, Right.OWNER));
            }
        }
    }
}
