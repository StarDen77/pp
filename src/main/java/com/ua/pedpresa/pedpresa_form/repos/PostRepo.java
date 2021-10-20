package com.ua.pedpresa.pedpresa_form.repos;

import com.ua.pedpresa.pedpresa_form.domain.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepo extends CrudRepository<Post,Integer> {
        List<Post> findByTitle(String title_origin);
}

