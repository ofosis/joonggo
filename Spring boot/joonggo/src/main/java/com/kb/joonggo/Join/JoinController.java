package com.kb.joonggo.Join;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("Join")
public class JoinController {
    @GetMapping("joinpage")
    public String list()
    {
        return "Join/joinpage";
    }
}
