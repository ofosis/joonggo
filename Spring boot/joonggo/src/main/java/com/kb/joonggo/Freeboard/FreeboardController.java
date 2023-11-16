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

    //@Value("${file.upload.path}")
    //private String uploadPath;

    @GetMapping("list")
    public String list(Model model, @RequestParam(required = false, defaultValue = "1") int pageNum) {
        //현재 페이지 번호 속성 등록
        model.addAttribute("pageNum", pageNum);

        pageNum = (pageNum - 1) * 5;
        List<Freeboard> list = freeboardRepository.list(pageNum);
        int countRow = freeboardRepository.countRow();

        // db 테이블 list
        model.addAttribute("list", list);
        //행 갯수
        model.addAttribute("countRow", countRow);
        //페이지 갯수
        int countPage = (countRow / 5) + ((countRow % 5 > 0) ? 1 : 0);
        model.addAttribute("countPage", countPage);

        return "Freeboard/list";
    }

    @PostMapping("writeproc")
    public String writeproc(Model model,
                            @Valid FreeboardReq freeboardReq,
                            BindingResult result,
                            MultipartFile file, HttpServletRequest request) {

        String originalFilename = file.getOriginalFilename();

        // File dest = new File(uploadPath + "/" + originalFilename);

        try {
            // file.transferTo(dest);
            // 파일 이름 FreeboardReq에 저장
            freeboardReq.setOriginalfilename(originalFilename);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //유효성 검사
        if (result.hasErrors()) {
            return "Freeboard/writeform";
        }
        System.out.println(freeboardReq);
        //저장하는 부분
        //req객체를 Freeboard 객체로 변환
        Freeboard freeboard = Freeboard.builder()
                .originalfilename(freeboardReq.getOriginalfilename())
                .content(freeboardReq.getContent())
                .title(freeboardReq.getTitle())
                .name(freeboardReq.getName())
                .build();
        // db에 insert 하는 것
        freeboardRepository.insert(freeboard);
        //저장하는 부분 끝
        return "redirect:/Freeboard/list";
    }

    @GetMapping("updateform")
    public String updateform(Model model, FreeboardReq freeboardReq) {
        model.addAttribute("first", "true");

        Freeboard freeboard = freeboardRepository.selectRow(freeboardReq.getIdx());

        freeboardReq = FreeboardReq.builder()
                .idx(freeboard.getIdx())
                .content(freeboard.getContent())
                .name(freeboard.getName())
                .title(freeboard.getTitle())
                .originalfilename(freeboard.getOriginalfilename())
                .build();

        model.addAttribute("freeboardReq", freeboardReq);

        return "Freeboard/updateform";
    }

    @PostMapping("updateproc")
    public String updateproc(Model model,
                             @Valid FreeboardReq freeboardReq,
                             BindingResult result,
                             MultipartFile file) {
        String originalFilename = file.getOriginalFilename();

        System.out.println(originalFilename);

        // System.out.println(uploadPath);
        // File dest = new File(uploadPath + "/" + originalFilename);

        try {
            String path = new ClassPathResource("").getFile().getAbsolutePath();
            System.out.println(path);
            // 파일이름 Req에 저장
            freeboardReq.setOriginalfilename(originalFilename);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //유효성 검사
        if (result.hasErrors()) {
            return "Freeboard/updateform";
        }
        System.out.println(freeboardReq);


        Freeboard freeboard = Freeboard.builder()
                .originalfilename(freeboardReq.getOriginalfilename())
                .content(freeboardReq.getContent())
                .title(freeboardReq.getTitle())
                .name(freeboardReq.getName())
                .build();
        freeboardRepository.update(freeboard);
        return "redirect:/Freeboard/list";
    }

    @GetMapping("view")
    public String view(Model model, int idx) {
        Freeboard freeboard = freeboardRepository.selectRow(idx);
        model.addAttribute("freeboard", freeboard);
        return "Freeboard/view";
    }

    @PostMapping("delete")
    @ResponseBody
    public FreeboardJson delete(@RequestBody FreeboardJson freeboardJson) {
        // freeboardJson.idx = [1,2,3]
        //        List<Integer> idxList = new ArrayList<>();
        //        for (int temp : boardJson.getIdx())
        //            idxList.add(temp);
        List<Integer> idxList = Arrays.stream(freeboardJson.getIdx())
                .boxed()
                .collect(Collectors.toList());
        freeboardRepository.delete(idxList);

        FreeboardJson bj = FreeboardJson.builder().msg("성공").build();
        return bj;
    }


    //@GetMapping("/attach/{filename}")
    //public ResponseEntity<Resource> downloadAttach(@PathVariable String filename)
      //      throws MalformedURLException {

        //        FileEntity file = fileRepository.findById(id).orElse(null);

        //UrlResource resource = new UrlResource("file:" + uploadPath + "/" + filename);

        //String encodedFileName = UriUtils.encode(filename, StandardCharsets.UTF_8);

        // 파일 다운로드 대화상자가 뜨도록 하는 헤더를 설정해주는 것
        // Content-Disposition 헤더에 attachment; filename="업로드 파일명" 값을 준다.
        //String contentDisposition = "attachment; filename=\"" + encodedFileName + "\"";

        //return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition).body(resource);
    //}
}
