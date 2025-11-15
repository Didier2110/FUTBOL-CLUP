package com.innovation.futbolclub.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "partido")
public class Partido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private LocalDateTime fecha;
    
    @Column(name = "goles_local", nullable = false)
    private Integer golesLocal = 0;
    
    @Column(name = "goles_visitante", nullable = false)
    private Integer golesVisitante = 0;
    
    @Column(length = 100)
    private String estadio;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "local_id")
    private Equipo local;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "visitante_id")
    private Equipo visitante;
    
    // Constructores
    public Partido() {}
    
    public Partido(LocalDateTime fecha, Integer golesLocal, Integer golesVisitante, String estadio) {
        this.fecha = fecha;
        this.golesLocal = golesLocal;
        this.golesVisitante = golesVisitante;
        this.estadio = estadio;
    }
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
    
    public Integer getGolesLocal() { return golesLocal; }
    public void setGolesLocal(Integer golesLocal) { this.golesLocal = golesLocal; }
    
    public Integer getGolesVisitante() { return golesVisitante; }
    public void setGolesVisitante(Integer golesVisitante) { this.golesVisitante = golesVisitante; }
    
    public String getEstadio() { return estadio; }
    public void setEstadio(String estadio) { this.estadio = estadio; }
    
    public Equipo getLocal() { return local; }
    public void setLocal(Equipo local) { this.local = local; }
    
    public Equipo getVisitante() { return visitante; }
    public void setVisitante(Equipo visitante) { this.visitante = visitante; }
    
    @Override
    public String toString() {
        return "Partido{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", golesLocal=" + golesLocal +
                ", golesVisitante=" + golesVisitante +
                ", estadio='" + estadio + '\'' +
                '}';
    }
}