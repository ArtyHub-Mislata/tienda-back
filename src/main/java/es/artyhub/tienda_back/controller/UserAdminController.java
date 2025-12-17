package es.artyhub.tienda_back.controller;

import es.artyhub.tienda_back.domain.dto.ArtworkDto;
import es.artyhub.tienda_back.domain.dto.UserDto;
import es.artyhub.tienda_back.domain.exception.ValidationException;
import es.artyhub.tienda_back.domain.model.Page;
import es.artyhub.tienda_back.domain.service.LoginService;
import es.artyhub.tienda_back.domain.service.SesionService;
import es.artyhub.tienda_back.domain.service.UserService;
import es.artyhub.tienda_back.domain.validation.DtoValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class UserAdminController {

    private final UserService userService;
    private final SesionService sesionService;
    private final LoginService loginService;

    public UserAdminController(UserService userService, SesionService sesionService, LoginService loginService) {
        this.userService = userService;
        this.sesionService = sesionService;
        this.loginService = loginService;
    }
    @GetMapping("/users")
    public ResponseEntity<Page<UserDto>> getAllUsers(@RequestParam(required = false, defaultValue = "1") int page,
                                                     @RequestParam(required = false, defaultValue = "20") int size) {
        Page<UserDto> userDtoPage = userService.findAll(page, size);

        return new ResponseEntity<>(userDtoPage, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto userDto = userService.findById(id);
        if (userDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
    @PutMapping("/users/{id}")
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

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/users/{id}/artworks")
    public ResponseEntity<Page<ArtworkDto>> getArtworksOfUser(@RequestParam(required = false, defaultValue = "1") int page,
                                                              @RequestParam(required = false, defaultValue = "20") int size,
                                                              @PathVariable Long id){
        Page<ArtworkDto> artworkDtoPage = userService.findAllArtworks(id, page, size);

        return new ResponseEntity<>(artworkDtoPage, HttpStatus.OK);
    }

    @PostMapping("/users")
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
}
