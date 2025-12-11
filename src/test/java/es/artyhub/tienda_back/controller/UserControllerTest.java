package es.artyhub.tienda_back.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.context.bean.override.mockito.MockitoBean;

import es.artyhub.tienda_back.domain.dto.UserDto;
import es.artyhub.tienda_back.domain.enums.UserRole;
import es.artyhub.tienda_back.domain.model.Page;
import es.artyhub.tienda_back.domain.service.UserService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.springframework.http.MediaType;

@WebMvcTest(UserController.class)
class UserControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @Test
    @DisplayName("Test findAll Users")
    void findAllUsers() throws Exception {
        UserDto userDto1 = new UserDto(1L, "name1", "email1", "password1", "nAccount1", "description1", "address1", "imageProfileUrl1", List.of(), UserRole.ADMIN);
        UserDto userDto2 = new UserDto(2L, "name2", "email2", "password2", "nAccount2", "description2", "address2", "imageProfileUrl2", List.of(), UserRole.USER);

        List<UserDto> userDtoList = List.of(userDto1, userDto2);

        Page<UserDto> userDtoPage = new Page<>(userDtoList, 1, 10, 2);

        when(userService.findAll(1, 10)).thenReturn(userDtoPage);

        mockMvc.perform(get("/api/users?pageNumber="+userDtoPage.pageNumber()+"&pageSize="+userDtoPage.pageSize()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.data.length()").value(2))
            .andExpect(jsonPath("$.data[0].name").value("name1"))
            .andExpect(jsonPath("$.data[1].name").value("name2"))
            .andExpect(jsonPath("$.pageNumber").value(1))
            .andExpect(jsonPath("$.pageSize").value(10))
            .andExpect(jsonPath("$.totalElements").value(2));
    }

    @Test
    @DisplayName("Test find User by id")
    void findUserById() throws Exception {
        UserDto userDto = new UserDto(1L, "name1", "email1", "password1", "nAccount1", "description1", "address1", "imageProfileUrl1", List.of(), UserRole.ADMIN);

        when(userService.findById(userDto.getId())).thenReturn(userDto);

        mockMvc.perform(get("/api/users/" + userDto.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(1L))
            .andExpect(jsonPath("$.name").value("name1"))
            .andExpect(jsonPath("$.email").value("email1"))
            .andExpect(jsonPath("$.password").value("password1"))
            .andExpect(jsonPath("$.nAccount").value("nAccount1"))
            .andExpect(jsonPath("$.description").value("description1"))
            .andExpect(jsonPath("$.address").value("address1"))
            .andExpect(jsonPath("$.imageProfileUrl").value("imageProfileUrl1"));
    }

    @Test
    @DisplayName("Test create user")
    void createUser() throws Exception {
        String userJson = """
                {
                    "id": 1,
                    "name": "name1",
                    "email": "email1",
                    "password": "password1",
                    "nAccount": "nAccount1",
                    "description": "description1",
                    "address": "address1",
                    "imageProfileUrl": "imageProfileUrl1",
                    "artworks": []
                }
                """;

        UserDto userDto = new UserDto(1L, "name1", "email1", "password1", "nAccount1", "description1", "address1", "imageProfileUrl1", List.of(), UserRole.ADMIN);

        when(userService.insert(userDto)).thenReturn(userDto);

        mockMvc.perform(post("/api/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(userJson))
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(1L))
            .andExpect(jsonPath("$.name").value("name1"))
            .andExpect(jsonPath("$.email").value("email1"))
            .andExpect(jsonPath("$.password").value("password1"))
            .andExpect(jsonPath("$.nAccount").value("nAccount1"))
            .andExpect(jsonPath("$.description").value("description1"))
            .andExpect(jsonPath("$.address").value("address1"))
            .andExpect(jsonPath("$.imageProfileUrl").value("imageProfileUrl1"));
    }

    @Test
    @DisplayName("Test update user")
    void updateUser() throws Exception {
        String userJson = """
                {
                    "id": 1,
                    "name": "updatedName1",
                    "email": "updatedEmail1",
                    "password": "password1",
                    "nAccount": "nAccount1",
                    "description": "description1",
                    "address": "address1",
                    "imageProfileUrl": "imageProfileUrl1",
                    "artworks": []
                }
                """;
                
        UserDto userDto = new UserDto(1L, "name1", "email1", "password1", "nAccount1", "description1", "address1", "imageProfileUrl1", List.of(), UserRole.ADMIN);
        UserDto updatedUserDto = new UserDto(1L, "updatedName1", "updatedEmail1", "password1", "nAccount1", "description1", "address1", "imageProfileUrl1", List.of(), UserRole.ADMIN);

        when(userService.update(userDto)).thenReturn(updatedUserDto);

        mockMvc.perform(put("/api/users/" + userDto.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .content(userJson))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(1L))
            .andExpect(jsonPath("$.name").value("updatedName1"))
            .andExpect(jsonPath("$.email").value("updatedEmail1"))
            .andExpect(jsonPath("$.password").value("password1"))
            .andExpect(jsonPath("$.nAccount").value("nAccount1"))
            .andExpect(jsonPath("$.description").value("description1"))
            .andExpect(jsonPath("$.address").value("address1"))
            .andExpect(jsonPath("$.imageProfileUrl").value("imageProfileUrl1"));
    }

    @Test
    @DisplayName("Test delete user")
    void deleteUser() throws Exception {
        UserDto userDto = new UserDto(1L, "name1", "email1", "password1", "nAccount1", "description1", "address1", "imageProfileUrl1", List.of(), UserRole.ADMIN);

        when(userService.findById(userDto.getId())).thenReturn(userDto);

        mockMvc.perform(delete("/api/users/" + userDto.getId()))
            .andExpect(status().isNoContent());
    }
}
