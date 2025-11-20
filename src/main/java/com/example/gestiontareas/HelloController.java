package com.example.gestiontareas;

import com.example.gestiontareas.edd.TareaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    private final TareaService tareaService;

    public HelloController(TareaService tareaService) {
        this.tareaService = tareaService;
    }

    @GetMapping("/")
    public String inicio(Model model) {
        model.addAttribute("lista", tareaService.obtenerTareas());
        return "tareas/ver";
    }

}


