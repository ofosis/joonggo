package com.kb.joonggo.Freeboard;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("Freeboard")
public class FreeboardController {

    private final FreeboardService freeboardService;

    @Autowired
    public FreeboardController(FreeboardService freeboardService) {
        this.freeboardService = freeboardService;
    }

    @Autowired
    FreeboardRepository freeboardRepository;

    @Value("${file.upload.path}")
    private String uploadPath;

    @GetMapping("list")
    public String list(Model model, @RequestParam(required = false) Integer pageNum) {
        pageNum = (pageNum != null && pageNum > 0) ? pageNum : 1;

        int offset = (pageNum - 1) * 5;
        List<FreeBoard> list = freeboardRepository.list(offset);
        int countRow = freeboardRepository.countRow();

        int countPage = (countRow / 5) + ((countRow % 5 > 0) ? 1 : 0);

        model.addAttribute("pageNum", pageNum);
        model.addAttribute("list", list);
        model.addAttribute("countRow", countRow);
        model.addAttribute("countPage", countPage);

        return "Freeboard/list";
    }


    @GetMapping("view")
    public String view(Model model, int fr_idx) {
        // 글을 조회할 때 조회수 증가
        freeboardRepository.updateViewCount(fr_idx);

        FreeboardReq freeboardreq = freeboardRepository.selectRow(fr_idx);
        model.addAttribute("FreeboardReq", freeboardreq);
        System.out.println(freeboardreq);
        return "Freeboard/view";
    }

//    @GetMapping("writeform")
//    public String writefrom(Model model, FreeboardReq freeboardReq) {
//        model.addAttribute("first", "true");
//        return "Freeboard/writeform";
//    }

    @GetMapping("writeform")
    public String writeform(Model model) {
        model.addAttribute("FreeboardReq", new FreeboardReq()); // FreeboardReq 객체를 생성하고 모델에 추가
        model.addAttribute("first", "true");
        return "Freeboard/writeform";
    }

    @PostMapping("writeproc")
    public String writeproc(Model model,
                            @Valid FreeboardReq freeboardReq,
                            BindingResult result) {

        if (result.hasErrors()) {
            System.out.println("Binding Result has errors:");
            for (ObjectError error : result.getAllErrors()) {
                System.out.println(error);
            }
            model.addAttribute("FreeboardReq", freeboardReq);
            System.out.println(freeboardReq);
            return "Freeboard/writeform";
        }

        // 작성 시간 설정
        freeboardReq.setCreated_at(LocalDateTime.now());

        /* 저장하는 부분 시작 */
        FreeBoard freeboard = FreeBoard.builder()
                .fr_content(freeboardReq.getFr_content())
                .fr_title(freeboardReq.getFr_title())
                .mbr_idx(freeboardReq.getMbr_idx())
                .created_at(freeboardReq.getCreated_at())  // 작성 시간 추가
                .build();

        freeboardRepository.insert(freeboard);
        /* 저장하는 부분 끝 */

        return "redirect:/Freeboard/list";
    }


    @GetMapping("updateform")
    public String updateform(Model model, FreeboardReq freeboardReq) {
        model.addAttribute("first", "true");

        FreeboardReq freeboard = freeboardRepository.selectRow(freeboardReq.getFr_idx());

        freeboardReq = FreeboardReq.builder()
//                .fr_idx(freeboard.getFr_idx())
                .fr_content(freeboard.getFr_content())
                .fr_title(freeboard.getFr_title())
                .build();

        model.addAttribute("freeboardReq", freeboardReq);

        return "Freeboard/updateform";
    }

    @GetMapping("/search")
    public String search(@RequestParam String query, Model model) {
        List<FreeBoard> searchResults = freeboardService.searchByTitleOrContent(query);
        model.addAttribute("list", searchResults);
        return "Freeboard/list";
    }

    @PostMapping("updateproc")
    public String updateproc(Model model,
                             @Valid FreeboardReq freeboardReq,
                             BindingResult result,
                             MultipartFile file) {

        // 유효성 검사
        if (result.hasErrors()) {
            model.addAttribute("FreeboardReq", freeboardReq);
            return "Freeboard/updateform";
        }

        // 작성 시간 업데이트
        freeboardReq.setCreated_at(LocalDateTime.now());

        /* 저장하는 부분 시작 */
        // boardReq 객체를 Board 객체로 변환
        FreeBoard freeboard = FreeBoard.builder()
                .fr_idx(freeboardReq.getFr_idx()) // 업데이트 시에는 기존의 게시물을 업데이트해야 하므로 ID를 설정
                .fr_content(freeboardReq.getFr_content())
                .fr_title(freeboardReq.getFr_title())
                .created_at(freeboardReq.getCreated_at())  // 작성 시간 업데이트
                .build();

        // db update
        FreeboardRepository.update(freeboard);
        /* 저장하는 부분 끝 */

        return "redirect:/Freeboard/list";
    }
}