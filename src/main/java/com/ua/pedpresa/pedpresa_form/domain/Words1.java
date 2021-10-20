package com.ua.pedpresa.pedpresa_form.domain;

import javax.persistence.*;

@Entity
@Table(name = "words_1")
public class Words1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String word;
    private String sinonim;

    public Words1() {
    }

    public Long getId() {
        return id;
    }

    public Words1(String word, String sinonim) {
        this.word = word;
        this.sinonim = sinonim;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getSinonim() {
        return sinonim;
    }

    public void setSinonim(String sinonim) {
        this.sinonim = sinonim;
    }
}
