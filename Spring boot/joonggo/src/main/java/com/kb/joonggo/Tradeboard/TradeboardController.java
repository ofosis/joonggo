package com.kb.joonggo.Tradeboard;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("Trade")
@CrossOrigin(origins = "http://localhost:8080") // 클라이언트의 주소에 맞게 수정
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

        // 문자열로 된 date를 Date로 변환
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date parsedDate;
        try {
            parsedDate = tradeboardReq.getTb_date();
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/Freeboard/list";
        }

        System.out.println(tradeboardReq);



        /* 저장하는 부분 시작 */
        // tradeboardReq 객체를 tradeboard 객체로 변환
        TradeboardDTO tradeboardDTO = TradeboardDTO.builder()
//                .originalfilename(tradeboardReq.getOriginalfilename())
                .tb_title(tradeboardReq.getTb_title())
                .tb_content(tradeboardReq.getTb_content())
                .tb_date(parsedDate)
                .tb_price(tradeboardReq.getTb_price())
                .tb_category(tradeboardReq.getTb_category())
                .tb_count(0)
                .tb_state(tradeboardReq.getTb_state())
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

    @GetMapping("productdetail")
    private String detail(Model model, @RequestParam(name = "tb_idx", required = false) Integer tb_idx) {
        if (tb_idx != null) {
            TradeboardReq tradeboardReq = tradeboardRepository.selectRow2(tb_idx);
            model.addAttribute("TradeboardReq",tradeboardReq);
            return "Trade/productdetail";
        } else {
            System.out.println("idx가 없어요");
            return "error"; // 또는 다른 에러 처리 로직
        }
    }

    @GetMapping("getTradeboardInfo")
    @ResponseBody
    public ResponseEntity<TradeboardReq> getTradeboardInfo(@RequestParam(name = "tb_idx", required = true) Integer tb_idx) {
        if (tb_idx != null) {
            TradeboardReq tradeboardReq = tradeboardRepository.selectRow2(tb_idx);
            System.out.println(tradeboardReq);
            if (tradeboardReq.getTb_date() == null) {
                tradeboardReq.setTb_date(new Date());
            }

            return new ResponseEntity<>(tradeboardReq, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
