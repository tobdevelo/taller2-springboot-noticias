package com.noticias.controlador;

import com.noticias.modelo.Noticia;
import com.noticias.servicio.NoticiaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/noticias")
public class NoticiaController {

    @Autowired
    private NoticiaService noticiaService;

    // Listar todas las noticias
    @GetMapping
    public String listarNoticias(HttpSession session, Model model) {
        if (session.getAttribute("usuarioLogueado") == null) {
            return "redirect:/login";
        }
        model.addAttribute("noticias", noticiaService.listarTodas());
        return "noticias/listar";
    }

    // Formulario de creación
    @GetMapping("/nuevo")
    public String formularioCrear(HttpSession session, Model model) {
        if (session.getAttribute("usuarioLogueado") == null) {
            return "redirect:/login";
        }
        model.addAttribute("noticia", new Noticia());
        return "noticias/formulario";
    }

    // Guardar (crear o actualizar)
    @PostMapping("/guardar")
    public String guardarNoticia(@ModelAttribute Noticia noticia, HttpSession session) {
        if (session.getAttribute("usuarioLogueado") == null) {
            return "redirect:/login";
        }
        noticiaService.guardar(noticia);
        return "redirect:/noticias";
    }

    // Formulario de edición
    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable Integer id, HttpSession session, Model model) {
        if (session.getAttribute("usuarioLogueado") == null) {
            return "redirect:/login";
        }
        noticiaService.buscarPorId(id).ifPresent(n -> model.addAttribute("noticia", n));
        return "noticias/formulario";
    }

    // Eliminar noticia
    @GetMapping("/eliminar/{id}")
    public String eliminarNoticia(@PathVariable Integer id, HttpSession session) {
        if (session.getAttribute("usuarioLogueado") == null) {
            return "redirect:/login";
        }
        noticiaService.eliminar(id);
        return "redirect:/noticias";
    }

    // Reporte 1: Filtrado por Categoría
    @GetMapping("/reporte/categoria")
    public String reportePorCategoria(@RequestParam(required = false) String categoria, HttpSession session, Model model) {
        if (session.getAttribute("usuarioLogueado") == null) {
            return "redirect:/login";
        }
        if (categoria != null && !categoria.trim().isEmpty()) {
            model.addAttribute("noticias", noticiaService.filtrarPorCategoria(categoria));
            model.addAttribute("filtroAplicado", categoria);
        } else {
            model.addAttribute("noticias", noticiaService.listarTodas());
        }
        return "noticias/reporte-categoria";
    }

    // Reporte 2: Filtrado por Ciudad
    @GetMapping("/reporte/ciudad")
    public String reportePorCiudad(@RequestParam(required = false) String ciudad, HttpSession session, Model model) {
        if (session.getAttribute("usuarioLogueado") == null) {
            return "redirect:/login";
        }
        if (ciudad != null && !ciudad.trim().isEmpty()) {
            model.addAttribute("noticias", noticiaService.filtrarPorCiudad(ciudad));
            model.addAttribute("filtroAplicado", ciudad);
        } else {
            model.addAttribute("noticias", noticiaService.listarTodas());
        }
        return "noticias/reporte-ciudad";
    }
}
