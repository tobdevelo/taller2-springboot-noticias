package com.noticias.repositorio;

import com.noticias.modelo.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface NoticiaRepository extends JpaRepository<Noticia, Integer> {

    // Reporte 1: Filtrar noticias por categoria
    List<Noticia> findByCategoriaIgnoreCase(String categoria);

    // Reporte 2: Filtrar noticias por ciudad
    List<Noticia> findByCiudadIgnoreCase(String ciudad);

    // Reporte adicional opcional: Filtrar por periodista
    List<Noticia> findByPeriodistaContainingIgnoreCase(String periodista);
}