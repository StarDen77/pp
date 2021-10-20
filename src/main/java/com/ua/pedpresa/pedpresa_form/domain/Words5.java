package com.ua.pedpresa.pedpresa_form.domain;

import javax.persistence.*;

@Entity
@Table(name = "words_5")
public class Words5 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String word;
    private String sinonim;

    public Words5() {
    }

    public Words5(String word, String sinonim) {
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
