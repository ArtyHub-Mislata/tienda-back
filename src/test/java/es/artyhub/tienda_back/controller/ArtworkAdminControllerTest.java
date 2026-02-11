package es.artyhub.tienda_back.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import es.artyhub.tienda_back.domain.dto.ArtworkDto;
import es.artyhub.tienda_back.domain.dto.CategoryDto;
import es.artyhub.tienda_back.domain.dto.UserDto;
import es.artyhub.tienda_back.domain.enums.UserRole;
import es.artyhub.tienda_back.domain.exception.ValidationException;
import es.artyhub.tienda_back.domain.service.ArtworkService;
import es.artyhub.tienda_back.domain.service.SesionService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(ArtworkAdminController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ArtworkAdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ArtworkService artworkService;

    @MockitoBean
    private SesionService sesionService;

    @Autowired
    private ObjectMapper objectMapper;

    @Nested
    @DisplayName("createArtwork")
    public class CreateArtwork {
        
        @Test
        @DisplayName("should create artwork if artwork is valid")
        public void shouldCreateArtwork_IfArtworkIsValid() throws Exception {
            CategoryDto categoryDto = new CategoryDto(
                1L,
                "Category"
            );
            UserDto userDto = new UserDto(
                1L,
                "User",
                "Email",
                "Password",
                "Description",
                "Address",
                "Image",
                UserRole.USER
            );
            ArtworkDto artworkDto = new ArtworkDto(
                1L,
                "Artwork",
                "Descripcion",
                "Image",
                BigDecimal.valueOf(20.0),
                categoryDto,
                userDto,
                10L
            );
            
            when(artworkService.insert(any(ArtworkDto.class))).thenReturn(artworkDto);
            
            mockMvc.perform(post("/api/admin/artworks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(artworkDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(artworkDto.getId()));
        }

        @Test
        @DisplayName("should return validation exception if artwork is not valid")
        public void shouldReturnValidationException_IfArtworkIsNotValid() throws Exception {
            ArtworkDto artworkDto = new ArtworkDto(
                1L,
                "Artwork",
                "Descripcion",
                "Image",
                BigDecimal.valueOf(20.0),
                null,
                null,    
                10L
            );

            when(artworkService.insert(artworkDto)).thenThrow(new ValidationException("Artwork is not valid"));
            
            mockMvc.perform(post("/api/admin/artworks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(artworkDto)))
                .andExpect(status().isBadRequest());
        }
    }

    @Nested
    @DisplayName("updateArtwork")
    public class UpdateArtwork {
        
        @Test
        @DisplayName("should update artwork if artwork is valid")
        public void shouldUpdateArtwork_IfArtworkIsValid() throws Exception {
            Long id = 1L;
            CategoryDto categoryDto = new CategoryDto(
                1L,
                "Category"
            );
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
            ArtworkDto artworkExists = new ArtworkDto(
                id,
                "Artwork",
                "Descripcion",
                "Image",
                BigDecimal.valueOf(20.0),
                categoryDto,
                userDto,
                10L
            );

            ArtworkDto newArtworkDto = new ArtworkDto(
                id,
                "New Artwork",
                "New Descripcion",
                "New Image",
                BigDecimal.valueOf(20.0),
                categoryDto,
                userDto,
                10L
            );
            
            when(artworkService.findById(id)).thenReturn(artworkExists);
            when(artworkService.update(any(ArtworkDto.class))).thenReturn(newArtworkDto);
            
            mockMvc.perform(put("/api/admin/artworks/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newArtworkDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(newArtworkDto.getId()))
                .andExpect(jsonPath("$.id").value(artworkExists.getId()))
                .andExpect(jsonPath("$.name").value(newArtworkDto.getName()))
                .andExpect(jsonPath("$.description").value(newArtworkDto.getDescription()))
                .andExpect(jsonPath("$.image").value(newArtworkDto.getImage()));
        }

        @Test
        @DisplayName("should return validation exception if artwork is not valid")
        public void shouldReturnValidationException_IfArtworkIsNotValid() throws Exception {
            Long id = 1L;
            CategoryDto categoryDto = new CategoryDto(
                1L,
                "Category"
            );
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
            ArtworkDto artworkExists = new ArtworkDto(
                id,
                "Artwork",
                "Descripcion",
                "Image",
                BigDecimal.valueOf(20.0),
                categoryDto,
                userDto,
                10L
            );

            ArtworkDto newArtworkDto = new ArtworkDto(
                id,
                "New Artwork",
                "New Descripcion",
                "New Image",
                BigDecimal.valueOf(20.0),
                null,
                null,
                10L
            );
            
            when(artworkService.findById(id)).thenReturn(artworkExists);
            when(artworkService.update(any(ArtworkDto.class))).thenThrow(new ValidationException("Artwork is not valid"));
            
            mockMvc.perform(put("/api/admin/artworks/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newArtworkDto)))
                .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("should return not found exception if artwork is not found")
        public void shouldReturnNotFoundException_IfArtworkIsNotFound() throws Exception {
            Long id = 1L;

            ArtworkDto newArtworkDto = new ArtworkDto(
                2L,
                "New Artwork",
                "New Descripcion",
                "New Image",
                BigDecimal.valueOf(20.0),
                null,
                null,
                10L
            );
            
            when(artworkService.findById(id)).thenThrow(new ValidationException("ID in path and request body must match"));
            
            mockMvc.perform(put("/api/admin/artworks/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newArtworkDto)))
                .andExpect(status().isBadRequest());
        }
    }

    @Nested
    @DisplayName("deleteArtwork")
    public class DeleteArtwork {
        
        @Test
        @DisplayName("should delete artwork if artwork is valid")
        public void shouldDeleteArtwork_IfArtworkIsValid() throws Exception {
            Long id = 1L;
            
            mockMvc.perform(delete("/api/admin/artworks/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        }
    }
}
