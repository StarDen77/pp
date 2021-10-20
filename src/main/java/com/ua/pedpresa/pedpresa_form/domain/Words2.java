package com.ua.pedpresa.pedpresa_form.domain;

import javax.persistence.*;

@Entity
@Table(name = "words_2")
public class Words2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String word;
    private String sinonim;

    public Words2() {
    }

    public Words2(String word, String sinonim) {
        this.word = word;
        this.sinonim = sinonim;
    }

    public Long getId() {
        return id;
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
