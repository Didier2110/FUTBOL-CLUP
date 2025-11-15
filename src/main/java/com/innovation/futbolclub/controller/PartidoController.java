package com.innovation.futbolclub.controller;

import com.innovation.futbolclub.model.Partido;
import com.innovation.futbolclub.model.Equipo;
import com.innovation.futbolclub.repository.PartidoRepository;
import com.innovation.futbolclub.repository.EquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/partidos")
public class PartidoController {
    
    @Autowired
    private PartidoRepository partidoRepository;
    
    @Autowired
    private EquipoRepository equipoRepository;
    
    @GetMapping
    public String listarPartidos(Model model) {
        List<Partido> partidos = partidoRepository.findAll();
        model.addAttribute("partidos", partidos);
        return "partidos/listar";
    }
    
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("partido", new Partido());
        List<Equipo> equipos = equipoRepository.findAll();
        model.addAttribute("equipos", equipos);
        return "partidos/formulario";
    }
    
    @PostMapping("/guardar")
    public String guardarPartido(@ModelAttribute Partido partido, 
                               @RequestParam Long localId,
                               @RequestParam Long visitanteId,
                               @RequestParam String fecha) {
        
        // Validar que no sea el mismo equipo
        if (localId.equals(visitanteId)) {
            throw new IllegalArgumentException("El equipo local y visitante no pueden ser el mismo");
        }
        
        Equipo local = equipoRepository.findById(localId)
            .orElseThrow(() -> new IllegalArgumentException("Equipo local no encontrado"));
        Equipo visitante = equipoRepository.findById(visitanteId)
            .orElseThrow(() -> new IllegalArgumentException("Equipo visitante no encontrado"));
        
        partido.setLocal(local);
        partido.setVisitante(visitante);
        
        // Convertir String fecha a LocalDateTime
        LocalDateTime fechaHora = LocalDateTime.parse(fecha);
        partido.setFecha(fechaHora);
        
        partidoRepository.save(partido);
        return "redirect:/partidos";
    }
    
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Partido partido = partidoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Partido no encontrado con id: " + id));
        
        model.addAttribute("partido", partido);
        List<Equipo> equipos = equipoRepository.findAll();
        model.addAttribute("equipos", equipos);
        
        return "partidos/formulario";
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminarPartido(@PathVariable Long id) {
        if (partidoRepository.existsById(id)) {
            partidoRepository.deleteById(id);
        }
        return "redirect:/partidos";
    }
}