package com.tareas.gestion_tareas.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.security.core.GrantedAuthority;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    private final UserDetailsService userDetailsService;
    private final Key secretKey;

    public JwtUtil(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
        this.secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256); // Clave secreta (en producción, cárgalo desde configuración)
    }

    public String generarToken(String nombreUsuario, List<String> roles) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(nombreUsuario);
        roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        long tiempoExpiracion = 1000 * 60 * 60; // 1 hora
        Date fechaExpiracion = new Date(System.currentTimeMillis() + tiempoExpiracion);

        return Jwts.builder()
                .setSubject(nombreUsuario)
                .claim("roles", roles) // Incluye los roles en el token
                .setIssuedAt(new Date())
                .setExpiration(fechaExpiracion)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }
    // Obtener el usuario del token
    public String obtenerUsuarioDelToken(String token) {
        return Jwts.parser()  // Usamos parserBuilder() en lugar de parser()
                .setSigningKey(secretKey)  // Establecemos la clave secreta
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Validar si el token es correcto
    public boolean esTokenValido(String token) {
        try {
            Jwts.parser()  // Usamos parserBuilder() en lugar de parser()
                    .setSigningKey(secretKey)  // Establecemos la clave secreta
                    .build()
                    .parseClaimsJws(token);  // Verificamos la validez del token
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Authentication getAuthentication(String username) {
        // Lógica para obtener la autenticación (puedes usar un UserDetailsService)
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        // Si no estás usando un servicio de usuarios, puedes crear un objeto de autenticación simple.
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
