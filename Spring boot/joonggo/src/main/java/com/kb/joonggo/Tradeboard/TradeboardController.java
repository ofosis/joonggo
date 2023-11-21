package com.kb.joonggo.Tradeboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("Trade")
public class TradeboardController {

    @Autowired
    TradeboardRepository tradeboardRepository;

    @GetMapping("buylist")
    public String buylist(Model model, @RequestParam(required = false, defaultValue = "1") int pageNum)
    {
        try {
            model.addAttribute("pageNum", pageNum);

            pageNum = (pageNum - 1) * 5;
            List<TradeboardDTO> Buylist = tradeboardRepository.Buylist(pageNum);
            int countBuyRow = tradeboardRepository.countBuyRow();

            model.addAttribute("Buylist", Buylist);
            model.addAttribute("countBuyRow", countBuyRow);

            int countBuyPage = (countBuyRow / 5) + ((countBuyRow % 5 > 0) ? 1 : 0);
            model.addAttribute("countBuyPage", countBuyPage);
        }
        catch (Exception e){
            System.out.println(e.toString());
        }

        return "Trade/buylist";
    }

    @GetMapping("selllist")
    public String selllist(Model model, @RequestParam(required = false, defaultValue = "1") int pageNum)
    {
        try {
            model.addAttribute("pageNum", pageNum);

            pageNum = (pageNum - 1) * 5;
            List<TradeboardDTO> Selllist = tradeboardRepository.Selllist(pageNum);
            int countSellRow = tradeboardRepository.countSellRow();

            model.addAttribute("Selllist", Selllist);
            model.addAttribute("countSellRow", countSellRow);

            int countSellPage = (countSellRow / 5) + ((countSellRow % 5 > 0) ? 1 : 0);
            model.addAttribute("countSellPage", countSellPage);
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
        return "Trade/selllist";
    }

}
