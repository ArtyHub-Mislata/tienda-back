package es.artyhub.tienda_back.controller;

import es.artyhub.tienda_back.domain.dto.ArtworkDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;

import es.artyhub.tienda_back.domain.service.LoginService;
import es.artyhub.tienda_back.domain.service.SesionService;
import es.artyhub.tienda_back.domain.service.UserService;
import es.artyhub.tienda_back.domain.dto.CredentialsDto;
import es.artyhub.tienda_back.domain.dto.UserDto;
import es.artyhub.tienda_back.domain.exception.ValidationException;
import es.artyhub.tienda_back.domain.model.Page;
import java.util.Map;
import org.springframework.http.HttpStatus;
import es.artyhub.tienda_back.domain.validation.DtoValidator;


@RestController
@RequestMapping("/api/users")
public class UserController {
    
    private final UserService userService;
    private final SesionService sesionService;
    private final LoginService loginService;

    public UserController(UserService userService, SesionService sesionService, LoginService loginService) {
        this.userService = userService;
        this.sesionService = sesionService;
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody CredentialsDto credentialsDto) {
        System.out.println("LLEGA AL CONTROLLER");
        String token = loginService.login(credentialsDto);
        return ResponseEntity.ok(Map.of("token", token));
    }

    @DeleteMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        String token = request.getHeader("authorization").substring(7);
        sesionService.deleteSesion(token);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
