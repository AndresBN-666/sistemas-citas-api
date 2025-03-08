package com.tareas.gestion_tareas.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty
    @Size(max = 50, message = "Insertar Titulo")
    private String titulo;

    private Boolean estado;

    public Perfil(Integer id, String titulo, Boolean estado) {
        this.id = id;
        this.titulo = titulo;
        this.estado = estado;
    }
}
