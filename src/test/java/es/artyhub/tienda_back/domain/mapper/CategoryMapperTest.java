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

public class CategoryMapperTest {
    
    @Nested
    @DisplayName("Test fromCategoryToCategoryDto")
    class FromCategoryToCategoryDtoTest {

        @Test
        @DisplayName("Test fromCategoryToCategoryDto with null Category should throw exception")
        void testFromCategoryToCategoryDto_NullInput() {
            assertThrows(BusinessException.class, () -> CategoryMapper.getInstance().fromCategoryToCategoryDto(null));
        }

        @Test
        @DisplayName("Test fromCategoryToCategoryDto with valid Category should return CategoryDto")
        void testFromCategoryToCategoryDto_ValidInput() {
            Category category = new Category(
                    1L,
                    "Category"
            );

            CategoryDto categoryDto = CategoryMapper.getInstance().fromCategoryToCategoryDto(category);

            assertAll(
                    () -> assertNotNull(categoryDto),
                    () -> assertEquals(1L, categoryDto.getId()),
                    () -> assertEquals("Category", categoryDto.getName()));
        }
    }

    @Nested
    @DisplayName("Test fromCategoryDtoToCategory")
    class FromCategoryDtoToCategoryTest {

        @Test
        @DisplayName("Test fromCategoryDtoToCategory with null CategoryDto should throw exception")
        void testFromCategoryDtoToCategory_NullInput() {
            assertThrows(BusinessException.class, () -> CategoryMapper.getInstance().fromCategoryDtoToCategory(null));
        }

        @Test
        @DisplayName("Test fromCategoryDtoToCategory with valid CategoryDto should return Category")
        void testFromCategoryDtoToCategory_ValidInput() {
            CategoryDto categoryDto = new CategoryDto(
                    1L,
                    "Category"
            );

            Category category = CategoryMapper.getInstance().fromCategoryDtoToCategory(categoryDto);

            assertAll(
                    () -> assertNotNull(category),
                    () -> assertEquals(1L, category.getId()),
                    () -> assertEquals("Category", category.getName()));
        }
    }
}
