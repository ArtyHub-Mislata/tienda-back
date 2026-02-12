package es.artyhub.tienda_back.spring.filters;

import java.io.IOException;


import es.artyhub.tienda_back.domain.dto.UserDto;
import org.springframework.core.annotation.Order;
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
@Order(1)
public class UserFilter implements Filter {
    
    
    private final SesionService sesionService;

    public UserFilter(SesionService sesionService) {
        this.sesionService = sesionService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;


        String header = req.getHeader("authorization");
        String token = null;

        if (header != null) {
            if (header.startsWith("Bearer ")) {
                token = header.substring(7);
            } else {
                token = header; // viene sin Bearer
            }
        }
        UserDto userDto = sesionService.findUserByToken(token);

        req.setAttribute("USER_DTO", userDto);

        chain.doFilter(request, response);
    }
}
