package br.ufsm.csi.flutter_back.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

@Service
public class AuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String url = request.getRequestURI();
        if (!url.contains("/login") && !url.contains("/criarConta")) {
            try {
                String token = request.getHeader("Authorization");
                if (token == null || !token.startsWith("Bearer ")) {
                    System.out.println("Token nulo ou inválido");
                    HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper(response);
                    wrapper.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token nulo ou inválido");
                    return;
                }
                String username = new JWTUtil().getUsernameToken(token);
                if (username == null && new JWTUtil().isTokenExpirado(token)) {
                    System.out.println("Token expirado");
                    HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper(response);
                    wrapper.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token expirado");
                    return;
                }
                if (SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = new UserDetailsServiceCustom().loadUserByUsername(username);
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (Exception e) {
                HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper(response);
                wrapper.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Erro de autenticação");
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

}
