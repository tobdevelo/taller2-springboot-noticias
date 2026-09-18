package com.noticias.controlador;

import com.noticias.modelo.Usuario;
import com.noticias.servicio.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Listar todos los usuarios
    @GetMapping
    public String listarUsuarios(HttpSession session, Model model) {
        if (session.getAttribute("usuarioLogueado") == null) {
            return "redirect:/login";
        }
        model.addAttribute("usuarios", usuarioService.listarTodos());
        return "usuarios/listar";
    }

    // Formulario nuevo usuario
    @GetMapping("/nuevo")
    public String formularioCrear(HttpSession session, Model model) {
        if (session.getAttribute("usuarioLogueado") == null) {
            return "redirect:/login";
        }
        model.addAttribute("usuario", new Usuario());
        return "usuarios/formulario";
    }

    // Guardar usuario
    @PostMapping("/guardar")
    public String guardarUsuario(@ModelAttribute Usuario usuario, HttpSession session) {
        if (session.getAttribute("usuarioLogueado") == null) {
            return "redirect:/login";
        }
        usuarioService.guardar(usuario);
        return "redirect:/usuarios";
    }

    // Formulario editar usuario
    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable String id, HttpSession session, Model model) {
        if (session.getAttribute("usuarioLogueado") == null) {
            return "redirect:/login";
        }
        usuarioService.buscarPorId(id).ifPresent(u -> model.addAttribute("usuario", u));
        return "usuarios/formulario";
    }

    // Eliminar usuario
    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable String id, HttpSession session) {
        if (session.getAttribute("usuarioLogueado") == null) {
            return "redirect:/login";
        }
        usuarioService.eliminar(id);
        return "redirect:/usuarios";
    }

    // Reporte 1: Filtrar por Rol
    @GetMapping("/reporte/rol")
    public String reportePorRol(@RequestParam(required = false) String rol, HttpSession session, Model model) {
        if (session.getAttribute("usuarioLogueado") == null) {
            return "redirect:/login";
        }
        if (rol != null && !rol.trim().isEmpty()) {
            model.addAttribute("usuarios", usuarioService.filtrarPorRol(rol));
            model.addAttribute("filtroAplicado", rol);
        } else {
            model.addAttribute("usuarios", usuarioService.listarTodos());
        }
        return "usuarios/reporte-rol";
    }

    // Reporte 2: Buscar por Coincidencia en Nombre
    @GetMapping("/reporte/nombre")
    public String reportePorNombre(@RequestParam(required = false) String nombre, HttpSession session, Model model) {
        if (session.getAttribute("usuarioLogueado") == null) {
            return "redirect:/login";
        }
        if (nombre != null && !nombre.trim().isEmpty()) {
            model.addAttribute("usuarios", usuarioService.filtrarPorNombre(nombre));
            model.addAttribute("filtroAplicado", nombre);
        } else {
            model.addAttribute("usuarios", usuarioService.listarTodos());
        }
        return "usuarios/reporte-nombre";
    }
}