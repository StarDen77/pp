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

import java.util.*;

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
    public String newsList(Model model) {
        Iterable<PostMod> postMods = postModRepo.findAll();
        model.addAttribute("posts", postMods);
        return "news";
    }

    @GetMapping("/edit/{id}")
    public String newsEdit(@PathVariable(value = "id") long id, Model model) {
        if (!postModRepo.existsById(id)) return "news";
        Optional<PostMod> editPost = postModRepo.findById(id);
        List<PostMod> res = new ArrayList<>();
        editPost.ifPresent(res::add);
        model.addAttribute("post", res);
        return "edit";
    }

    @GetMapping("/edit/{id}/delete")
    public String newsDelete(@PathVariable(value = "id") long id, Model model) {
        if (!postModRepo.existsById(id)) return "news";
        postModRepo.deleteById(id);
        Iterable<PostMod> postMods = postModRepo.findAll();
        model.addAttribute("posts", postMods);
        return "redirect:/news";
    }

    @PostMapping("/edit/{id}")
    public String newsChange(
            @PathVariable(value = "id") long id,
            @RequestParam(required = false) String text_old,
            @RequestParam(required = false) String text_new,
            Model model) {
        wordService.addSinonim(text_old, text_new);
        Optional<PostMod> editPost = postModRepo.findById(id);
        List<PostMod> res = new ArrayList<>();
        editPost.ifPresent(res::add);
        if (res.size() == 1) {
            PostMod pm = res.get(0);
            pm.setTitle_mod(pm.getTitle_mod().replaceAll(text_old, text_new));
            pm.setText_mod(pm.getText_mod().replaceAll(text_old, text_new));
            pm.setText_mod_tag(pm.getText_mod_tag().replaceAll(text_old, text_new));
            postModRepo.save(pm);
            res.set(0, pm);

        }
        model.addAttribute("post", res);
        Map<String,Integer> freq = new HashMap<>();
        freq.put("Word 1",7);
        freq.put("Word 2",2);
        freq.put("Word 3",1);
        model.addAttribute("freq",freq);

        return "edit";
    }

    @GetMapping("/keywords")
    public String keyWordsEdit(Model model) {
        Iterable<KeyWord> keyWords = keyWordRepo.findAll();
        model.addAttribute("keywords", keyWords);
        return "keywords";
    }

    @PostMapping("/keywords")
    public String keyWordsInfo(
            @RequestParam(required = true) String new_key,
            @RequestParam(required = false) String new_tag,
            @RequestParam(required = false) String new_pict,
            Model model) {
        if (!new_key.isEmpty()) {
            KeyWord keyWord = new KeyWord(new_key, new_tag, new_pict);
            keyWordRepo.save(keyWord);
        }
        Iterable<KeyWord> keyWords = keyWordRepo.findAll();
        model.addAttribute("keywords", keyWords);
        return "keywords";
    }

    @GetMapping("/keyedit/{id}")
    public String keyEdit(
            @PathVariable(value = "id") long id,
            Model model) {
        Optional<KeyWord> editKey = keyWordRepo.findById(id);
        List<KeyWord> res = new ArrayList<>();
        editKey.ifPresent(res::add);
        model.addAttribute("keywords", res);
        return "keyedit";
    }

    @GetMapping("/keyedit/{id}/delete")
    public String keyDelete(
            @PathVariable(value = "id") long id,
            Model model) {
        if (!keyWordRepo.existsById(id)) return "redirect:/keywords";
        keyWordRepo.deleteById(id);
        Iterable<KeyWord> res = keyWordRepo.findAll();
        model.addAttribute("keywords", res);
        return "redirect:/keywords";
    }

    @PostMapping("/keyedit/{id}")
    public String keyDetails(
            @PathVariable(value = "id") long id,
            @RequestParam(required = false) String key,
            @RequestParam(required = false) String tag,
            @RequestParam(required = false) String pict,
            Model model) {
        if (!key.isEmpty()) {
            KeyWord keyWord = new KeyWord(id, key, tag, pict);
            keyWordRepo.save(keyWord);
        }
        Iterable<KeyWord> keyWords = keyWordRepo.findAll();
        model.addAttribute("keywords", keyWords);
        return "keywords";
    }

    @GetMapping("/auto/{kw}")
    public String keyRep(
            @PathVariable(value = "kw") int kw, Model model) {
        if (kw < 1 || kw > 5) return "auto";
        wordService.getWords(kw, model);
        return "auto";
    }
    @GetMapping("/auto/{kw}/delete/{id}")
    public String keyDel(
            @PathVariable(value = "kw") int kw,
            @PathVariable(value = "id") long id,
            Model model) {
        if (kw < 1 || kw > 5) return "auto";
        wordService.delWords(kw, id, model);
        return "auto";
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
