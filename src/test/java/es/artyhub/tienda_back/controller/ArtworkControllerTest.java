package es.artyhub.tienda_back.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.http.MediaType;

import es.artyhub.tienda_back.domain.dto.ArtworkDto;
import es.artyhub.tienda_back.domain.dto.CategoryDto;
import es.artyhub.tienda_back.domain.model.Page;
import es.artyhub.tienda_back.domain.service.ArtworkService;
import java.math.BigDecimal;

@WebMvcTest(ArtworkController.class)
public class ArtworkControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ArtworkService artworkService;

    @Test
    @DisplayName("Test findAll Artworks")
    void findAllArtworks() throws Exception {
        ArtworkDto artworkDto1 = new ArtworkDto(1L, "name1", "description1", "url1", new BigDecimal(1.0), 1, new CategoryDto(1L, "name1"));
        ArtworkDto artworkDto2 = new ArtworkDto(2L, "name2", "description2", "url2", new BigDecimal(2.0), 2, new CategoryDto(2L, "name2"));

        List<ArtworkDto> artworkDtoList = List.of(artworkDto1, artworkDto2);

        Page<ArtworkDto> artworkDtoPage = new Page<>(artworkDtoList, 1, 10, 2);

        when(artworkService.findAll(1, 10)).thenReturn(artworkDtoPage);

        mockMvc.perform(get("/api/artworks?pageNumber=" + artworkDtoPage.pageNumber() + "&pageSize=" + artworkDtoPage.pageSize()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.data.length()").value(2))
            .andExpect(jsonPath("$.data[0].title").value("name1"))
            .andExpect(jsonPath("$.data[1].title").value("name2"))
            .andExpect(jsonPath("$.pageNumber").value(1))
            .andExpect(jsonPath("$.pageSize").value(10))
            .andExpect(jsonPath("$.totalElements").value(2));
    }

    @Test
    @DisplayName("Test find Artwork by id")
    void findArtworkById() throws Exception {
        ArtworkDto artworkDto = new ArtworkDto(1L, "name1", "description1", "url1", new BigDecimal(1.0), 1, new CategoryDto(1L, "name1"));

        when(artworkService.findById(1L)).thenReturn(artworkDto);

        mockMvc.perform(get("/api/artworks/" + artworkDto.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.title").value("name1"))
            .andExpect(jsonPath("$.description").value("description1"))
            .andExpect(jsonPath("$.price").value(1.0))
            .andExpect(jsonPath("$.stock").value(1))
            .andExpect(jsonPath("$.category.id").value(1L));
    }

    @Test
    @DisplayName("Test create Artwork")
    void createArtwork() throws Exception {
        String artworkJson = """
                {
                    "id": 1,
                    "name": "name1",
                    "description": "description1",
                    "price": 1.0,
                    "stock": 1,
                    "category": {"id": 1, "name": "name1"}
                }
                """;

        ArtworkDto artworkDto = new ArtworkDto(1L, "name1", "description1", "url1", new BigDecimal(1.0), 1, new CategoryDto(1L, "name1"));

        when(artworkService.insert(artworkDto)).thenReturn(artworkDto);

        mockMvc.perform(post("/api/artworks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(artworkJson))
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(1L))
            .andExpect(jsonPath("$.name").value("name1"))
            .andExpect(jsonPath("$.description").value("description1"))
            .andExpect(jsonPath("$.price").value(1.0))
            .andExpect(jsonPath("$.stock").value(1))
            .andExpect(jsonPath("$.category.id").value(1L))
            .andExpect(jsonPath("$.category.name").value("name1"));
    }

    @Test
    @DisplayName("Test update Artwork")
    void updateArtwork() throws Exception {
        String artworkJson = """
                {
                    "id": 1,
                    "name": "updatedName1",
                    "description": "updatedDescription1",
                    "price": 1.0,
                    "stock": 1,
                    "category": 
                        {"id": 1, "name": "name1"}
                }
                """;

        ArtworkDto artworkDto = new ArtworkDto(1L, "name1", "description1", "url1", new BigDecimal(1.0), 1, new CategoryDto(1L, "name1"));
        ArtworkDto artworkDtoUpdated = new ArtworkDto(1L, "updatedName1", "updatedDescription1", "url1", new BigDecimal(1.0), 1, new CategoryDto(1L, "name1"));

        when(artworkService.update(artworkDto)).thenReturn(artworkDtoUpdated);

        mockMvc.perform(put("/api/artworks/" + artworkDto.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .content(artworkJson))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(1L))
            .andExpect(jsonPath("$.name").value("updatedName1"))
            .andExpect(jsonPath("$.description").value("updatedDescription1"))
            .andExpect(jsonPath("$.price").value(1.0))
            .andExpect(jsonPath("$.stock").value(1))
            .andExpect(jsonPath("$.category.id").value(1L))
            .andExpect(jsonPath("$.category.name").value("name1"));
    }

    @Test
    @DisplayName("Test delete Artwork")
    void deleteArtwork() throws Exception {
        ArtworkDto artworkDto = new ArtworkDto(1L, "name1", "description1", "url1", new BigDecimal(1.0), 1, new CategoryDto(1L, "name1"));

        when(artworkService.findById(artworkDto.getId())).thenReturn(artworkDto);

        mockMvc.perform(delete("/api/artworks/" + artworkDto.getId()))
            .andExpect(status().isNoContent());
    }
}
