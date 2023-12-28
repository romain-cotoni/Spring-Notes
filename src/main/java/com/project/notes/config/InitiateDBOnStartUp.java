package com.project.notes.config;

import com.project.notes.enumeration.Right;
import com.project.notes.enumeration.Role;
import com.project.notes.model.Account;
import com.project.notes.model.AccountNoteAssociation;
import com.project.notes.model.AccountNoteCompositeKeyId;
import com.project.notes.model.Note;
import com.project.notes.repository.AccountNoteRepository;
import com.project.notes.repository.NoteRepository;
import com.project.notes.repository.AccountRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

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
    @PostConstruct
    private void runAfterStartup() {
        System.out.println("Class InitiateDBOnStartUp - runAfterStartup() - creation of fixtures");

        Account account;
        Note note;
        for(int i=1; i<=10; i++) {
            account = accountRepository.save(new Account("rom"+i, bcryptEncoder.encode("ssap"), Role.USER));
            account.setFirstname("romain");
            account.setLastname("cotoni");
            accountRepository.save(account);
            for(int j=1; j<=10; j++) {
                note = noteRepository.save(new Note("title"+j, "<font size=\"5\" color=\"#f50a0a\"><b>HELLO WORLD</b></font><div><b style=\"\"><font size=\"5\">&nbsp; &nbsp;&nbsp;</font><font size=\"4\">HELLO 1</font></b></div><div><b style=\"font-size: large;\">&nbsp; &nbsp; &nbsp;</b><font size=\"4\">bla bla bla 1</font></div><div><br></div><div>&nbsp; &nbsp; &nbsp; <font size=\"4\"><b>HELLO 2</b></font></div><div><div><b style=\"font-size: x-large;\">&nbsp; &nbsp; </b><font size=\"4\">bla bla bla 2</font></div><div><font size=\"5\"><br></font><b><font size=\"5\"><br></font></b></div><div><b></b></div></div>"));
                //account.addNote(note,true);
                //accountRepository.save(account);
                accountNoteRepository.save(new AccountNoteAssociation(account, note, true, Right.OWNER));
            }
        }







//        Account account1 = accountRepository.save(new Account("rom1", bcryptEncoder.encode("ssap"), Role.USER));
//        account1.setFirstname("romain");
//        accountRepository.save(account1);
//
//        Note note1 = noteRepository.save(new Note("title1", "bla bla bla 1 <b>Bold Bold Bold Bold</b>"));
//        Note note10= noteRepository.save(new Note("title2", "<font size=\"5\" color=\"#f50a0a\"><b>HELLO WORLD</b></font><div><b style=\"\"><font size=\"5\">&nbsp; &nbsp;&nbsp;</font><font size=\"4\">HELLO 1</font></b></div><div><b style=\"font-size: large;\">&nbsp; &nbsp; &nbsp;</b><font size=\"4\">bla bla bla 1</font></div><div><br></div><div>&nbsp; &nbsp; &nbsp; <font size=\"4\"><b>HELLO 2</b></font></div><div><div><b style=\"font-size: x-large;\">&nbsp; &nbsp; </b><font size=\"4\">bla bla bla 2</font></div><div><font size=\"5\"><br></font><b><font size=\"5\"><br></font></b></div><div><b></b></div></div>"));
//        Note note3 = noteRepository.save(new Note("title3", "bla bla bla 3 ..."));
//        Note note4 = noteRepository.save(new Note("title4", "bla bla bla 4 ..."));
//        Note note5 = noteRepository.save(new Note("title5", "bla bla bla 5 ..."));
//        Note note6 = noteRepository.save(new Note("title6", "bla bla bla 6 ..."));
//        Note note7 = noteRepository.save(new Note("title7", "bla bla bla 7 ..."));
//        Note note8 = noteRepository.save(new Note("title8", "bla bla bla 8 ..."));
//        Note note9 = noteRepository.save(new Note("title9", "bla bla bla 9 ..."));
//        Note note2 = noteRepository.save(new Note("title10", "bla bla bla 10 ..."));
//
//        account1.addNote(note1,true);
//        account1.addNote(note2,true);
//        account1.addNote(note3,true);
//        account1.addNote(note4,false);
//        account1.addNote(note5,true);
//        account1.addNote(note6,true);
//        account1.addNote(note7,true);
//        account1.addNote(note8,true);
//        account1.addNote(note9,true);
//        account1.addNote(note10,true);
//        accountRepository.save(account1);

    }
}
