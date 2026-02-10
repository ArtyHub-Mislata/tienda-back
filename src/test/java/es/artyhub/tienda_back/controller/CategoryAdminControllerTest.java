package es.artyhub.tienda_back.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.artyhub.tienda_back.domain.dto.CategoryDto;
import es.artyhub.tienda_back.domain.exception.ValidationException;
import es.artyhub.tienda_back.domain.service.CategoryService;
import es.artyhub.tienda_back.domain.service.SesionService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CategoryAdminController.class)
@AutoConfigureMockMvc(addFilters = false)
public class CategoryAdminControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CategoryService categoryService;

    @MockitoBean
    private SesionService sessionService;

    @Autowired
    private ObjectMapper objectMapper;

    @Nested
    @DisplayName("createCategory")
    public class CreateCategory {
        
        @Test
        @DisplayName("should create category if category is valid")
        public void shouldCreateCategory_IfCategoryIsValid() throws Exception {
            CategoryDto categoryDto = new CategoryDto(
                1L,
                "Category"
            );
            
            when(categoryService.insert(any(CategoryDto.class))).thenReturn(categoryDto);
            
            mockMvc.perform(post("/api/admin/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categoryDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(categoryDto.getId()));
        }

        @Test
        @DisplayName("should return validation exception if category is not valid")
        public void shouldReturnValidationException_IfCategoryIsNotValid() throws Exception {
            CategoryDto categoryDto = new CategoryDto(
                1L,
                ""
            );

            when(categoryService.insert(any(CategoryDto.class))).thenThrow(new ValidationException("Category is not valid"));
            
            mockMvc.perform(post("/api/admin/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categoryDto)))
                .andExpect(status().isBadRequest());
        }
    }

    @Nested
    @DisplayName("updateCategory")
    public class UpdateCategory {
        
        @Test
        @DisplayName("should update category if category is valid")
        public void shouldUpdateCategory_IfCategoryIsValid() throws Exception {
            Long id = 1L;
            CategoryDto existentCategoryDto = new CategoryDto(
                id,
                "Category"
            );

            CategoryDto newCategoryDto = new CategoryDto(
                id,
                "New Category"
            );

            
            when(categoryService.findById(id)).thenReturn(existentCategoryDto);
            when(categoryService.update(any(CategoryDto.class))).thenReturn(newCategoryDto);
            
            mockMvc.perform(put("/api/admin/categories/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newCategoryDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(newCategoryDto.getId()))
                .andExpect(jsonPath("$.id").value(existentCategoryDto.getId()))
                .andExpect(jsonPath("$.name").value(newCategoryDto.getName()));
        }

        @Test
        @DisplayName("should return validation exception if category is not valid")
        public void shouldReturnValidationException_IfCategoryIsNotValid() throws Exception {
            Long id = 1L;
            CategoryDto categoryDto = new CategoryDto(
                id,
                ""
            );
            
            when(categoryService.findById(id)).thenReturn(categoryDto);
            when(categoryService.update(any(CategoryDto.class))).thenThrow(new ValidationException("Category is not valid"));
            
            mockMvc.perform(put("/api/admin/categories/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categoryDto)))
                .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("should return validation exception if category id in path and request body do not match")
        public void shouldReturnValidationException_IfCategoryIdInPathAndRequestBodyDoNotMatch() throws Exception {
            Long id = 1L;

            CategoryDto newCategoryDto = new CategoryDto(
                2L,
                "New Category"
            );
            
            when(categoryService.findById(id)).thenThrow(new ValidationException("ID in path and request body must match"));
            
            mockMvc.perform(put("/api/admin/categories/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newCategoryDto)))
                .andExpect(status().isBadRequest());
        }
    }

    @Nested
    @DisplayName("deleteCategory")
    public class DeleteCategory {
        
        @Test
        @DisplayName("should delete category if category is valid")
        public void shouldDeleteCategory_IfCategoryIsValid() throws Exception {
            Long id = 1L;
            
            mockMvc.perform(delete("/api/admin/categories/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        }
    }
}
