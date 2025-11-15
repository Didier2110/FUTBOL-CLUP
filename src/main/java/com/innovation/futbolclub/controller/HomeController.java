package com.innovation.futbolclub.controller;

import com.innovation.futbolclub.repository.ClubRepository;
import com.innovation.futbolclub.repository.EquipoRepository;
import com.innovation.futbolclub.repository.JugadorRepository;
import com.innovation.futbolclub.repository.PartidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    @Autowired
    private ClubRepository clubRepository;
    
    @Autowired
    private EquipoRepository equipoRepository;
    
    @Autowired
    private JugadorRepository jugadorRepository;
    
    @Autowired
    private PartidoRepository partidoRepository;
    
    @GetMapping("/")
    public String home(Model model) {
        long totalClubes = clubRepository.count();
        long totalEquipos = equipoRepository.count();
        long totalJugadores = jugadorRepository.count();
        long totalPartidos = partidoRepository.count();
        
        model.addAttribute("totalClubes", totalClubes);
        model.addAttribute("totalEquipos", totalEquipos);
        model.addAttribute("totalJugadores", totalJugadores);
        model.addAttribute("totalPartidos", totalPartidos);
        
        return "index";
    }
}