package com.kb.joonggo.Write;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("Write")
public class WriteController {

    @Autowired
    WriteRepository writeRepository;

    @GetMapping("WriteForm")
    public String writeFrom(){
        return "Write/WriteForm";
    }

    @PostMapping("writeproc")
    public String writeproc(Model model, WriteReq writeReq){

        // db에 저장
        WriteDTO writeDTO = WriteDTO.builder()
                .tb_idx(writeReq.getIdx())
                .tb_title(writeReq.getTitle())
                .tb_content(writeReq.getContent())
                .tb_date(writeReq.getDate())
                .tb_price(writeReq.getPrice())
                .tb_category(writeReq.getCategory())
                .tb_count(writeReq.getCount())
                .tb_state(writeReq.getState())
                .mbr_idx(writeReq.getMbr_idx())
                .img_idx(writeReq.getImg_idx())
                .build();

        writeRepository.insert(writeDTO);


        return "/";
    }

}