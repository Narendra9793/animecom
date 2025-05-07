package com.animecommunity.animecom.Controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/")
public class UserController {
    @GetMapping("")
    // @PreAuthorize("hasRole('USER')")
    public String getMethodName() {
        return "This is  userpage";
    }
}
