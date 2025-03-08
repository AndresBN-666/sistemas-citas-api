package com.tareas.gestion_tareas.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActualizarTareaDTO {

    private String titulo;
    private String descripcion;
    private Boolean estado;

    public ActualizarTareaDTO(String titulo, String descripcion, Boolean estado) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estado = estado;
    }
}
