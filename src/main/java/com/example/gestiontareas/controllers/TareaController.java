package com.example.gestiontareas.controllers;

import com.example.gestiontareas.model.Tarea;
import com.example.gestiontareas.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tareas")
@CrossOrigin("*") // permite peticiones desde el front
public class TareaController {

    @Autowired
    private TareaService tareaService;

    @GetMapping
    public Object[] obtenerTodas() {
        return tareaService.obtenerTodas();
    }

    @GetMapping("/{id}")
    public Tarea obtenerPorId(@PathVariable int id) {
        return tareaService.buscarPorId(id);
    }

    @PostMapping
    public Tarea crear(@RequestBody Tarea tarea) {
        tareaService.agregarTarea(tarea);
        return tarea;
    }

    @PutMapping("/{id}")
    public boolean editar(@PathVariable int id, @RequestBody Tarea tarea) {
        tarea.setId(id);
        return tareaService.editarTarea(tarea);
    }

    @DeleteMapping("/eliminar/{id}")
    public boolean eliminar(@PathVariable int id) {
        return tareaService.eliminarTarea(id);
    }

    @GetMapping("/buscar")
    public Tarea[] buscar(@RequestParam String query) {
        if (query == null || query.trim().isEmpty()) return new Tarea[0];
        return tareaService.buscarPorTitulo(query);
    }

    @GetMapping("/ordenar")
    public Tarea[] ordenar(@RequestParam String tipo) {

        if (tipo.equalsIgnoreCase("asc")) {
            return tareaService.ordenarAsc();
        }

        return tareaService.ordenarDesc();
    }

    @PutMapping("/completar/{id}")
    public boolean completar(@PathVariable int id) {
        return tareaService.completarTarea(id);
    }

    @PutMapping("/descompletar/{id}")
    public boolean descompletar(@PathVariable int id) {
        return tareaService.descompletarTarea(id);
    }

}
