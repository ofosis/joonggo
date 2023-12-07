package com.kb.joonggo.Chat;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("chat")
public class ChatController {

    @GetMapping("showchat")
    public String showchat(){
        return "chat/showchat";
    }
}
