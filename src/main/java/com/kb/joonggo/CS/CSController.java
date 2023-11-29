package com.kb.joonggo.CS;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("CS")
public class CSController {

    @GetMapping("list")
    public String list()
    {
        return "CS/list";
    }
}
