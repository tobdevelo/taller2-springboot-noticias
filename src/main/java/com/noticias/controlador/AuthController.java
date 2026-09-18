package com.noticias.controlador;

import com.noticias.modelo.Usuario;
import com.noticias.servicio.EmailService;
import com.noticias.servicio.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EmailService emailService;

    @GetMapping("/")
    public String raiz(HttpSession session) {
        if (session.getAttribute("usuarioLogueado") != null) {
            return "redirect:/noticias";
        }
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String procesarLogin(@RequestParam String id,
                                @RequestParam String clave,
                                HttpSession session,
                                Model model) {
        Optional<Usuario> usuario = usuarioService.autenticar(id, clave);
        if (usuario.isPresent()) {
            session.setAttribute("usuarioLogueado", usuario.get());
            return "redirect:/noticias";
        } else {
            model.addAttribute("error", "Credenciales incorrectas");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/recuperar")
    public String mostrarRecuperar() {
        return "recuperar";
    }

    @PostMapping("/recuperar")
    public String procesarRecuperar(@RequestParam String email, Model model) {
        Optional<Usuario> usuario = usuarioService.buscarPorEmail(email);
        if (usuario.isPresent()) {
            try {
                emailService.enviarRecuperacionClave(email, usuario.get().getClave());
                model.addAttribute("mensaje", "Se ha enviado un correo con sus credenciales.");
            } catch (Exception e) {
                model.addAttribute("error", "Error al enviar el correo. Verifique la configuración SMTP.");
            }
        } else {
            model.addAttribute("error", "No existe ningún usuario asociado a ese correo.");
        }
        return "recuperar";
    }
}