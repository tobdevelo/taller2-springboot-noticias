package com.noticias.servicio;

import com.noticias.modelo.Usuario;
import com.noticias.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarPorId(String id) {
        return usuarioRepository.findById(id);
    }

    public Usuario guardar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void eliminar(String id) {
        usuarioRepository.deleteById(id);
    }

    public Optional<Usuario> autenticar(String id, String clave) {
        return usuarioRepository.findById(id)
                .filter(u -> u.getClave().equals(clave));
    }

    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public List<Usuario> filtrarPorRol(String rol) {
        return usuarioRepository.findByRol(rol);
    }

    public List<Usuario> filtrarPorNombre(String texto) {
        return usuarioRepository.findByNombreContainingIgnoreCase(texto);
    }
}
