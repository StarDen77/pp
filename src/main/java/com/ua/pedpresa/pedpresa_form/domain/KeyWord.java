package com.ua.pedpresa.pedpresa_form.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "keywords")
public class KeyWord {

    @Id
    @Column(name = "id")
    private long id;
    private String key_words;
    private String tag;
    private String pict;

    public KeyWord() {
    }

    public KeyWord(String key_words, String tag, String pict) {
        this.key_words = key_words;
        this.tag = tag;
        this.pict = pict;
    }

    public String getPict() {
        return pict;
    }

    public void setPict(String pict) {
        this.pict = pict;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getKey_words() {
        return key_words;
    }

    public void setKey_words(String key_words) {
        this.key_words = key_words;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
