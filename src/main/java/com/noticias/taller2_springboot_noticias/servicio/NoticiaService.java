package com.noticias.servicio;

import com.noticias.modelo.Noticia;
import com.noticias.repositorio.NoticiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class NoticiaService {

    @Autowired
    private NoticiaRepository noticiaRepository;

    public List<Noticia> listarTodas() {
        return noticiaRepository.findAll();
    }

    public Optional<Noticia> buscarPorId(Integer id) {
        return noticiaRepository.findById(id);
    }

    public Noticia guardar(Noticia noticia) {
        return noticiaRepository.save(noticia);
    }

    public void eliminar(Integer id) {
        noticiaRepository.deleteById(id);
    }

    public List<Noticia> filtrarPorCategoria(String categoria) {
        return noticiaRepository.findByCategoriaIgnoreCase(categoria);
    }

    public List<Noticia> filtrarPorCiudad(String ciudad) {
        return noticiaRepository.findByCiudadIgnoreCase(ciudad);
    }
}