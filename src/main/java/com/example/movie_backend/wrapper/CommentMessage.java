package com.example.movie_backend.wrapper;

import com.example.movie_backend.enumerate.CommentAction;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentMessage<T> {
    private CommentAction action;
    private T data;

    public CommentMessage(CommentAction action, T data) {
        this.action = action;
        this.data = data;
    }
}
