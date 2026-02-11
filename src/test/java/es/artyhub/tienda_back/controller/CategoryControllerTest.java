package es.artyhub.tienda_back.controller;

import static org.mockito.Mockito.when;

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

import es.artyhub.tienda_back.domain.dto.CategoryDto;
import es.artyhub.tienda_back.domain.model.Page;
import es.artyhub.tienda_back.domain.service.ArtworkService;
import es.artyhub.tienda_back.domain.service.CategoryService;
import es.artyhub.tienda_back.domain.service.SesionService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CategoryController.class)
@AutoConfigureMockMvc(addFilters = false)
public class CategoryControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CategoryService categoryService;

    @MockitoBean
    private SesionService sessionService;

    @MockitoBean
    private ArtworkService artworkService;

    @Nested
    @DisplayName("getAllCategories")
    public class GetAllCategories {
        
        @Test
        @DisplayName("should get all categories if categories are valid")
        public void shouldGetAllCategories_IfCategoriesAreValid() throws Exception {
            CategoryDto categoryDto = new CategoryDto(
                1L,
                "Category"
            );

            List<CategoryDto> categories = List.of(categoryDto);

            Page<CategoryDto> categoryDtoPage = new Page<>(
                categories,
                1,
                10,
                1L,
                2
            );
            
            when(categoryService.findAll(1, 10)).thenReturn(categoryDtoPage);
            
            mockMvc.perform(get("/api/categories")
                .param("pageNumber", "1")
                .param("pageSize", "10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].id").value(categoryDto.getId()))
                .andExpect(jsonPath("$.data[0].name").value(categoryDto.getName()));
        }

        @Test
        @DisplayName("should return not found if categories is empty")
        public void shouldReturnNotFound_IfCategoriesIsEmpty() throws Exception {
            Page<CategoryDto> categoryDtoPage = new Page<>(
                List.of(),
                1,
                10,
                0L,
                0
            );

            when(categoryService.findAll(1, 10)).thenReturn(categoryDtoPage);
            
            mockMvc.perform(get("/api/categories")
                .param("pageNumber", "1")
                .param("pageSize", "10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
        }
    }

    @Nested
    @DisplayName("getCategoryById")
    public class GetCategoryById {
        
        @Test
        @DisplayName("should get category if category is valid")
        public void shouldGetCategory_IfCategoryIsValid() throws Exception {
            Long id = 1L;
            CategoryDto categoryDto = new CategoryDto(
                id,
                "Category"
            );
            
            when(categoryService.findById(id)).thenReturn(categoryDto);
            
            mockMvc.perform(get("/api/categories/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(categoryDto.getId()))
                .andExpect(jsonPath("$.name").value(categoryDto.getName()));
        }

        @Test
        @DisplayName("should return not found if category is null")
        public void shouldReturnNotFound_IfCategoryIsNull() throws Exception {
            Long id = 1L;
            
            when(categoryService.findById(id)).thenReturn(null);
            
            mockMvc.perform(get("/api/categories/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
        }
    }
}
