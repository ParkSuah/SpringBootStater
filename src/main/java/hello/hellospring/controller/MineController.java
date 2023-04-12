package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class MineController {
    @GetMapping("test")
    public String hello(Model model){
        model.addAttribute("data", "yoo hoo"); // key, value
        return "mine"; // hello.html 로 랜더링
    }
}
