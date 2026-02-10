package es.artyhub.tienda_back.controller;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;

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
import es.artyhub.tienda_back.domain.model.Page;
import es.artyhub.tienda_back.domain.service.ArtworkService;
import es.artyhub.tienda_back.domain.service.SesionService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ArtworkController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ArtworkControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ArtworkService artworkService;

    @MockitoBean
    private SesionService sesionService;

    @Nested
    @DisplayName("getAllArtworks")
    public class GetAllArtworks {
        
        @Test
        @DisplayName("should get all artworks if artworks are valid")
        public void shouldGetAllArtworks_IfArtworksAreValid() throws Exception {
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

            List<ArtworkDto> artworks = List.of(artworkDto);

            Page<ArtworkDto> artworkDtoPage = new Page<>(
                artworks,
                1,
                10,
                1L,
                2
            );
            
            when(artworkService.findAll(1, 10)).thenReturn(artworkDtoPage);
            
            mockMvc.perform(get("/api/artworks")
                .param("pageNumber", "1")
                .param("pageSize", "10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].id").value(artworkDto.getId()))
                .andExpect(jsonPath("$.data[0].name").value(artworkDto.getName()))
                .andExpect(jsonPath("$.data[0].description").value(artworkDto.getDescription()))
                .andExpect(jsonPath("$.data[0].image").value(artworkDto.getImage()))
                .andExpect(jsonPath("$.data[0].price").value(artworkDto.getPrice()))
                .andExpect(jsonPath("$.data[0].stock").value(artworkDto.getStock()));
        }

        @Test
        @DisplayName("should return not found if artworks is empty")
        public void shouldReturnNotFound_IfArtworksIsEmpty() throws Exception {
            Page<ArtworkDto> artworkDtoPage = new Page<>(
                List.of(),
                1,
                10,
                0L,
                0
            );

            when(artworkService.findAll(1, 10)).thenReturn(artworkDtoPage);
            
            mockMvc.perform(get("/api/artworks")
                .param("pageNumber", "1")
                .param("pageSize", "10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
        }
    }

    @Nested
    @DisplayName("getArtworkById")
    public class GetArtworkById {
        
        @Test
        @DisplayName("should get artwork if artwork is valid")
        public void shouldGetArtwork_IfArtworkIsValid() throws Exception {
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
            ArtworkDto artworkDto = new ArtworkDto(
                id,
                "Artwork",
                "Descripcion",
                "Image",
                BigDecimal.valueOf(20.0),
                categoryDto,
                userDto,
                10L
            );
            
            when(artworkService.findById(id)).thenReturn(artworkDto);
            
            mockMvc.perform(get("/api/artworks/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(artworkDto.getId()))
                .andExpect(jsonPath("$.name").value(artworkDto.getName()))
                .andExpect(jsonPath("$.description").value(artworkDto.getDescription()))
                .andExpect(jsonPath("$.image").value(artworkDto.getImage()))
                .andExpect(jsonPath("$.price").value(artworkDto.getPrice()))
                .andExpect(jsonPath("$.stock").value(artworkDto.getStock()));
        }

        @Test
        @DisplayName("should return not found if artwork is null")
        public void shouldReturnNotFound_IfArtworkIsNull() throws Exception {
            Long id = 1L;
            
            when(artworkService.findById(id)).thenReturn(null);
            
            mockMvc.perform(get("/api/artworks/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
        }
    }
}
