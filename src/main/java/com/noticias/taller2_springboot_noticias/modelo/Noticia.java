package com.noticias.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "noticias")
public class Noticia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String categoria;
    private LocalDate fecha;
    private String pais;
    private String departamento;
    private String ciudad;
    private String periodista;
    private String programaEmite;
    private LocalDate fechaEmision;
    private String descripcion;
    private String nivelPublico;

    public Noticia() {
    }

    public Noticia(Integer id, String categoria, LocalDate fecha, String pais, String departamento,
                   String ciudad, String periodista, String programaEmite, LocalDate fechaEmision,
                   String descripcion, String nivelPublico) {
        this.id = id;
        this.categoria = categoria;
        this.fecha = fecha;
        this.pais = pais;
        this.departamento = departamento;
        this.ciudad = ciudad;
        this.periodista = periodista;
        this.programaEmite = programaEmite;
        this.fechaEmision = fechaEmision;
        this.descripcion = descripcion;
        this.nivelPublico = nivelPublico;
    }

    // Getters y Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }

    public String getDepartamento() { return departamento; }
    public void setDepartamento(String departamento) { this.departamento = departamento; }

    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }

    public String getPeriodista() { return periodista; }
    public void setPeriodista(String periodista) { this.periodista = periodista; }

    public String getProgramaEmite() { return programaEmite; }
    public void setProgramaEmite(String programaEmite) { this.programaEmite = programaEmite; }

    public LocalDate getFechaEmision() { return fechaEmision; }
    public void setFechaEmision(LocalDate fechaEmision) { this.fechaEmision = fechaEmision; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getNivelPublico() { return nivelPublico; }
    public void setNivelPublico(String nivelPublico) { this.nivelPublico = nivelPublico; }
}
