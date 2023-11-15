package com.kb.joonggo.Seller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("Seller")
public class SellerController {
    @GetMapping("list")
    public String list()
    {
        return "Seller/list";
    }
}
