package com.tareas.gestion_tareas.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class JWTController {

   /* private final JwtUtil jwtUtil;

    public JWTController() {
        this.jwtUtil =  new JwtUtil();
    }

    // Endpoint para generar un token
    @PostMapping("/generar")
    public String generarToken(@RequestParam String nombreUsuario) {
        return jwtUtil.generarToken(nombreUsuario);
    }

    // Endpoint para validar un token
    @GetMapping("/validar")
    public boolean validarToken(@RequestParam String token) {
        return jwtUtil.esTokenValido(token);
    }


    // Endpoint para obtener el usuario del token
    @GetMapping("/usuario")
    public String obtenerUsuarioDelToken(@RequestParam String token) {
        return jwtUtil.obtenerUsuarioDelToken(token);
    }
*/
}
