package com.kb.joonggo.Find;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("Find")
public class FindController {

    @Autowired
    FindRepository findRepository;

    @GetMapping("findpage")
    public String list()
    {
        return "Find/findpage";
    }


    @GetMapping("find_id")
    @ResponseBody
    public String check_id(String mbr_contact, String mbr_email, String mbr_id) {
        System.out.println("find_id 컨트롤러들어옴");
        System.out.println("입력된 mbr_contact : "+mbr_contact);
        System.out.println("입력된 mbr_email : "+mbr_email);

        if(mbr_contact.equals(""))
            return  "휴대전화를 입력해주세요.";
        else if (mbr_email.equals(""))
            return  "email을 입력해주세요.";

        Find DBFind = findRepository.find_id(mbr_contact, mbr_email);

        if(DBFind == null){
            return  "해당하는 사용자가 없습니다.";
        }

        mbr_id = DBFind.getMbr_id();

        if(DBFind != null) {
            System.out.println("-----DB값 없을 때-----");
            System.out.println("mbr_contact : "+mbr_contact);
            System.out.println("mbr_email : "+mbr_email);
            System.out.println("DB에서 가져온 mbr_id : "+mbr_id);
            return  "찾으시는 아이디 :"+mbr_id;
        }
        else {
            System.out.println("전화번호 : "+mbr_contact);
            System.out.println("이메일 : "+mbr_email);
            System.out.println("아이디 : "+mbr_id);
            return "뭔가오류남"+mbr_id;
        }
    }

    @GetMapping("find_pwd")
    @ResponseBody
    public String find_pwd(String mbr_id, String mbr_email, String mbr_pwd) {
        System.out.println("find_pwd 컨트롤러들어옴");
        System.out.println("입력된 mbr_id : "+mbr_id);
        System.out.println("입력된 mbr_email : "+mbr_email);

        if(mbr_id.equals(""))
            return  "아이디를 입력해주세요.";
        else if (mbr_email.equals(""))
            return  "email을 입력해주세요.";

        Find DBFind = findRepository.find_pwd(mbr_id, mbr_email);

        if(DBFind == null){
            return  "해당하는 사용자가 없습니다.";
        }

        mbr_pwd = DBFind.getMbr_pwd();

        if(DBFind != null) {
            System.out.println("-----DB값 있을 때-----");
            System.out.println("mbr_id : "+mbr_id);
            System.out.println("mbr_email2 : "+mbr_email);
            System.out.println("DB에서 가져온 mbr_pwd : "+mbr_pwd);
            return  "찾으시는 비밀번호 :"+mbr_pwd;
        }
        else {
            System.out.println("전화번호 : "+mbr_id);
            System.out.println("이메일 : "+mbr_email);
            System.out.println("아이디 : "+mbr_pwd);
            return "뭔가오류남"+mbr_pwd;
        }
    }
}
