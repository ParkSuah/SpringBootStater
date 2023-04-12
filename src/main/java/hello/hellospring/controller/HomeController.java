package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/") // localhost:8080으로 들어오면 맨 처음 호출 되는 것 (우선순위가 index.html 보다 높음)
    public String home(){
        return "home";
    }
}
