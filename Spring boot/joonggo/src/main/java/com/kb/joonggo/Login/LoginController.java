package com.kb.joonggo.Login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("Login")
public class LoginController {
    @GetMapping("loginpage")
    public String list()
    {
        return "Login/loginpage";
    }
}
