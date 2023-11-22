package com.kb.joonggo.ThePost;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("aPost")
public class PostController {

    @GetMapping("Watchpost")
    public String list()
    {
        return "aPost/Watchpost";
    }

}
