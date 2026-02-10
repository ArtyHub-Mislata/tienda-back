package es.artyhub.tienda_back.controller;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.artyhub.tienda_back.domain.dto.UserDto;
import es.artyhub.tienda_back.domain.enums.UserRole;
import es.artyhub.tienda_back.domain.exception.ValidationException;
import es.artyhub.tienda_back.domain.model.Page;
import es.artyhub.tienda_back.domain.service.SesionService;
import es.artyhub.tienda_back.domain.service.UserService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@WebMvcTest(UserAdminController.class)
@AutoConfigureMockMvc(addFilters = false)
public class UserAdminControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @MockitoBean
    private SesionService sessionService;

    @Autowired
    private ObjectMapper objectMapper;

    @Nested
    @DisplayName("getAllUsers")
    public class GetAllUsers {
        
        @Test
        @DisplayName("should get all users if users are valid")
        public void shouldGetAllUsers_IfUsersAreValid() throws Exception {
            UserDto userDto = new UserDto(
                1L,
                "User",
                "Email",
                "Password",
                "Description",
                "Address",
                "Image",
                UserRole.ADMIN
            );

            List<UserDto> users = List.of(userDto);

            Page<UserDto> userDtoPage = new Page<>(
                users,
                1,
                10,
                1L,
                1
            );
            
            when(userService.findAll(1, 10)).thenReturn(userDtoPage);
            
            mockMvc.perform(get("/api/admin/users")
                .param("page", "1")
                .param("size", "10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].id").value(userDto.getId()))
                .andExpect(jsonPath("$.data[0].name").value(userDto.getName()))
                .andExpect(jsonPath("$.data[0].email").value(userDto.getEmail()))
                .andExpect(jsonPath("$.data[0].description").value(userDto.getDescription()))
                .andExpect(jsonPath("$.data[0].address").value(userDto.getAddress()))
                .andExpect(jsonPath("$.data[0].imageProfileUrl").value(userDto.getImageProfileUrl()));
        }

        @Test
        @DisplayName("should return not found if users is empty")
        public void shouldReturnNotFound_IfUsersIsEmpty() throws Exception {
            Page<UserDto> userDtoPage = new Page<>(
                List.of(),
                1,
                10,
                0L,
                0
            );

            when(userService.findAll(anyInt(), anyInt())).thenReturn(userDtoPage);
            
            mockMvc.perform(get("/api/admin/users")
                .param("pageNumber", "1")
                .param("pageSize", "10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
        }
    }

    @Nested
    @DisplayName("createUser")
    public class CreateUser {
        
        @Test
        @DisplayName("should create user if user is valid")
        public void shouldCreateUser_IfUserIsValid() throws Exception {
            UserDto userDto = new UserDto(
                1L,
                "User",
                "Email",
                "Password",
                "Description",
                "Address",
                "Image",
                UserRole.ADMIN
            );
            
            when(userService.insert(any(UserDto.class))).thenReturn(userDto);
            
            mockMvc.perform(post("/api/admin/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(userDto.getId()));
        }

        @Test
        @DisplayName("should return validation exception if user is not valid")
        public void shouldReturnValidationException_IfUserIsNotValid() throws Exception {
            UserDto userDto = new UserDto(
                1L,
                "",
                "",
                "",
                "",
                "",
                "",
                UserRole.ADMIN
            );

            when(userService.insert(userDto)).thenThrow(new ValidationException("User is not valid"));
            
            mockMvc.perform(post("/api/admin/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isBadRequest());
        }
    }

    @Nested
    @DisplayName("updateUser")
    public class UpdateUser {
        
        @Test
        @DisplayName("should update user if user is valid")
        public void shouldUpdateUser_IfUserIsValid() throws Exception {
            Long id = 1L;
            UserDto existingUserDto = new UserDto(
                id,
                "User",
                "Email",
                "Password",
                "Description",
                "Address",
                "Image",
                UserRole.ADMIN
            );
            
            UserDto newUserDto = new UserDto(
                id,
                "New User",
                "New Email",
                "New Password",
                "New Description",
                "New Address",
                "New Image",
                UserRole.ADMIN
            );
            
            when(userService.findById(id)).thenReturn(existingUserDto);
            when(userService.update(any(UserDto.class))).thenReturn(newUserDto);
            
            mockMvc.perform(put("/api/admin/users/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newUserDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(newUserDto.getId()))
                .andExpect(jsonPath("$.id").value(existingUserDto.getId()))
                .andExpect(jsonPath("$.name").value(newUserDto.getName()))
                .andExpect(jsonPath("$.description").value(newUserDto.getDescription()))
                .andExpect(jsonPath("$.address").value(newUserDto.getAddress()))
                .andExpect(jsonPath("$.email").value(newUserDto.getEmail()));
        }

        @Test
        @DisplayName("should return validation exception if user is not valid")
        public void shouldReturnValidationException_IfUserIsNotValid() throws Exception {
            Long id = 1L;
            UserDto userDto = new UserDto(
                1L,
                "",
                "",
                "",
                "",
                "",
                "",
                UserRole.ADMIN
            );
            
            when(userService.findById(id)).thenReturn(userDto);
            when(userService.update(userDto)).thenThrow(new ValidationException("User is not valid"));
            
            mockMvc.perform(put("/api/admin/users/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("should return validation exception if user id in path and request body do not match")
        public void shouldReturnValidationException_IfUserIdInPathAndRequestBodyDoNotMatch() throws Exception {
            Long id = 1L;

            UserDto userDto = new UserDto(
                2L,
                "User",
                "Email",
                "Password",
                "Description",
                "Address",
                "Image",
                UserRole.ADMIN
            );
            
            when(userService.findById(id)).thenThrow(new ValidationException("ID in path and request body must match"));
            
            mockMvc.perform(put("/api/admin/users/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isBadRequest());
        }
    }

    @Nested
    @DisplayName("deleteUser")
    public class DeleteUser {
        
        @Test
        @DisplayName("should delete user if user is valid")
        public void shouldDeleteUser_IfUserIsValid() throws Exception {
            Long id = 1L;
            
            mockMvc.perform(delete("/api/admin/users/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        }
    }
}
