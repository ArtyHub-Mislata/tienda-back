package es.artyhub.tienda_back.controller;

import es.artyhub.tienda_back.controller.mapper.UserMapper;
import es.artyhub.tienda_back.controller.webmodel.response.UserSummaryResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;

import es.artyhub.tienda_back.domain.service.LoginService;
import es.artyhub.tienda_back.domain.service.SesionService;
import es.artyhub.tienda_back.domain.service.UserService;
import es.artyhub.tienda_back.domain.dto.CredentialsDto;
import es.artyhub.tienda_back.domain.dto.UserDto;

import java.util.Map;
import org.springframework.http.HttpStatus;


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
    @GetMapping("/islogged")
    public ResponseEntity<UserSummaryResponse> isLogged(HttpServletRequest request){
        UserDto userDto = (UserDto) request.getAttribute("USER_DTO");
        UserSummaryResponse userSummaryResponse = UserMapper.getInstance().fromUserDtoToUserSummaryResponse(userDto);
        return new ResponseEntity<>(userSummaryResponse, HttpStatus.OK);
    }

}
