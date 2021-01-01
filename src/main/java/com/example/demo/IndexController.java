package com.example.demo;

import com.example.demo.config.auth.LoginUser;
import com.example.demo.config.auth.dto.SessionUser;
import com.example.demo.domain.posts.Posts;
import com.example.demo.service.posts.PostsService;
import com.example.demo.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;

@RequiredArgsConstructor
@Controller
public class IndexController {

   private final PostsService postsService;

    @GetMapping("/")
    public String index(@PageableDefault Pageable pageable, Model model, @LoginUser SessionUser user){

        Page<Posts> list = postsService.findAllDesc(pageable);
        ArrayList<Integer> page = new ArrayList<>();
        for(int i =0;i<list.getTotalPages();i++){
            page.add(i+1);
        }

        model.addAttribute("posts",list);

        model.addAttribute("page",page);

        if(user!=null){
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post",dto);

        return "posts-update";
    }
}
