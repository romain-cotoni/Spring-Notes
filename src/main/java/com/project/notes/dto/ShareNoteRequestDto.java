package com.project.notes.dto;

import com.project.notes.enumeration.Right;
import com.project.notes.model.Note;

import java.util.List;

public class ShareNoteRequestDto {

    private Right right;

    private List<String> shareUsers;

    public ShareNoteRequestDto() {}

    public Right getRight() {
        return right;
    }

    public void setRight(Right right) {
        this.right = right;
    }

    public List<String> getShareUsers() {
        return shareUsers;
    }

    public void setShareUsers(List<String> shareUsers) {
        this.shareUsers = shareUsers;
    }

}
