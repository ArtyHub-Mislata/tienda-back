package es.artyhub.tienda_back.spring.filters;

import es.artyhub.tienda_back.domain.dto.UserDto;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@Order(3)
public class LoginFilter implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        List<String> loginProtectedUrls = List.of(
                "/api/users/logout"
        );

        UserDto userDto =(UserDto) req.getAttribute("USER_DTO");
        String path = req.getRequestURI();

        if(loginProtectedUrls.contains(path) && userDto == null){
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        filterChain.doFilter(request, response);
    }
}
