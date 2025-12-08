package com.example.gestiontareas.repository;

import com.example.gestiontareas.model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Integer> {
    //Buscar tarea por título (opcional)
    Optional<Tarea> findByTitulo(String titulo);
}