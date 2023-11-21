package com.kb.joonggo.Join;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("Join")
public class JoinController {

    @Autowired
    JoinRepository joinRepository;

    @GetMapping("joinpage")
    public String list()
    {
        return "Join/joinpage";
    }

    @PostMapping("joininsert")
    public String joininsert(JoinReq joinReq) {
        System.out.println("joininsert 컨트롤러들어옴");

        System.out.println(joinReq);


        Join join = Join.builder()
                        .mbr_nick(joinReq.getMbr_nick())
                        .mbr_id(joinReq.getMbr_id())
                        .mbr_pwd(joinReq.getMbr_pwd())
                        .mbr_name(joinReq.getMbr_name())
                        .mbr_contact(joinReq.getMbr_contact())
                        .mbr_email(joinReq.getMbr_email())
                        .mbr_addr(joinReq.getMbr_addr())
                        .build();
        joinRepository.insert(join);

        System.out.println("회원가입완료");

        return "redirect:/Login/loginpage";
    }
    @GetMapping("check_id")
    @ResponseBody
    public String check_id(String id) {
        System.out.println("check_id 컨트롤러들어옴");

        Join DBjoin = joinRepository.check_id(id);

        if(DBjoin != null) {
            System.out.println("id :"+id);
            return  "fail";
        }
        else {
            System.out.println("id :"+id);
            return "suc";
        }


    }

}


