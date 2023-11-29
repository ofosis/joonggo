package com.kb.joonggo.Login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("Login")
public class LoginController {

    @Autowired
    LoginRepository loginRepository;


    @GetMapping("loginpage")
    public String list()
    {
        return "Login/loginpage";
    }

    @GetMapping("loginnig")
    @ResponseBody
    public String loginnig(String mbr_id, String mbr_pwd,String mbr_nick) {
        System.out.println("login 컨트롤러들어옴");
        System.out.println("입력된 mbr_id : "+mbr_id);
        System.out.println("입력된 mbr_pwd : "+mbr_pwd);

        if(mbr_id.equals(""))
            return  "아이디를 입력해주세요.";
        else if (mbr_pwd.equals(""))
            return  "비밀번호을 입력해주세요.";

        Login DBLogin = loginRepository.login(mbr_id, mbr_pwd);

        if(DBLogin == null){
            System.out.println("-----DB값 없을 때-----");
            return   "아이디 또는 비밀번호를 다시 확인해주세요.";
        }

        System.out.println("---DB불러왔음---");
        mbr_nick = DBLogin.getMbr_nick();
        mbr_id = DBLogin.getMbr_id();
        mbr_pwd = DBLogin.getMbr_pwd();

        if(DBLogin != null) {
            System.out.println("-----DB값 있을 때-----");
            System.out.println("mbr_id : "+mbr_id);
            System.out.println("mbr_pwd : "+mbr_pwd);
            System.out.println("닉네임 : "+mbr_nick);
            System.out.println("로그인 성공");
            return mbr_nick+"님 환영합니다";
        }
        else { return "뭔가오류남"+mbr_id; }
    }

}
