package com.tareas.gestion_tareas.controller;

import com.tareas.gestion_tareas.entity.Perfil;
import com.tareas.gestion_tareas.repository.PerfilRespository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/perfil")
public class PerfilController {
    @Autowired
    private PerfilRespository perfilRespository;

    @GetMapping
    public List<Perfil> listarPerfil(){
        return perfilRespository.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Perfil> listarPerfilId(@PathVariable Integer id){
        Optional<Perfil> perfil = perfilRespository.findById(id);
        return perfil.map(ResponseEntity :: ok).orElseGet(()-> ResponseEntity.notFound().build());

    }

    @PostMapping
    public ResponseEntity<Perfil> crearPerfil(@Valid @RequestBody Perfil perfil){
        Perfil grabarPerfil = perfilRespository.save(perfil);
        return ResponseEntity.ok(grabarPerfil);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Perfil> actualizarPerfil(@PathVariable Integer id, @Valid @RequestBody
                                                   Perfil perfil){
        Optional<Perfil> perfilActual = perfilRespository.findById(id);
        if(perfilActual.isPresent()){
            Perfil actPerfil1 = perfilActual.get();
            actPerfil1.setTitulo(perfil.getTitulo());
            actPerfil1.setEstado(perfil.getEstado());
            Perfil updatePerfil = perfilRespository.save(actPerfil1);
            return ResponseEntity.ok(updatePerfil);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Perfil> eliminarPerfil(@PathVariable Integer id){
        if (perfilRespository.existsById(id)){
            perfilRespository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


}
