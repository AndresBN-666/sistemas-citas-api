package com.tareas.gestion_tareas.mapper;

import com.tareas.gestion_tareas.dto.ActualizarTareaDTO;
import com.tareas.gestion_tareas.dto.CrearTareaDTO;
import com.tareas.gestion_tareas.dto.TareaDTO;
import com.tareas.gestion_tareas.entity.Tarea;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TareaMapper {

    TareaDTO toTareaDTO(Tarea tarea);

    Tarea toTarea(CrearTareaDTO crearTareaDTO);

    void actualizarTareaDesdeDTO(ActualizarTareaDTO actualizarTareaDTO,
                                 @MappingTarget Tarea tarea);

}
