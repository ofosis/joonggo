package com.kb.joonggo.Buyer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("Buyer")
public class BuyerController {

    @Autowired
    BuyRepository buyRepository;

    @Autowired
    BuyerReq buyerReq;

    @GetMapping("list")
    public String list(Model model)
    {
        List<BuyerReq> list =
        return "Buyer/list";
    }


<<<<<<< HEAD
=======

>>>>>>> 052e99cdcfae378bb077e4474a9fc2e82867e657
}
