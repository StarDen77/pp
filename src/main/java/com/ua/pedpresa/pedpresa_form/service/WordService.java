package com.ua.pedpresa.pedpresa_form.service;

import com.ua.pedpresa.pedpresa_form.domain.*;
import com.ua.pedpresa.pedpresa_form.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class WordService {

    @Autowired
    private Words1Repo words1Repo;
    @Autowired
    private Words2Repo words2Repo;
    @Autowired
    private Words3Repo words3Repo;
    @Autowired
    private Words4Repo words4Repo;
    @Autowired
    private Words5Repo words5Repo;

    public WordService() {
    }

    public void addSinonim(String text_old, String text_new){

        if(!text_old.isEmpty() || !text_new.isEmpty()) {
            int titleLenth = text_old.trim().split(" ").length;

            switch (titleLenth) {
                case 1: {
                    Words1 w1 = new Words1(text_old.trim(),text_new.trim());
                    words1Repo.save(w1);
                    break;
                }
                case 2: {

                    Words2 w2 = new Words2(text_old.trim(),text_new.trim());
                    words2Repo.save(w2);
                    break;
                }
                case 3: {
                    Words3 w3 = new Words3(text_old.trim(),text_new.trim());
                    words3Repo.save(w3);
                    break;
                }
                case 4: {
                    Words4 w4 = new Words4(text_old.trim(),text_new.trim());
                    words4Repo.save(w4);
                    break;
                }
                case 5: {
                    Words5 w5 = new Words5(text_old.trim(),text_new.trim());
                    words5Repo.save(w5);
                    break;
                }
                default: {
                    System.out.println("ERROR");
                }
            }
        }
    }
}
