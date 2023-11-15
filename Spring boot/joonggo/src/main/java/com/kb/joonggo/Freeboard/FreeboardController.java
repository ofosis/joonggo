package com.kb.joonggo.Freeboard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("Freeboard")
public class FreeboardController {
    @GetMapping("list")
    public String list()
    {
        return "Freeboard/list";
    }
}
