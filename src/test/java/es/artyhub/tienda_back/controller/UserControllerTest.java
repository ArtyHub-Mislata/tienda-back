package es.artyhub.tienda_back.controller;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.artyhub.tienda_back.controller.webmodel.request.RegisterRequest;
import es.artyhub.tienda_back.domain.dto.ArtworkDto;
import es.artyhub.tienda_back.domain.dto.CartDto;
import es.artyhub.tienda_back.domain.dto.CartItemDto;
import es.artyhub.tienda_back.domain.dto.CredentialsDto;
import es.artyhub.tienda_back.domain.dto.UserDto;
import es.artyhub.tienda_back.domain.enums.UserRole;
import es.artyhub.tienda_back.domain.model.Page;
import es.artyhub.tienda_back.domain.service.CartService;
import es.artyhub.tienda_back.domain.service.LoginService;
import es.artyhub.tienda_back.domain.service.SesionService;
import es.artyhub.tienda_back.domain.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;
    
    @MockitoBean
    private LoginService loginService;

    @MockitoBean
    private SesionService sesionService;

    @MockitoBean
    private CartService cartService;

    @Autowired
    private ObjectMapper objectMapper;
    
    @Nested
    @DisplayName("getArtworksOfUser")
    public class GetArtworksOfUser {
        
        @Test
        @DisplayName("should get artworks if user is valid")
        public void shouldGetArtworks_IfUserIsValid() throws Exception {
            Long userId = 1L;
            UserDto userDto = new UserDto(
                userId,
                "Name",
                "Email",
                "Password",
                "Address",
                "Description",
                "ImageProfileUrl",
                UserRole.USER
            );

            Page<ArtworkDto> artworkDtoPage = userService.findAllArtworks(userId, 1, 20);
            
            when(userService.findById(userId)).thenReturn(userDto);
            when(userService.findAllArtworks(userId, 1, 20)).thenReturn(artworkDtoPage);
            
            mockMvc.perform(get("/api/users/{id}/artworks", userId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("login")
    public class Login {
        @Test
        @DisplayName("should login if credentials are valid")
        public void shouldLogin_IfCredentialsAreValid() throws Exception {
            CredentialsDto credentialsDto = new CredentialsDto(
                "email@gmail.com",
                "password"
            );
            
            when(loginService.login(any(CredentialsDto.class))).thenReturn("token-de-prueba");
            
            mockMvc.perform(post("/api/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(credentialsDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("token-de-prueba"));
        }
    }

    @Nested
    @DisplayName("logout")
    public class Logout {
        @Test
        @DisplayName("should logout if token is valid")
        public void shouldLogout_IfTokenIsValid() throws Exception {
            HttpServletRequest request = mock(HttpServletRequest.class);
            when(request.getHeader("Authorization")).thenReturn("token");

            mockMvc.perform(delete("/api/users/logout")
                .header("Authorization", "Bearer token-valido")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        }
    }

    @Nested
    @DisplayName("register")
    public class Register {
        @Test
        @DisplayName("should register if credentials are valid")
        public void shouldRegister_IfCredentialsAreValid() throws Exception {
            RegisterRequest registerRequest = new RegisterRequest(
                "Email",
                "Password",
                "Name",
                "Description",
                "Image",
                "Address"
            );

            UserDto newUserDto = new UserDto();
            newUserDto.setPassword(registerRequest.getPassword());
            newUserDto.setAddress(registerRequest.getAddress());
            newUserDto.setDescription(registerRequest.getDescription());
            newUserDto.setEmail(registerRequest.getEmail());
            newUserDto.setName(registerRequest.getName());
            newUserDto.setImageProfileUrl(registerRequest.getImageProfileUrl());
            newUserDto.setRole(UserRole.USER);
            
            when(userService.insert(newUserDto)).thenReturn(newUserDto);
            
            mockMvc.perform(post("/api/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(registerRequest)))
                .andExpect(status().isCreated());
        }

        @Test
        @DisplayName("should return bad request if credentials are not valid")
        public void shouldRegister_IfCredentialsAreNotValid() throws Exception {
            RegisterRequest registerRequest = new RegisterRequest(
                "",
                "",
                "",
                "",
                "",
                ""
            );

            UserDto newUserDto = new UserDto();
            newUserDto.setPassword(registerRequest.getPassword());
            newUserDto.setAddress(registerRequest.getAddress());
            newUserDto.setDescription(registerRequest.getDescription());
            newUserDto.setEmail(registerRequest.getEmail());
            newUserDto.setName(registerRequest.getName());
            newUserDto.setImageProfileUrl(registerRequest.getImageProfileUrl());
            newUserDto.setRole(UserRole.USER);
            
            when(userService.insert(newUserDto)).thenReturn(null);
            
            mockMvc.perform(post("/api/users/register")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
        }
    }

    @Nested
    @DisplayName("isLogged")
    public class IsLogged {
        @Test
        @DisplayName("should return true if user is logged")
        public void shouldReturnTrue_IfUserIsLogged() throws Exception {
            UserDto userDto = new UserDto(1L, "name", "email@gmail.com", "password", "description", "image", "address", UserRole.USER);

            mockMvc.perform(get("/api/users/islogged")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer token-valido")
                .with(request -> {
                    request.setAttribute("USER_DTO", userDto);
                    return request;
                }))
                .andExpect(status().isOk());
        }

        @Test
        @DisplayName("should return false if user is not logged")
        public void shouldReturnFalse_IfUserIsNotLogged() throws Exception {
            HttpServletRequest request = mock(HttpServletRequest.class);
            when(request.getAttribute("USER_DTO")).thenReturn(null);

            mockMvc.perform(get("/api/users/islogged")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
        }
    }

    @Nested
    @DisplayName("getLoggedUser")
    public class GetLoggedUser {
        @Test
        @DisplayName("should return logged user")
        public void shouldReturnLoggedUser() throws Exception {
            UserDto userDto = new UserDto(1L, "name", "email@gmail.com", "password", "description", "image", "address", UserRole.USER);

            mockMvc.perform(get("/api/users/logged")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer token-valido")
                .with(request -> {
                    request.setAttribute("USER_DTO", userDto);
                    return request;
                }))
                .andExpect(status().isOk());
        }

        @Test
        @DisplayName("should return null if user is not logged")
        public void shouldReturnNull_IfUserIsNotLogged() throws Exception {
            HttpServletRequest request = mock(HttpServletRequest.class);
            when(request.getAttribute("USER_DTO")).thenReturn(null);

            mockMvc.perform(get("/api/users/logged")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
        }
    }

    @Nested
    @DisplayName("/api/users/{id}")
    public class GetUserById {
        @Test
        @DisplayName("should return user by id")
        public void shouldReturnUserById() throws Exception {
            Long id = 1L;
            UserDto userDto = new UserDto(
                id,
                "Name",
                "Email",
                "Password",
                "Description",
                "Address",
                "Image",
                UserRole.USER
            );
            when(userService.findById(id)).thenReturn(userDto);

            mockMvc.perform(get("/api/users/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value("Name"))
                .andExpect(jsonPath("$.email").value("Email"))
                .andExpect(jsonPath("$.description").value("Description"))
                .andExpect(jsonPath("$.address").value("Address"))
                .andExpect(jsonPath("$.role").value("USER"));
        }

        @Test
        @DisplayName("should return not found if user is not found")
        public void shouldReturnUserNotFound() throws Exception {
            Long id = 1L;
            when(userService.findById(id)).thenReturn(null);

            mockMvc.perform(get("/api/users/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
        }
    }

    @Nested
    @DisplayName("/api/users/cart")
    public class GetUserCart {
        @Test
        @DisplayName("should return user cart")
        public void shouldReturnUserCart() throws Exception {
            UserDto userDto = new UserDto(
                1L,
                "Name",
                "Email",
                "Password",
                "Description",
                "Address",
                "Image",
                UserRole.USER
            );

            CartDto cartDto = new CartDto(
                1L,
                List.of(new CartItemDto()),
                userDto

            );

            when(cartService.getCartOfUser(userDto.getId())).thenReturn(cartDto);

            mockMvc.perform(get("/api/users/cart")
                .contentType(MediaType.APPLICATION_JSON)
                .with(request -> {
                    request.setAttribute("USER_DTO", userDto);
                    return request;
                }))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(cartDto.getId()))
                .andExpect(jsonPath("$.cartItems").isArray())
                .andExpect(jsonPath("$.userDto.id").value(cartDto.getUserDto().getId()));
        }

        @Test
        @DisplayName("should return unauthorized if user is not logged")
        public void shouldReturnUnauthorized() throws Exception {
            HttpServletRequest request = mock(HttpServletRequest.class);
            when(request.getAttribute("USER_DTO")).thenReturn(null);

            mockMvc.perform(get("/api/users/cart")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
        }
    }
}