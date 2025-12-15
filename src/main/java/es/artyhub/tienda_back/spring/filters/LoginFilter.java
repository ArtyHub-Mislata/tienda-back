package es.artyhub.tienda_back.spring.filters;

import jakarta.servlet.*;

import java.io.IOException;
import java.util.List;

public class LoginFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //TODO Lista de rutas protegidas


        filterChain.doFilter(servletRequest, servletResponse);
    }
}
