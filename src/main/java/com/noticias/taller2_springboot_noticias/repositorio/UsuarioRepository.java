package com.noticias.repositorio;

import com.noticias.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    // Buscar por correo para la recuperacion de clave
    Optional<Usuario> findByEmail(String email);

    // Reporte 1: Buscar usuarios por rol
    List<Usuario> findByRol(String rol);

    // Reporte 2: Buscar usuarios cuyo nombre contenga cierto texto
    List<Usuario> findByNombreContainingIgnoreCase(String texto);
}