package es.artyhub.tienda_back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import es.artyhub.tienda_back.domain.service.UserService;
import es.artyhub.tienda_back.domain.dto.UserDto;
import es.artyhub.tienda_back.controller.webmodel.response.UserDetailResponse;
import es.artyhub.tienda_back.domain.model.Page;
import java.util.List;
import org.springframework.http.HttpStatus;
import es.artyhub.tienda_back.controller.mapper.UserMapper;
import es.artyhub.tienda_back.domain.validation.DtoValidator;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Page<UserDetailResponse>> getAllUsers(@RequestParam(required = false, defaultValue = "1") int page,
                                                                   @RequestParam(required = false, defaultValue = "20") int size) {
        Page<UserDto> userDtoPage = userService.findAll(page, size);

        List<UserDetailResponse> userDetailResponses = userDtoPage.data().stream()
            .map(UserMapper::fromUserDtoToUserDetailResponse)
            .toList();

        Page<UserDetailResponse> userDetailPage = new Page<>(
            userDetailResponses,
            userDtoPage.pageNumber(),
            userDtoPage.pageSize(),
            userDtoPage.totalElements()
        );    
        return new ResponseEntity<>(userDetailPage, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDetailResponse> getUserById(@PathVariable Long id) {
        return userService.findById(id)
            .map(UserMapper::fromUserDtoToUserDetailResponse)
            .map(userDetailResponse -> new ResponseEntity<>(userDetailResponse, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<UserDetailResponse> createUser(@RequestBody UserDto userDto) {
        try {
            DtoValidator.validate(userDto);
            UserDto createUserDto = userService.insert(userDto);
            UserDetailResponse userDetailResponse = UserMapper.fromUserDtoToUserDetailResponse(createUserDto);
            return new ResponseEntity<>(userDetailResponse, HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping()
    public ResponseEntity<UserDetailResponse> updateUser(@RequestBody UserDto userDto) {
        try {
            DtoValidator.validate(userDto);
            UserDto updateUserDto = userService.update(userDto);
            UserDetailResponse userDetailResponse = UserMapper.fromUserDtoToUserDetailResponse(updateUserDto);
            return new ResponseEntity<>(userDetailResponse, HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
