package com.kb.joonggo.Seller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("Seller")
public class SellerController {
    @Autowired
    SellerRepository sellerRepository;

    @GetMapping("list")
    public String list(Model model, @RequestParam(required = false, defaultValue = "1") int pageNum)
    {
        try {
            model.addAttribute("pageNum", pageNum);

            pageNum = (pageNum - 1) * 5;
            List<SellerDTO>list = sellerRepository.list(pageNum);
            int countRow = sellerRepository.countRow();

            model.addAttribute("list", list);
            model.addAttribute("countRow", countRow);

            int countPage = (countRow / 5) + ((countRow % 5 > 0) ? 1 : 0);
            model.addAttribute("countPage", countPage);
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
        return "Seller/list";
    }
}
