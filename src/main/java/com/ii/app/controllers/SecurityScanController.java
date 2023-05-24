package com.ii.app.controllers;

import org.springframework.http.ResponseCookie;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by SecurityScan !
 */
@RequestMapping("/api/scan")
@RestController
public class SecurityScanController {
    
    /**
     * @apiNote Store email and phone number data as part of HTTP GET parameters
     * @apiNote Store phone number in cookie
     * @param email current user Email
     * @param phoneNumber current user phoneNumber
     * @param response set phoneNumber cookie to user.
     */
    @GetMapping("/account")
    @PreAuthorize("isAuthenticated()")
    @Secured("ROLE_USER")
    public void loginScan(@RequestParam("email") String email, @RequestParam("phoneNumber")String  phoneNumber,
            HttpServletResponse response) {
        response.setHeader("Set-Cookie", "phoneNumber="+phoneNumber+";Secure;HttpOnly");
    }
    
}