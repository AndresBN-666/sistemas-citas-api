package com.tareas.gestion_tareas.service;

import com.tareas.gestion_tareas.dto.ActualizarTareaDTO;
import com.tareas.gestion_tareas.dto.CrearTareaDTO;
import com.tareas.gestion_tareas.dto.TareaDTO;
import com.tareas.gestion_tareas.entity.Tarea;
import com.tareas.gestion_tareas.mapper.TareaMapper;
import com.tareas.gestion_tareas.repository.TareaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TareaService {

    private final TareaRepository tareaRepository;
    private final TareaMapper tareaMapper;


    public TareaService(TareaRepository tareaRepository, TareaMapper tareaMapper) {
        this.tareaRepository = tareaRepository;
        this.tareaMapper = tareaMapper;
    }

    public TareaDTO crearTarea(CrearTareaDTO crearTareaDTO){
        Tarea tarea = tareaMapper.toTarea(crearTareaDTO);
        tarea.setEstado(false);
        Tarea tareaSaved = tareaRepository.save(tarea);
        return tareaMapper.toTareaDTO(tareaSaved);
    }

    public List<TareaDTO> listarTareas(){
        return tareaRepository.findAll()
                .stream()
                .map(tareaMapper::toTareaDTO)
                .collect(Collectors.toList());
    }

    public TareaDTO actualizarTarea(Long id, ActualizarTareaDTO actualizarTareaDTO){
        Tarea tarea = tareaRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Tarea no encontrada"));
        tareaMapper.actualizarTareaDesdeDTO(actualizarTareaDTO, tarea);
        Tarea tareaActualizado = tareaRepository.save(tarea);
        return tareaMapper.toTareaDTO(tareaActualizado);
    }

}
