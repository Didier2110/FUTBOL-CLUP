package com.innovation.futbolclub.controller;

import com.innovation.futbolclub.model.Equipo;
import com.innovation.futbolclub.model.Club;
import com.innovation.futbolclub.repository.EquipoRepository;
import com.innovation.futbolclub.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/equipos")
public class EquipoController {
    
    @Autowired
    private EquipoRepository equipoRepository;
    
    @Autowired
    private ClubRepository clubRepository;
    
    @GetMapping
    public String listarEquipos(Model model) {
        List<Equipo> equipos = equipoRepository.findAll();
        model.addAttribute("equipos", equipos);
        return "equipos/listar";
    }
    
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("equipo", new Equipo());
        List<Club> clubes = clubRepository.findAll();
        model.addAttribute("clubes", clubes);
        return "equipos/formulario";
    }
    
    @PostMapping("/guardar")
    public String guardarEquipo(@ModelAttribute Equipo equipo, @RequestParam Long clubId) {
        Club club = clubRepository.findById(clubId)
            .orElseThrow(() -> new IllegalArgumentException("Club no encontrado"));
        equipo.setClub(club);
        equipoRepository.save(equipo);
        return "redirect:/equipos";
    }
    
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Equipo equipo = equipoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Equipo no encontrado"));
        model.addAttribute("equipo", equipo);
        List<Club> clubes = clubRepository.findAll();
        model.addAttribute("clubes", clubes);
        return "equipos/formulario";
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminarEquipo(@PathVariable Long id) {
        if (equipoRepository.existsById(id)) {
            equipoRepository.deleteById(id);
        }
        return "redirect:/equipos";
    }
}