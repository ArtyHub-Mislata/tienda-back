package es.artyhub.tienda_back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import es.artyhub.tienda_back.domain.dto.CategoryDto;
import es.artyhub.tienda_back.domain.model.Page;
import es.artyhub.tienda_back.domain.service.CategoryService;

import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.http.MediaType;

@WebMvcTest(CategoryController.class)
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @MockitoBean
    private CategoryService categoryService;

    @Test
    @DisplayName("Test findAll Categories")
    void findAllCategories() throws Exception {
        CategoryDto categoryDto1 = new CategoryDto(1L, "Category 1");
        CategoryDto categoryDto2 = new CategoryDto(2L, "Category 2");
        CategoryDto categoryDto3 = new CategoryDto(3L, "Category 3");

        List<CategoryDto> categoryDtoList = List.of(categoryDto1, categoryDto2, categoryDto3);

        Page<CategoryDto> categoryDtoPage = new Page<>(categoryDtoList, 1, 10, 3);

        when(categoryService.findAll(1, 10)).thenReturn(categoryDtoPage);

        mockMvc.perform(get("/api/categories?pageNumber=1&pageSize=10"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.data.length()").value(3))
            .andExpect(jsonPath("$.data[0].name").value("Category 1"))
            .andExpect(jsonPath("$.data[1].name").value("Category 2"))
            .andExpect(jsonPath("$.data[2].name").value("Category 3"))
            .andExpect(jsonPath("$.pageNumber").value(1))
            .andExpect(jsonPath("$.pageSize").value(10))
            .andExpect(jsonPath("$.totalElements").value(3));
    }

    @Test
    @DisplayName("Test find Category by id")
    void findCategoryById() throws Exception {
        CategoryDto categoryDto = new CategoryDto(1L, "Category 1");

        when(categoryService.findById(1L)).thenReturn(Optional.of(categoryDto));

        mockMvc.perform(get("/api/categories/1"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(1L))
            .andExpect(jsonPath("$.name").value("Category 1"));
    }

    @Test
    @DisplayName("Test create Category")
    void createCategory() throws Exception {
        String categoryJson = """
                {
                    "id": 1,
                    "name": "name1"
                }
                """;

        CategoryDto categoryDto = new CategoryDto(1L, "Category 1");

        when(categoryService.insert(categoryDto)).thenReturn(categoryDto);

        mockMvc.perform(post("/api/categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(categoryJson))
            .andExpect(status().isNoContent())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(1L))
            .andExpect(jsonPath("$.name").value("Category 1"));
    }

    @Test
    @DisplayName("Test update Category")
    void updateCategory() throws Exception {
        String categoryJson = """
                {
                    "id": 1,
                    "name": "updatedName"
                }
                """;

        CategoryDto categoryDto = new CategoryDto(1L, "name1");
        CategoryDto updatedCategoryDto = new CategoryDto(1L, "updatedName");

        when(categoryService.update(categoryDto)).thenReturn(updatedCategoryDto);

        mockMvc.perform(put("/api/categories/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(categoryJson))
            .andExpect(status().isNoContent())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(1L))
            .andExpect(jsonPath("$.name").value("updatedName"));
    }

    @Test
    @DisplayName("Test delete Category")
    void deleteCategory() throws Exception {
        CategoryDto categoryDto = new CategoryDto(1L, "name1");

        when(categoryService.findById(categoryDto.id())).thenReturn(Optional.of(categoryDto));

        mockMvc.perform(delete("/api/categories/1"))
            .andExpect(status().isNoContent());
    }
}
