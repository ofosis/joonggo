package com.kb.joonggo.Seller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;


@Controller
@RequestMapping("Seller")
public class SellerController {

    @Autowired
    SellerRepository sellerRepository;

    @GetMapping("list")
    public String list(Model model)
    {


        List<Seller> list = sellerRepository.list();
//        int countRow = sellerRepository.countRow();

        // db 테이블 list
        model.addAttribute("list", list);

//        model.addAttribute("countRow", countRow);

        return "/Seller/list";
    }
}
