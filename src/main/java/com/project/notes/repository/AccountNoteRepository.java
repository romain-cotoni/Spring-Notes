package com.project.notes.repository;

import com.project.notes.model.AccountNoteAssociation;
import com.project.notes.model.AccountNoteCompositeKeyId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountNoteRepository extends JpaRepository<AccountNoteAssociation,AccountNoteCompositeKeyId> {

}
