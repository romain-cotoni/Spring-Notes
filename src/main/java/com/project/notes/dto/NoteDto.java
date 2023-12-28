package com.project.notes.dto;

import com.project.notes.enumeration.Right;
import java.util.Date;

public class NoteDto implements Comparable<NoteDto> {
    private Integer id;

    private String title;

    private String content;

    private Date creation = new Date();

    private Boolean owner = true;

    private Boolean shared = false;

    private Right right;

    //CONSTRUCTORS

    public NoteDto() {}



    //GETTERS & SETTERS

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
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


    @Override
    public int compareTo(NoteDto o) {
        return getId().compareTo(o.getId());
    }
}
