package com.tareas.gestion_tareas.repository;

import com.tareas.gestion_tareas.entity.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TareaRepository extends JpaRepository<Tarea, Long> {
}
