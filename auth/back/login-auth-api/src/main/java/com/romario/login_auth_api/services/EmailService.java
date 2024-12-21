package com.romario.login_auth_api.services;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.romario.login_auth_api.dto.EmailDTO;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String remetente;

    public String enviaEmail(EmailDTO emailDTO) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            String template = carregarTemplateEmail();
            template = template.replace("#{nome}", emailDTO.nome());

            helper.setFrom(remetente);
            helper.setTo(emailDTO.destinatario());
            helper.setSubject(emailDTO.assunto());
            helper.setText(template, true);
            javaMailSender.send(message);

            return "Email enviado";
        } catch (Exception e) {
            return "Erro ao enviar email " + e.getLocalizedMessage();
        }
    }

    public String carregarTemplateEmail() throws IOException {
        ClassPathResource resource = new ClassPathResource("template-email.html");
        return new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
    }
}
