package com.kb.joonggo.Tradeboard;
import com.kb.joonggo.Image.ImageDTO;
import com.kb.joonggo.Image.ImageRepository;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("Trade")
public class TradeboardController {

    @Autowired
    TradeboardRepository tradeboardRepository;

    @Autowired
    TradeboardService tradeboardService;

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
    public String writeproc(Model model,
                            MultipartFile file,
                            @Valid TradeboardReq tradeboardReq,
                            BindingResult result) {

        // 1. image 테이블 insert

        try {
            // 파일이 비어있지 않은 경우에만 처리
            if (!file.isEmpty()) {

                System.out.println("Image DB 입력을 시작합니다.");

                // 파일이름을 설정
                tradeboardReq.setImg_name(file.getOriginalFilename());
                File dest = new File(uploadPath + "/" + tradeboardReq.getImg_name());


                // 파일 경로 생성
                Path filePath = Paths.get(uploadPath, tradeboardReq.getImg_name());
                System.out.println("Full File Path: " + filePath);

                // 파일 경로 TB req에 설정
                String img_path = filePath.toString();
                tradeboardReq.setImg_path(img_path);

                try{
                    File directory = new File(uploadPath);

                    if (!directory.exists()) {
                        directory.mkdirs();
                    }
                    System.out.println(filePath);
                    System.out.println(img_path);

                    file.transferTo(dest);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // 유효성 검사
                if (result.hasErrors()) {
                    System.out.println("에러 발생");
                    System.out.println(model);
                    System.out.println(tradeboardReq);
                    return "redirect:/Freeboard/list";
                }
                System.out.println("에러 발생 X");
                System.out.println(tradeboardReq);

                // 저장하는 부분
                ImageDTO image = ImageDTO.builder()
                        .img_name(tradeboardReq.getImg_name())
                        .img_path(tradeboardReq.getImg_path())
                        .tb_idx(1) // 임시값
                        .build();
                imageRepository.save(image);
                System.out.println(image);
                System.out.println("Image DB 입력을 마칩니다.");
                System.out.println("파일 업로드 및 저장 성공!");
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
            parsedDate = dateFormat.parse(tradeboardReq.getDate());
        } catch (ParseException e) {
            e.printStackTrace();
            return "redirect:/Freeboard/list";
        }

        System.out.println(tradeboardReq);

        TradeboardDTO tradeboardDTO = TradeboardDTO.builder()
                .tb_title(tradeboardReq.getTitle())
                .tb_content(tradeboardReq.getContent())
                .tb_date(parsedDate)
                .tb_price(tradeboardReq.getPrice())
                .tb_category(tradeboardReq.getCategory())
                .tb_count(0)
                .tb_state(tradeboardReq.getState())
                .build();

        // db insert
        System.out.println("Trade DB 입력을 시작합니다.");
        tradeboardRepository.insert(tradeboardDTO);
        System.out.println("Trade DB 입력을 마칩니다.");

        // 3. image 테이블 tb_idx 업데이트
        System.out.println("image 테이블 tb_idx 업데이트 시작");
        // 지금 작업중인 img_idx 찾기
        System.out.println(tradeboardReq.getImg_name());
        System.out.println(tradeboardReq.getImg_path());
        int img_idx = imageRepository.search_img_idx(
                tradeboardReq.getImg_name(),
                tradeboardReq.getImg_path(),
                1);

        // 지금 작업중인 tb_idx 찾기
        System.out.println(tradeboardDTO.getTb_title());
        System.out.println(tradeboardDTO.getTb_content());
        System.out.println(tradeboardDTO.getTb_price());
        System.out.println(tradeboardDTO.getTb_category());
        System.out.println(tradeboardDTO.getTb_count());
        System.out.println(tradeboardDTO.getTb_state());

        int tb_idx = tradeboardRepository.search_tb_idx(
                tradeboardDTO.getTb_title(),
                tradeboardDTO.getTb_content(),
                tradeboardDTO.getTb_price(),
                tradeboardDTO.getTb_category(),
                tradeboardDTO.getTb_count(),
                tradeboardDTO.getTb_state()
        );
        System.out.println("지금 작업중인 tb_idx 번호는 "+tb_idx+" 입니다");
        System.out.println("지금 작업중인 img_idx 번호는 "+img_idx+" 입니다");

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
                tradeboardReq.getTitle(),
                tradeboardReq.getContent(),
                tradeboardReq.getPrice(),
                tradeboardReq.getCategory(),
                tradeboardReq.getCount(),
                tradeboardReq.getState()
        );

        System.out.println("tb 테이블 img_idx 업데이트 완료");
        return "redirect:/";
    }

}
