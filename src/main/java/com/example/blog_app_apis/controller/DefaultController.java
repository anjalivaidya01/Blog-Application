package com.example.blog_app_apis.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@CrossOrigin(origins = "*")
public class DefaultController {


//    @GetMapping("/")
//    public String redirectToSwagger() {
//        return "redirect:/swagger-ui/index.html";
//    }
@GetMapping("/")
public RedirectView redirectToSwagger() {
    return new RedirectView("/swagger-ui/index.html");
}

}
