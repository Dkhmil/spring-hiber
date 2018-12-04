package com.example.sweater.domain;

import javax.persistence.*;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String tag;
    private String text;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private User author;

    public User getAutor() {
        return author;
    }

    public String getAuthorName() {
        return author != null ? author.getUsername() : "<none>";

    }

    public void setAutor(User autor) {
        this.author = autor;
    }


    public Message() {
    }

    public Message(String tag, String text, User author) {
        this.tag = tag;
        this.text = text;
        this.author = author;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
