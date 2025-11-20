package com.example.gestiontareas;

import com.example.gestiontareas.edd.Tarea;
import com.example.gestiontareas.edd.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tareas")
public class TareaController {

    @Autowired
    private TareaService servicio;

    @GetMapping("")
    public String verTareas(Model model) {
        model.addAttribute("tareas", servicio.obtenerTareas());
        return "tareas/ver"; // si está dentro de /tareas/
    }

    @GetMapping("/agregar")
    public String agregarForm(Model model) {
        model.addAttribute("tarea", new Tarea());
        return "tareas/agregar";
    }

    @PostMapping("/agregar")
    public String procesar(@ModelAttribute Tarea tarea) {
        servicio.agregarTarea(tarea);
        return "redirect:/tareas";
    }

    @GetMapping("/ordenar")
    public String ordenar(Model model, String tipo) {
        boolean asc = tipo == null || tipo.equals("asc");
        model.addAttribute("ordenadas",
        servicio.ordenarPorPrioridad(asc));
        return "tareas/ordenar";
    }

    @PostMapping("/eliminar")
    public String eliminar(int id) {
        servicio.eliminarTarea(id);
        return "redirect:/tareas";
    }

    @GetMapping("/editar/{index}")
    public String editarForm(@PathVariable int index, Model model) {
        model.addAttribute("tarea", servicio.obtenerPorIndex(index));
        model.addAttribute("index", index);
        return "tareas/editar"; // nueva vista
    }

    @PostMapping("/editar")
    public String guardarEdicion(@ModelAttribute Tarea tarea, int index) {
        servicio.actualizarTarea(index, tarea);
        return "redirect:/tareas";
    }

    @GetMapping("/buscar")
    public String buscarForm() {
        return "tareas/buscar";
    }

    @PostMapping("/buscar")
    public String buscar(String query, Model model) {
        model.addAttribute("resultados", servicio.buscar(query));
        model.addAttribute("query", query);
        return "tareas/buscar";
    }



}
