package com.kb.joonggo.Tradeboard;
import com.kb.joonggo.Image.ImageDTO;
import com.kb.joonggo.Image.ImageRepository;
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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Filter;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("Trade")
@CrossOrigin(origins = "http://localhost:8080") // 클라이언트의 주소에 맞게 수정
public class TradeboardController {

    @Autowired
    TradeboardRepository tradeboardRepository;

    @Autowired
    ImageRepository imageRepository;

    @Value("${file.upload.path}")
    private String uploadPath;

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

            List<TradeboardReq> BuyAlllist = tradeboardRepository.BuyAlllist(BuypageNum);
            model.addAttribute("BuyAlllist", BuyAlllist);

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

            List<TradeboardReq> SellAlllist = tradeboardRepository.SellAlllist(SellpageNum);
            model.addAttribute("SellAlllist", SellAlllist);

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
    public String writeproc(Model model,
                            MultipartFile file,
                            @Valid TradeboardReq tradeboardReq,
                            BindingResult result) {

        // 1. image 테이블 insert

        try {
            // 파일이 비어있지 않은 경우에만 처리
            if (!file.isEmpty()) {


                // 파일이름을 설정
                tradeboardReq.setImg_name(file.getOriginalFilename());
                File dest = new File(uploadPath + "/" + tradeboardReq.getImg_name());

                // 파일 경로 생성
                Path filePath = Paths.get(uploadPath, tradeboardReq.getImg_name());

                // 파일 경로 TB req에 설정
                String img_path = filePath.toString().replace(File.separator, "/");
                tradeboardReq.setImg_path(img_path);

                try{
                    File directory = new File(uploadPath);

                    if (!directory.exists()) {
                        directory.mkdirs();
                    }

                    file.transferTo(dest);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // 유효성 검사
                if (result.hasErrors()) {
                    return "redirect:/Freeboard/list";
                }

                // 저장하는 부분
                ImageDTO image = ImageDTO.builder()
                        .img_name(tradeboardReq.getImg_name())
                        .img_path(tradeboardReq.getImg_path())
                        .tb_idx(79) // 임시값(DB에 tb_idx 값이 있어야 합니다 --- 임시글 작성)
                        .build();
                imageRepository.save(image);

            } else {
                System.out.println("파일이 비어있습니다.");
            }

        } catch (Exception e) {
            model.addAttribute("message", "파일 업로드 및 저장에 실패했습니다.");
            e.printStackTrace();
        }


        // 2. tb 테이블 insert
        //유효성 검사
        if (result.hasErrors()) {
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

        TradeboardDTO tradeboardDTO = TradeboardDTO.builder()
                .tb_title(tradeboardReq.getTb_title())
                .tb_content(tradeboardReq.getTb_content())
                .tb_date(parsedDate)
                .tb_price(tradeboardReq.getTb_price())
                .tb_category(tradeboardReq.getTb_category())
                .tb_count(0)
                .tb_state(tradeboardReq.getTb_state())
//                .mbr_idx()
//                .img_idx()
                .build();

        // db insert
        tradeboardRepository.insert(tradeboardDTO);

        // 3. image 테이블 tb_idx 업데이트
        // 지금 작업중인 img_idx 찾기
        int img_idx = imageRepository.search_img_idx(
                tradeboardReq.getImg_name(),
                tradeboardReq.getImg_path(),
                79);

        int tb_idx = tradeboardRepository.search_tb_idx(
                tradeboardDTO.getTb_title(),
                tradeboardDTO.getTb_content(),
                tradeboardDTO.getTb_price(),
                tradeboardDTO.getTb_category(),
                tradeboardDTO.getTb_count(),
                tradeboardDTO.getTb_state()
        );

        // image 테이블의 tb_idx 재설정(맞춰주기)
        imageRepository.tb_idx_update(
                tb_idx,
                img_idx,
                tradeboardReq.getImg_name(),
                tradeboardReq.getImg_path()
        );
        System.out.println("image 테이블 tb_idx 업데이트 완료");

        // 4. tb 테이블 img_idx 업데이트

        System.out.println("tb 테이블 img_idx 업데이트 시작");


        tradeboardRepository.img_idx_update(
                img_idx,
                tb_idx,
                tradeboardReq.getTb_title(),
                tradeboardReq.getTb_content(),
                tradeboardReq.getTb_price(),
                tradeboardReq.getTb_category(),
                tradeboardReq.getTb_count(),
                tradeboardReq.getTb_state()
        );

        System.out.println("tb 테이블 img_idx 업데이트 완료");
        return "redirect:/";
    }

    @GetMapping(value = "productdetail", params = "tb_idx")
    private String detail(Model model, @RequestParam(name = "tb_idx", required = false) Integer tb_idx) {
        if (tb_idx != null) {
            TradeboardReq tradeboardReq = tradeboardRepository.selectRow2(tb_idx);
            model.addAttribute("TradeboardReq", tradeboardReq);
            List<TradeboardReq> showRecommend = tradeboardRepository.showRecommend(tradeboardReq.getTb_category(), tb_idx);
            model.addAttribute("showRecommend", showRecommend);
            return "Trade/productdetail";
        }
        else {
            System.out.println("idx가 없어요");
            return "error";
        }
    }

    @GetMapping("getTradeboardInfo")
    @ResponseBody
    public ResponseEntity<TradeboardReq> getTradeboardInfo(@RequestParam(name = "tb_idx", required = true) Integer tb_idx) {
        if (tb_idx != null) {
            TradeboardReq tradeboardReq = tradeboardRepository.selectRow2(tb_idx);
            if (tradeboardReq == null) {
            }
            return new ResponseEntity<>(tradeboardReq, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "sellbuylist", params = "mbr_idx")
    public String productList(Model model, @RequestParam(name = "mbr_idx", required = false) Integer mbr_idx)
    {
        if (mbr_idx != null) {
            List<TradeboardReq> selectallproduct = tradeboardRepository.selectallproduct(mbr_idx);
            List<TradeboardReq> saleSection = selectallproduct.stream()
                    .filter(product -> "판매중".equals(product.getTb_state()))
                    .collect(Collectors.toList());

            List<TradeboardReq> purchaseSection = selectallproduct.stream()
                    .filter(product -> "구매중".equals(product.getTb_state()))
                    .collect(Collectors.toList());

            model.addAttribute("saleSection", saleSection);
            model.addAttribute("purchaseSection", purchaseSection);
            return "Trade/sellbuylist";
        }
        else{
            System.out.println("idx가 없어요");
            return "error";
        }
    }

    @GetMapping("getsellbuylist")
    @ResponseBody
    public ResponseEntity<List<TradeboardReq>> getsellbuylist(@RequestParam(name = "mbr_idx", required = true) Integer mbr_idx) {
        if (mbr_idx != null) {
            List<TradeboardReq> selectallproduct = tradeboardRepository.selectallproduct(mbr_idx);
            if (selectallproduct == null) {
            }
            return new ResponseEntity<>(selectallproduct, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
