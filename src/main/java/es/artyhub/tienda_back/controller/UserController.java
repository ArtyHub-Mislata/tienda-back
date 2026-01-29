package es.artyhub.tienda_back.controller;

import es.artyhub.tienda_back.controller.mapper.UserMapper;
import es.artyhub.tienda_back.controller.webmodel.response.UserSummaryResponse;
import es.artyhub.tienda_back.domain.dto.ArtworkDto;
import es.artyhub.tienda_back.domain.enums.UserRole;
import es.artyhub.tienda_back.domain.model.Page;
import es.artyhub.tienda_back.domain.service.UserService;
import es.artyhub.tienda_back.domain.validation.DtoValidator;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import es.artyhub.tienda_back.domain.service.LoginService;
import es.artyhub.tienda_back.domain.service.SesionService;
import es.artyhub.tienda_back.domain.dto.CredentialsDto;
import es.artyhub.tienda_back.controller.webmodel.request.RegisterRequest;
import es.artyhub.tienda_back.domain.dto.UserDto;
import java.util.Map;
import org.springframework.http.HttpStatus;


@RestController
@RequestMapping("/api/users")
public class UserController {
    
    private final SesionService sesionService;
    private final LoginService loginService;

    private final UserService userService;

    public UserController(SesionService sesionService, LoginService loginService, UserService userService) {
        this.sesionService = sesionService;
        this.loginService = loginService;
        this.userService = userService;
    }

    @GetMapping("/{id}/artworks")
    public ResponseEntity<Page<ArtworkDto>> getArtworksOfUser(@RequestParam(required = false, defaultValue = "1") int page,
                                                              @RequestParam(required = false, defaultValue = "20") int size,
                                                              @PathVariable Long id){
        Page<ArtworkDto> artworkDtoPage = userService.findAllArtworks(id, page, size);

        return new ResponseEntity<>(artworkDtoPage, HttpStatus.OK);
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

    @PostMapping("/register")
    public ResponseEntity<UserSummaryResponse> register(@RequestBody RegisterRequest registerRequest) {
        UserDto newUser = new UserDto();
        newUser.setPassword(registerRequest.getPassword());
        newUser.setAddress(registerRequest.getAddress());
        newUser.setDescription(registerRequest.getDescription());
        newUser.setEmail(registerRequest.getEmail());
        newUser.setName(registerRequest.getName());
        newUser.setImageProfileUrl(registerRequest.getImageProfileUrl());
        newUser.setRole(UserRole.USER);
        DtoValidator.validate(newUser);
        userService.insert(newUser);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/islogged")
    public ResponseEntity<Boolean> isLogged(HttpServletRequest request){
        Boolean isLogged = false;
        UserDto userDto = (UserDto) request.getAttribute("USER_DTO");
        if(userDto != null){
            isLogged = true;
        }
        return new ResponseEntity<>(isLogged, HttpStatus.OK);
    }
    @GetMapping("/logged")
    public ResponseEntity<UserSummaryResponse> getLoggedUser(HttpServletRequest request){
        UserDto userDto = (UserDto) request.getAttribute("USER_DTO");
        UserSummaryResponse userSummaryResponse = UserMapper.getInstance().fromUserDtoToUserSummaryResponse(userDto);
        return new ResponseEntity<>(userSummaryResponse, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserSummaryResponse> getUserById(@PathVariable Long id) {
        UserDto userDto = userService.findById(id);
        if (userDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        UserSummaryResponse userSummaryResponse = UserMapper.getInstance().fromUserDtoToUserSummaryResponse(userDto);
        return new ResponseEntity<>(userSummaryResponse, HttpStatus.OK);
    }
}
