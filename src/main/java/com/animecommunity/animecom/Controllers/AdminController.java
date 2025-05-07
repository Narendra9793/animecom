package com.animecommunity.animecom.Controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/")
public class AdminController {
    @GetMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public String getMethodName() {
        return "This is  adminpage";
    }
}
