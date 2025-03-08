package com.tareas.gestion_tareas.controller;

import com.tareas.gestion_tareas.dto.ActualizarTareaDTO;
import com.tareas.gestion_tareas.dto.CrearTareaDTO;
import com.tareas.gestion_tareas.dto.TareaDTO;
import com.tareas.gestion_tareas.entity.Tarea;
import com.tareas.gestion_tareas.repository.TareaRepository;
import com.tareas.gestion_tareas.service.TareaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tarea")
public class TareaController {

    private final TareaService tareaService;

    public TareaController(TareaService tareaService) {
        this.tareaService = tareaService;
    }

    @PostMapping
    public ResponseEntity<TareaDTO> crearTarea(@RequestBody CrearTareaDTO crearTareaDTO){
        return ResponseEntity.ok(tareaService.crearTarea(crearTareaDTO));
    }

    @GetMapping
    public ResponseEntity<List<TareaDTO>> listarTareas(){
        return ResponseEntity.ok(tareaService.listarTareas());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TareaDTO> actualizarTarea(@PathVariable Long id,
                                                    @RequestBody ActualizarTareaDTO actualizarTareaDTO){
        return ResponseEntity.ok(tareaService.actualizarTarea(id,actualizarTareaDTO));
    }

/*
    @GetMapping
    public List<Tarea> getTareas() {
        return tareaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarea>obtenerTareaPorId(@PathVariable Long id){
        Optional<Tarea> tarea = tareaRepository.findById(id);
        return tarea.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Tarea> crearTarea(@Valid @RequestBody Tarea tarea){
        Tarea tareaSave = tareaRepository.save(tarea);
        return ResponseEntity.ok(tareaSave);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarea> actualizarTarea(@PathVariable Long id, @Valid @RequestBody Tarea tareaDetails){
        Optional<Tarea> tareaOptional = tareaRepository.findById(id);
        if(tareaOptional.isPresent()){
            Tarea tarea = tareaOptional.get();
            tarea.setTitulo(tareaDetails.getTitulo());
            tarea.setDescripcion(tareaDetails.getDescripcion());
            tarea.setEstado(tareaDetails.getEstado());
            Tarea updatedTarea = tareaRepository.save(tarea);
            return ResponseEntity.ok(updatedTarea);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/id")
    public ResponseEntity<Void> eliminarTarea(@PathVariable Long id){
        if (tareaRepository.existsById(id)){
            tareaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
*/

}
