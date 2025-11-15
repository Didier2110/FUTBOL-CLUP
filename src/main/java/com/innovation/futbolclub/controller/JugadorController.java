package com.innovation.futbolclub.controller;

import com.innovation.futbolclub.model.Jugador;
import com.innovation.futbolclub.model.Equipo;
import com.innovation.futbolclub.repository.JugadorRepository;
import com.innovation.futbolclub.repository.EquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/jugadores")
public class JugadorController {
    
    @Autowired
    private JugadorRepository jugadorRepository;
    
    @Autowired
    private EquipoRepository equipoRepository;
    
    @GetMapping
    public String listarJugadores(Model model) {
        List<Jugador> jugadores = jugadorRepository.findAll();
        model.addAttribute("jugadores", jugadores);
        return "jugadores/listar";
    }
    
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("jugador", new Jugador());
        List<Equipo> equipos = equipoRepository.findAll();
        model.addAttribute("equipos", equipos);
        return "jugadores/formulario";
    }
    
    @PostMapping("/guardar")
    public String guardarJugador(@ModelAttribute Jugador jugador, @RequestParam Long equipoId) {
        Equipo equipo = equipoRepository.findById(equipoId)
            .orElseThrow(() -> new IllegalArgumentException("Equipo no encontrado"));
        jugador.setEquipo(equipo);
        jugadorRepository.save(jugador);
        return "redirect:/jugadores";
    }
    
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Jugador jugador = jugadorRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Jugador no encontrado"));
        model.addAttribute("jugador", jugador);
        List<Equipo> equipos = equipoRepository.findAll();
        model.addAttribute("equipos", equipos);
        return "jugadores/formulario";
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminarJugador(@PathVariable Long id) {
        if (jugadorRepository.existsById(id)) {
            jugadorRepository.deleteById(id);
        }
        return "redirect:/jugadores";
    }
}