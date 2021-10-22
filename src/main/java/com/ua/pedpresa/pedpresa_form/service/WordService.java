package com.ua.pedpresa.pedpresa_form.service;

import com.ua.pedpresa.pedpresa_form.domain.*;
import com.ua.pedpresa.pedpresa_form.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;


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

    public void addSinonim(String text_old, String text_new) {

        if (!text_old.isEmpty() || !text_new.isEmpty()) {
            int titleLenth = text_old.trim().split(" ").length;

            switch (titleLenth) {
                case 1: {
                    Words1 w1 = new Words1(text_old.trim(), text_new.trim());
                    words1Repo.save(w1);
                    break;
                }
                case 2: {

                    Words2 w2 = new Words2(text_old.trim(), text_new.trim());
                    words2Repo.save(w2);
                    break;
                }
                case 3: {
                    Words3 w3 = new Words3(text_old.trim(), text_new.trim());
                    words3Repo.save(w3);
                    break;
                }
                case 4: {
                    Words4 w4 = new Words4(text_old.trim(), text_new.trim());
                    words4Repo.save(w4);
                    break;
                }
                case 5: {
                    Words5 w5 = new Words5(text_old.trim(), text_new.trim());
                    words5Repo.save(w5);
                    break;
                }
                default: {
                    System.out.println("ERROR");
                }
            }
        }
    }

    public Model getWords(int z, Model model) {

        switch (z) {
            case 1: {
                return model.addAttribute("list", words1Repo.findAll());
            }
            case 2: {
                return model.addAttribute("list", words2Repo.findAll());
            }
            case 3: {
                return model.addAttribute("list", words3Repo.findAll());
            }
            case 4: {
                return model.addAttribute("list", words4Repo.findAll());
            }
            case 5: {
                return model.addAttribute("list", words5Repo.findAll());
            }
            default: {
                System.out.println("ERROR");
            }
        }
        return model.addAttribute("list", words5Repo.findAll());
    }

    public Model delWords(int z,  long id, Model model) {

        switch (z) {
            case 1: {
                words1Repo.deleteById(id);
                break;
            }
            case 2: {
                words2Repo.deleteById(id);
                break;
            }
            case 3: {
                words3Repo.deleteById(id);
                break;
            }
            case 4: {
                words4Repo.deleteById(id);
                break;
            }
            case 5: {
                words5Repo.deleteById(id);
                break;
            }
            default: {
                System.out.println("ERROR");
            }
        }
        return  getWords(z, model);
    }
}
