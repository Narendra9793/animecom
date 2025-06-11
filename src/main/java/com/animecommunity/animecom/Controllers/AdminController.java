package com.animecommunity.animecom.Controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/")
public class AdminController {
    @GetMapping("test")
    @PreAuthorize("hasRole('ADMIN')")
    public String getMethodName() {
        return "This is  adminpage";
    }

    //http://localhost:5050/api/admin/all-posts
    //http://localhost:5050/api/admin/all-answers
    //http://localhost:5050/api/admin/all-theories
    //http://localhost:5050/api/admin/all-questions
    
    //http://localhost:5050/api/admin/delete/{targetType}/{targetId}
    //http://localhost:5050/api/admin/down/{targetType}/{targetId}
    //http://localhost:5050/api/admin/age-restrict/{targetType}/{targetId}


}
