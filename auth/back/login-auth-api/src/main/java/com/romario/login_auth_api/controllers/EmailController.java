package com.romario.login_auth_api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.romario.login_auth_api.dto.EmailDTO;
import com.romario.login_auth_api.services.EmailService;

import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/email")
public class EmailController {


    private EmailService emailService;

    @PostMapping()
    public ResponseEntity<String> enviaEmail(@RequestBody EmailDTO emailDTO) {
        return ResponseEntity.ok(emailService.enviaEmail(emailDTO));
    }
}
