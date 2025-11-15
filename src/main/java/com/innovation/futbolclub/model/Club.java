package com.innovation.futbolclub.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "club")
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 100)
    private String nombre;
    
    @Column(nullable = false, length = 100)
    private String ciudad;
    
    @Column(nullable = false)
    private Integer fundacion;
    
    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Equipo> equipos = new ArrayList<>();
    
    public Club() {}
    
    public Club(String nombre, String ciudad, Integer fundacion) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.fundacion = fundacion;
    }
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }
    
    public Integer getFundacion() { return fundacion; }
    public void setFundacion(Integer fundacion) { this.fundacion = fundacion; }
    
    public List<Equipo> getEquipos() { return equipos; }
    public void setEquipos(List<Equipo> equipos) { this.equipos = equipos; }
}