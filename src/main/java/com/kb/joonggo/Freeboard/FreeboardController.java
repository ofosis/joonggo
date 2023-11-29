package com.kb.joonggo.Freeboard;

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
@RequestMapping("Freeboard")
public class FreeboardController {

    @Autowired
    FreeboardRepository freeboardRepository;

//    @Value("${file.upload.path}")
//    private String uploadPath;

    @GetMapping("list")
    public String list(Model model, @RequestParam(required = false, defaultValue = "1") int pageNum) {
//    public String list(Model model) {
        model.addAttribute("pageNum", pageNum);

        pageNum = (pageNum - 1) * 5;
        List<FreeBoard> list = freeboardRepository.list(pageNum);
        int countRow = freeboardRepository.countRow();

        model.addAttribute("list", list);

        model.addAttribute("countRow", countRow);

        int countPage = (countRow / 5) + ((countRow % 5 > 0) ? 1 : 0);
        model.addAttribute("countPage", countPage);
     // List<FreeBoard> list = freeboardRepository.list();
     //   model.addAttribute("list",list);

        return "Freeboard/list";
    }
    @GetMapping("view")
    public String view(Model model,int fr_idx){
        FreeboardReq freeboardreq = freeboardRepository.selectRow(fr_idx);
        model.addAttribute("FreeboardReq",freeboardreq);
        System.out.println(freeboardreq);
        return "Freeboard/view";
    }

//    @GetMapping("writeform")
//    public String writeform(Model model, FreeboardReq freeboardReq) {
//
//        model.addAttribute("first", "true");
//        return "Freeboard/writeform";
//    }
//
//    @PostMapping("writeproc")
//    public String writeproc(Model model,
//                            @Valid FreeboardReq freeboardReq,
//                            BindingResult result,
//                            MultipartFile file, HttpServletRequest request) {
//        String originalFilename = file.getOriginalFilename();
//
//        File dest = new File(uploadPath + "/" + originalFilename);
//
//        try {
//            file.transferTo(dest);
//            // 파일이름 FreeboardReq 에 저장
//            freeboardReq.setOriginalfilename(originalFilename);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        // 유효성검사
//        if (result.hasErrors()) {
//            return "Freeboard/writerform";
//        }
//        System.out.println(freeboardReq);
//        /* 저장하는 부분 시작 */
//        // FreeboardReq 객체를 FreeBoard 객체로 변환
//        FreeBoard freeBoard = FreeBoard.builder()
//                .originalfilename(freeboardReq.getOriginalfilename())
//                .fr_content(freeboardReq.getFr_content())
//                .fr_title(freeboardReq.getFr_title())
//                .
//                .build();
//
//    }
}