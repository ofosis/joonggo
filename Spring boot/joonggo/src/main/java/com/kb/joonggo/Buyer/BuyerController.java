package com.kb.joonggo.Buyer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("Buyer")
public class BuyerController {

    @GetMapping("list")
    public String list()
    {
        return "Buyer/list";
    }



}
