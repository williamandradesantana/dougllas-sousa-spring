package io.github.williamandradesantana.libraryapi.controller;

import io.github.williamandradesantana.libraryapi.security.CustomAuthentication;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginViewController {

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/")
    @ResponseBody
    public String home(Authentication authentication) {
        if (authentication instanceof CustomAuthentication customAuthentication) {
            return "Hello World! " + customAuthentication.getName();
        }
        return "Hello World! " + authentication.getName();
    }

    @GetMapping("/authorized")
    @ResponseBody
    public String getAuthorizationCode(@RequestParam("code") String code) {
        return "Your authorization code: " + code;
    }
}
