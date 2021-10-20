package com.ua.pedpresa.pedpresa_form.controller;

import com.ua.pedpresa.pedpresa_form.repos.PostModRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private PostModRepo modRepo;

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("title","Главная страница");
        return "home";
    }


//    @GetMapping("/")
//    public String greeting(Map<String,Object> model) {
//        return "greeting";
//    }
//    @GetMapping("/main")
//    public String main(Map<String,Object> model) {
//        Iterable<Post> posts = postRepo.findAll();
//        model.put("posts", posts);
//        return "main";
//    }
//
//    @PostMapping("/main")
//    public String add(@RequestParam String title,@RequestParam String post, Map<String,Object> model){
//        Post post1 = new Post(title, post);
//        postRepo.save(post1);
//        Iterable<Post> posts = postRepo.findAll();
//        model.put("posts", posts);
//        return "main";
//    }
//
//    @PostMapping("filter")
//    public String filter(@RequestParam String filter, Map<String,Object> model){
//        Iterable<Post> posts;
//        if(filter != null && !filter.isEmpty()) {
//            posts = postRepo.findByTitle(filter);
//        } else {
//            posts = postRepo.findAll();
//        }
//         model.put("posts",posts);
//        return "main";
//    }

}