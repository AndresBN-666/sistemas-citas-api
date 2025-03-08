package com.tareas.gestion_tareas.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TareaDTO {
    private Long id;
    private String titulo;
    private String descripcion;
    private Boolean estado;

    public TareaDTO(Long id, String titulo, String descripcion, Boolean estado) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estado = estado;
    }
}
