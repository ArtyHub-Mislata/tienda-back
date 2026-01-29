package es.artyhub.tienda_back.controller;

import es.artyhub.tienda_back.controller.mapper.UserMapper;
import es.artyhub.tienda_back.controller.webmodel.response.UserSummaryResponse;
import es.artyhub.tienda_back.domain.dto.ArtworkDto;
import es.artyhub.tienda_back.domain.dto.UserDto;
import es.artyhub.tienda_back.domain.exception.ValidationException;
import es.artyhub.tienda_back.domain.model.Page;
import es.artyhub.tienda_back.domain.service.UserService;
import es.artyhub.tienda_back.domain.validation.DtoValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class UserAdminController {

    private final UserService userService;

    public UserAdminController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/users")
    public ResponseEntity<Page<UserSummaryResponse>> getAllUsers(@RequestParam(required = false, defaultValue = "1") int page,
                                                     @RequestParam(required = false, defaultValue = "20") int size) {
        Page<UserDto> userDtoPage = userService.findAll(page, size);
        List<UserSummaryResponse> list = userDtoPage
                .data()
                .stream()
                .map(UserMapper.getInstance()::fromUserDtoToUserSummaryResponse)
                .toList();
        Page<UserSummaryResponse> userSummaryResponsePage = new Page<UserSummaryResponse>(
                list,
                userDtoPage.pageNumber(),
                userDtoPage.pageSize(),
                userDtoPage.totalElements()
        );
        return new ResponseEntity<>(userSummaryResponsePage, HttpStatus.OK);
    }


    @PutMapping("/users/{id}")
    public ResponseEntity<UserSummaryResponse> updateUser(@PathVariable("id") Long id, @RequestBody UserDto userDto) {
        try {
            if (!id.equals(userDto.getId())) {
                throw new ValidationException("ID in path and request body must match");
            }
            UserDto userDtoWithDetails = userService.findById(id);
            userDto.setPassword(userDtoWithDetails.getPassword());

            DtoValidator.validate(userDto);
            UserDto updateUserDto = userService.update(userDto);

            return new ResponseEntity<>(UserMapper.getInstance().fromUserDtoToUserSummaryResponse(updateUserDto), HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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
