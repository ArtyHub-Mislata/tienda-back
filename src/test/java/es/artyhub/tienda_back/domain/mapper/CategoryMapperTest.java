package es.artyhub.tienda_back.domain.mapper;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import es.artyhub.tienda_back.domain.dto.CategoryDto;
import es.artyhub.tienda_back.domain.exception.BusinessException;
import es.artyhub.tienda_back.domain.model.Category;

class CategoryMapperTest {
    
    @Nested
    @DisplayName("From Category to CategoryDto")
    class FromCategoryToCategoryDto {

        @Test
        @DisplayName("Should return CategoryDto")
        void shouldReturnCategoryDto() {
            Category category = new Category(1L, "Category");
            
            CategoryDto categoryDto = CategoryMapper.getInstance().fromCategoryToCategoryDto(category);
            
            assertAll(
                () -> assertNotNull(categoryDto, "CategoryDto should not be null"),
                () -> assertEquals(categoryDto.id(), category.getId(), "CategoryDto id should be equal to Category id"),
                () -> assertEquals(categoryDto.name(), category.getName(), "CategoryDto name should be equal to Category name")
            );
        }
        
        @Test
        @DisplayName("When category is null should throw BusinessException")
        void whenCategoryIsNull_ShouldThrowBusinessException() {
            Category category = null;
            
            assertThrows(BusinessException.class, () -> CategoryMapper.getInstance().fromCategoryToCategoryDto(category));
        }
    }

    @Nested
    @DisplayName("From CategoryDto to Category")
    class FromCategoryDtoToCategory {

        @Test
        @DisplayName("Should return Category")
        void shouldReturnCategory() {
            CategoryDto categoryDto = new CategoryDto(1L, "Category");
            
            Category category = CategoryMapper.getInstance().fromCategoryDtoToCategory(categoryDto);
            
            assertAll(
                () -> assertNotNull(category, "Category should not be null"),
                () -> assertEquals(category.getId(), categoryDto.id(), "Category id should be equal to CategoryDto id"),
                () -> assertEquals(category.getName(), categoryDto.name(), "Category name should be equal to CategoryDto name")
            );
        }
        
        @Test
        @DisplayName("When categoryDto is null should throw BusinessException")
        void whenCategoryDtoIsNull_ShouldThrowBusinessException() {
            CategoryDto categoryDto = null;
            
            assertThrows(BusinessException.class, () -> CategoryMapper.getInstance().fromCategoryDtoToCategory(categoryDto));
        }
    }
}
