package com.kb.joonggo.Freeboard;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("Freeboard")
public class FreeboardController {

    @Autowired
    FreeboardRepository freeboardRepository;

    @Value("${file.upload.path}")


    @GetMapping("list")
    public String list(Model model, @RequestParam(required = false, defaultValue = "1") int pageNum) {
        model.addAttribute("pageNum", pageNum);

        pageNum = (pageNum - 1) * 5;
        List<FreeBoard> list = freeboardRepository.list(pageNum);
        int countRow = freeboardRepository.countRow();

        // db테이블 list
        model.addAttribute("list", list);
        // 행 갯수
        model.addAttribute("countRow", countRow);
        // 페이지 갯수
        int countPage = (countRow / 5) + ((countRow % 5 > 0) ? 1 : 0);
        model.addAttribute("countPage", countPage);
        return "FreeBoard/list";
    }

    @GetMapping("writeform")
    public String writeform(Model model, FreeboardReq freeboardReq){
        model.addAttribute("first","true");
        return "Fileboard/wrtieform";
    }

   /* @PostMapping("writeproc")
    public String writeproc(Model model,
                            @Valid FreeboardReq freeboardReq,
                            BindingResult result,
                            MultipartFile file, HttpServletRequest request) {

        String originalFilename = file.getOriginalFilename();

        File dest = new File(uploadPath+"/"+originalFilename);

        try{
            file.transferTo(dest);
            // 파일이름을 boardReq에 저장
            freeboardReq.setOriginalfilename(originalFilename);
        }catch (Exception e){
            e.printStackTrace();
        }

        // 유효성 검사
        if (result.hasErrors()) {
            return "board/writeform";
        }
        System.out.println(boardReq);
        *//* 저장하는 부분 시작 *//*
        // boardReq 객체를 Board 객체로 변환
        Board board = Board.builder()
                .originalfilename(boardReq.getOriginalfilename())
                .content(boardReq.getContent())
                .title(boardReq.getTitle())
                .name(boardReq.getName())
                .build();
        // db insert 하는 것
        boardRepository.insert(board);
        *//* 저장하는 부분 끝 *//*
        return "redirect:/board/list";
    }*/

  /*  @GetMapping("updateform")
    public String updateform(Model model, FreeboardReq freeboardReq) {
        model.addAttribute("first", "true");

        FreeBoard freeBoard = FreeboardReq.selectRow(freeboardReq.getIdx());

        freeboardReq = FreeboardReq.builder()
                .fr_idx(freeBoard.getFr_idx())
                .content(board.getContent())
                .name(board.getName())
                .title(board.getTitle())
                .originalfilename(board.getOriginalfilename())
                .build();

        model.addAttribute("freeboardReq",freeboardReq);

        return "Freeboard/updateform";
    }*/

    /*@PostMapping("updateproc")
    public String updateproc(Model model,
                            @Valid BoardReq boardReq,
                            BindingResult result,
                            MultipartFile file) {
        String originalFilename = file.getOriginalFilename();

        System.out.println(originalFilename);

        System.out.println(uploadPath);
        File dest = new File(uploadPath+"/"+originalFilename);

        try{
            String path = new ClassPathResource("").getFile().getAbsolutePath();
            System.out.println(path);

            file.transferTo(dest);
            // 파일이름을 boardReq에 저장
            boardReq.setOriginalfilename(originalFilename);
        }catch (Exception e){
            e.printStackTrace();
        }

        // 유효성 검사
        if (result.hasErrors()) {
            return "board/updateform";
        }
        System.out.println(boardReq);
        *//* 저장하는 부분 시작 *//*
        // boardReq 객체를 Board 객체로 변환
        Board board = Board.builder()
                .idx(boardReq.getIdx())
                .originalfilename(boardReq.getOriginalfilename())
                .content(boardReq.getContent())
                .title(boardReq.getTitle())
                .name(boardReq.getName())
                .build();
        // db insert 하는 것
        boardRepository.update(board);
        *//* 저장하는 부분 끝 *//*
        return "redirect:/board/list";
    }*/

    /*@GetMapping("view")
    public String view(Model model,int idx){
        FreeBoard freeBoard = freeboardRepository.selectRow(idx);
        model.addAttribute("Freeboard",FreeBoard);
        return "board/view";
    }*/

 /*   @PostMapping("delete")
    @ResponseBody
    public BoardJson delete(@RequestBody BoardJson boardJson){
        // boardJson.idx = [1,2,3]
//        List<Integer> idxList = new ArrayList<>();
//        for (int temp : boardJson.getIdx())
//            idxList.add(temp);
        List<Integer> idxList = Arrays.stream(boardJson.getIdx())
                                .boxed()
                                .collect(Collectors.toList());
        boardRepository.delete(idxList);

        BoardJson bj = BoardJson.builder().msg("성공").build();
        return bj;
    }


    @GetMapping("/attach/{filename}")
    public ResponseEntity<Resource> downloadAttach(@PathVariable String filename)
            throws MalformedURLException {

//        FileEntity file = fileRepository.findById(id).orElse(null);

        UrlResource resource = new UrlResource("file:" + uploadPath+"/"+filename);

        String encodedFileName = UriUtils.encode(filename, StandardCharsets.UTF_8);

        // 파일 다운로드 대화상자가 뜨도록 하는 헤더를 설정해주는 것
        // Content-Disposition 헤더에 attachment; filename="업로드 파일명" 값을 준다.
        String contentDisposition = "attachment; filename=\"" + encodedFileName + "\"";

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,contentDisposition).body(resource);
    }
*/}
