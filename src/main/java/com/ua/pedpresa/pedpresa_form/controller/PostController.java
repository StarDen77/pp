package com.ua.pedpresa.pedpresa_form.controller;

import com.ua.pedpresa.pedpresa_form.domain.KeyWord;
import com.ua.pedpresa.pedpresa_form.domain.PostMod;
import com.ua.pedpresa.pedpresa_form.repos.KeyWordRepo;
import com.ua.pedpresa.pedpresa_form.repos.PostModRepo;
import com.ua.pedpresa.pedpresa_form.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class PostController {

    private final PostModRepo postModRepo;

    @Autowired
    private WordService wordService;
    @Autowired
    private KeyWordRepo keyWordRepo;

    public PostController(PostModRepo postModRepo) {
        this.postModRepo = postModRepo;
    }


    @GetMapping("/news")
    public String newsList(Model model){
        Iterable<PostMod> postMods = postModRepo.findAll();
        model.addAttribute("posts",postMods);
    return "news";
    }

    @GetMapping("/edit/{id}")
    public String newsEdit(@PathVariable (value = "id") long id, Model model){
        if(!postModRepo.existsById(id)) return "news";
        Optional<PostMod> editPost = postModRepo.findById(id);
        List<PostMod> res = new ArrayList<>();
        editPost.ifPresent(res::add);
        model.addAttribute("post",res);
        return "edit";
    }
    @GetMapping("/edit/{id}/delete")
    public String newsDelete(@PathVariable (value = "id") long id, Model model) {
        if (!postModRepo.existsById(id)) return "news";
        postModRepo.deleteById(id);
        Iterable<PostMod> postMods = postModRepo.findAll();
        model.addAttribute("posts",postMods);
        return "news";
    }

    @PostMapping("/edit/{id}")
    public String newsChange(
            @PathVariable (value = "id") long id,
            @RequestParam(required = false) String text_old,
            @RequestParam(required = false) String text_new,
                    Model model) {
        wordService.addSinonim(text_old,text_new);
        Optional<PostMod> editPost = postModRepo.findById(id);
        List<PostMod> res = new ArrayList<>();
        editPost.ifPresent(res::add);
        if(res.size() == 1) {
            PostMod pm = res.get(0);
            pm.setTitle_mod(pm.getTitle_mod().replaceAll(text_old,text_new));
            pm.setText_mod(pm.getText_mod().replaceAll(text_old,text_new));
            pm.setText_mod_tag(pm.getText_mod_tag().replaceAll(text_old,text_new));
            postModRepo.save(pm);
            res.set(0,pm);

        }
        model.addAttribute("post",res);
        return "edit";
    }
    @GetMapping("/keywords")
    public String keyWordsEdit(Model model){
        Iterable<KeyWord> keyWords = keyWordRepo.findAll();
        model.addAttribute("keywords",keyWords);
        return "keywords";
    }
    @PostMapping("/keywords")
    public String keyWordsEdit(
            @RequestParam(required = true, defaultValue = "") String new_key,
            @RequestParam(required = false) String new_tag,
            @RequestParam(required = false) String new_pict,
            Model model){
        KeyWord keyWord = new KeyWord(new_key, new_tag, new_pict);
        keyWordRepo.save(keyWord);
        Iterable<KeyWord> keyWords = keyWordRepo.findAll();
        model.addAttribute("keywords",keyWords);
        return "keywords";
    }
}
