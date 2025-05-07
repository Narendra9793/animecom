package com.animecommunity.animecom.Controllers;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/home")
public class HomeController {
    @GetMapping("")
    public String getMethodName() {
        return "This is  homepage";
    }
    
}
