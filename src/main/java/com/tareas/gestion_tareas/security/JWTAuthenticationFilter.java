package com.tareas.gestion_tareas.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;


public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

/*    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String token = getJWTFromRequest(httpRequest);

        if (token != null && jwtUtil.esTokenValido(token)) {
            String username = jwtUtil.obtenerUsuarioDelToken(token);
            Authentication authentication = jwtUtil.getAuthentication(username);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // Aquí es donde debería ir el paso al siguiente filtro
        chain.doFilter(request, response);  // Pasa al siguiente filtro de la cadena
    }*/

    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);  // Extraemos el token del encabezado
        }
        return null;  // Si no hay token, retornamos null
    }

/*
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws java.io.IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String token = getJWTFromRequest(httpRequest);

        if (token != null && jwtUtil.esTokenValido(token)) {
            String username = jwtUtil.obtenerUsuarioDelToken(token);
            Authentication authentication = jwtUtil.getAuthentication(username);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // Aquí es donde debería ir el paso al siguiente filtro
        filterChain.doFilter(servletRequest, servletResponse);
    }
*/

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getTokenFromRequest(request);

        if (token != null && jwtUtil.esTokenValido(token)) {
            String username = jwtUtil.obtenerUsuarioDelToken(token);
            Authentication authentication = getAuthentication(username);

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }
    private Authentication getAuthentication(String username) {
        return new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
    }
}
