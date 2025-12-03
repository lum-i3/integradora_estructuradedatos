package com.example.gestiontareas.controllers;

import com.example.gestiontareas.service.TareaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/")
    public String redirectToVer() {
        return "redirect:/tareas/ver.html";
    }
}



