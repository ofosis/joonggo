package com.kb.joonggo.Find;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("Find")
public class FindController {
    @GetMapping("findpage")
    public String list()
    {
        return "Find/findpage";
    }
}
