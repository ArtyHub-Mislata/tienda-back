package es.artyhub.tienda_back.controller;

import es.artyhub.tienda_back.domain.dto.ArtworkDto;
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
    public ResponseEntity<Void> logout(@RequestParam String token) {
        sesionService.deleteSesion(token);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<Page<UserDto>> getAllUsers(@RequestParam(required = false, defaultValue = "1") int page,
                                                                   @RequestParam(required = false, defaultValue = "20") int size) {
        Page<UserDto> userDtoPage = userService.findAll(page, size);

        return new ResponseEntity<>(userDtoPage, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto userDto = userService.findById(id);
        if (userDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping("/{id}/artworks")
    public ResponseEntity<Page<ArtworkDto>> getArtworksOfUser(@RequestParam(required = false, defaultValue = "1") int page,
                                                              @RequestParam(required = false, defaultValue = "20") int size,
                                                              @PathVariable Long id){
        Page<ArtworkDto> artworkDtoPage = userService.findAllArtworks(id, page, size);

        return new ResponseEntity<>(artworkDtoPage, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        try {
            DtoValidator.validate(userDto);
            UserDto createUserDto = userService.insert(userDto);
            return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
        } catch (ValidationException e) {
            //LLEGAA AQUI
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long id, @RequestBody UserDto userDto) {
        try {
            if (!id.equals(userDto.getId())) {
                throw new ValidationException("ID in path and request body must match");
            }
            DtoValidator.validate(userDto);
            UserDto updateUserDto = userService.update(userDto);
            return new ResponseEntity<>(updateUserDto, HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
