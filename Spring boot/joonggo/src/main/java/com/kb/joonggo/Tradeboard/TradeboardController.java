package com.kb.joonggo.Tradeboard;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Controller
@RequestMapping("Trade")
public class TradeboardController {

    @Autowired
    TradeboardRepository tradeboardRepository;

//    @Value("${file.upload.path}")
//    private String uploadPath;

    @GetMapping("buylist")
    public String buylist(Model model, @RequestParam(required = false, defaultValue = "1") int BuypageNum)
    {
        try {
            model.addAttribute("BuypageNum", BuypageNum);

            BuypageNum = (BuypageNum - 1) * 3;
            List<TradeboardDTO> Buylist = tradeboardRepository.Buylist(BuypageNum);
            int countBuyRow = tradeboardRepository.countBuyRow();

            model.addAttribute("Buylist", Buylist);
            model.addAttribute("countBuyRow", countBuyRow);

            int countBuyPage = (countBuyRow / 3) + ((countBuyRow % 3 > 0) ? 1 : 0);
            model.addAttribute("countBuyPage", countBuyPage);
        }
        catch (Exception e){
            System.out.println(e.toString());
        }

        return "Trade/buylist";
    }

    @GetMapping("selllist")
    public String selllist(Model model, @RequestParam(required = false, defaultValue = "1") int SellpageNum)
    {
        try {
            model.addAttribute("SellpageNum", SellpageNum);

            SellpageNum = (SellpageNum - 1) * 3;
            List<TradeboardDTO> Selllist = tradeboardRepository.Selllist(SellpageNum);
            int countSellRow = tradeboardRepository.countSellRow();

            model.addAttribute("Selllist", Selllist);
            model.addAttribute("countSellRow", countSellRow);

            int countSellPage = (countSellRow / 3) + ((countSellRow % 3 > 0) ? 1 : 0);
            model.addAttribute("countSellPage", countSellPage);
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
        return "Trade/selllist";
    }

    @GetMapping("writeForm")
    public String writeFrom(Model model, TradeboardReq tradeboardReq){
        model.addAttribute("first","true");
        return "Trade/writeForm";
    }

    @PostMapping("writeproc")
//    public String writeproc(){

    public String writeproc(Model model,
                            @Valid TradeboardReq tradeboardReq,
                            BindingResult result) {
//
////        String originalFilename = file.getOriginalFilename();
//
////        File dest = new File(uploadPath+"/"+originalFilename);
//
////        try{
////            file.transferTo(dest);
////            // 파일이름을 boardReq에 저장
////            tradeboardReq.setImg_idx(originalFilename);
////        }catch (Exception e){
////            e.printStackTrace();
////        }
//
         //유효성 검사
        if (result.hasErrors()) {
            System.out.println("에러 발생");
            System.out.println(model);
            System.out.println(tradeboardReq);
            return "redirect:/Freeboard/list";
        }
        System.out.println("에러 발생 X");
        System.out.println(tradeboardReq);

        /* 저장하는 부분 시작 */
        // tradeboardReq 객체를 tradeboard 객체로 변환
        TradeboardDTO tradeboardDTO = TradeboardDTO.builder()
//                .originalfilename(tradeboardReq.getOriginalfilename())
                .tb_title(tradeboardReq.getTitle())
                .tb_content(tradeboardReq.getContent())
                .tb_date(tradeboardReq.getDate())
                .tb_price(tradeboardReq.getPrice())
                .tb_category(tradeboardReq.getCategory())
                .tb_count(0)
                .tb_state(tradeboardReq.getState())
                .mbr_idx(tradeboardReq.getMbr_idx())
                .img_idx(tradeboardReq.getImg_idx())
                .build();
        // db insert 하는 것
        System.out.println("DB 입력을 시작합니다.");
        tradeboardRepository.insert(tradeboardDTO);
        System.out.println("DB 입력을 마칩니다.");
        /* 저장하는 부분 끝 */


        return "redirect:/";
    }

}
