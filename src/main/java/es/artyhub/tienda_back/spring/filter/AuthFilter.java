package es.artyhub.tienda_back.spring.filter;

import java.io.IOException;


import org.springframework.stereotype.Component;

import es.artyhub.tienda_back.domain.service.SesionService;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthFilter implements Filter {
    
    
    private final SesionService sesionService;

    public AuthFilter(SesionService sesionService) {
        this.sesionService = sesionService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String path = req.getRequestURI();

        if (path.startsWith("/login")) {
            chain.doFilter(request, response);
            return;
        }

        String token = req.getHeader("Authorization");

        if (token == null || token.isEmpty()) {
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        if (!sesionService.isValidToken(token)) {
            res.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        chain.doFilter(request, response);
    }
}
