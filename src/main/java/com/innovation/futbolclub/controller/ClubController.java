package com.innovation.futbolclub.controller;

import com.innovation.futbolclub.model.Club;
import com.innovation.futbolclub.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clubes")
public class ClubController {
    
    @Autowired
    private ClubRepository clubRepository;
    
    @GetMapping
    public String listarClubes(Model model) {
        List<Club> clubes = clubRepository.findAll();
        model.addAttribute("clubes", clubes);
        return "clubes/listar";
    }
    
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("club", new Club());
        return "clubes/formulario";
    }
    
    @PostMapping("/guardar")
    public String guardarClub(@ModelAttribute Club club) {
        clubRepository.save(club);
        return "redirect:/clubes";
    }
    
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Club club = clubRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Club no encontrado con id: " + id));
        model.addAttribute("club", club);
        return "clubes/formulario";
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminarClub(@PathVariable Long id) {
        if (clubRepository.existsById(id)) {
            clubRepository.deleteById(id);
        }
        return "redirect:/clubes";
    }
}