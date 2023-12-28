package com.project.notes.repository;

import com.project.notes.model.Note;
import jakarta.persistence.OrderBy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note,Integer> {
//    @Query("SELECT new Note(n.id,n.title,n.content) FROM Note n INNER JOIN Account AS a ON a.id=n.owner.id WHERE n.owner.id=?1  ORDER BY n.id")
//    List<Note> findNotesByUserId(int id);

//   List<Note> findNotesByOwner_IdOrderById(int id);
}
