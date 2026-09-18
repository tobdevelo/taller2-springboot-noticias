package com.noticias.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired(required = false)
    private JavaMailSender mailSender;

    public void enviarRecuperacionClave(String destino, String clave) {
        if (mailSender != null) {
            SimpleMailMessage mensaje = new SimpleMailMessage();
            mensaje.setTo(destino);
            mensaje.setSubject("Recuperación de Clave - Sistema de Noticias");
            mensaje.setText("Estimado usuario, su clave de acceso registrada es: " + clave);
            mailSender.send(mensaje);
        } else {
            System.out.println("Simulación de correo a " + destino + ". Clave: " + clave);
        }
    }
}