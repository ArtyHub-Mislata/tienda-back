package es.artyhub.tienda_back.persistence.repository.mapper;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import es.artyhub.tienda_back.domain.dto.CategoryDto;
import es.artyhub.tienda_back.persistence.dao.jpa.entity.CategoryJpaEntity;

public class CategoryMapperTest {
    
    @Nested
    @DisplayName("Test fromCategoryJpaEntityToCategoryDto")
    class FromCategoryJpaEntityToCategoryDtoTest {

        @Test
        @DisplayName("Test fromCategoryJpaEntityToCategoryDto with null CategoryJpaEntity should return null")
        void testFromCategoryJpaEntityToCategoryDto_NullInput() {
            CategoryDto result = CategoryMapper.getInstance().fromCategoryJpaEntityToCategoryDto(null);
            assertNull(result);
        }

        @Test
        @DisplayName("Test fromCategoryJpaEntityToCategoryDto with valid CategoryJpaEntity should return CategoryDto")
        void testFromCategoryJpaEntityToCategoryDto_ValidInput() {
            CategoryJpaEntity categoryJpaEntity = new CategoryJpaEntity(
                    1L,
                    "Category"
            );

            CategoryDto categoryDto = CategoryMapper.getInstance().fromCategoryJpaEntityToCategoryDto(categoryJpaEntity);

            assertAll(
                    () -> assertNotNull(categoryDto),
                    () -> assertEquals(1L, categoryDto.getId()),
                    () -> assertEquals("Category", categoryDto.getName()));
        }
    }

    @Nested
    @DisplayName("Test fromCategoryDtoToCategoryJpaEntity")
    class FromCategoryDtoToCategoryJpaEntityTest {

        @Test
        @DisplayName("Test fromCategoryDtoToCategoryJpaEntity with null CategoryDto should return null")
        void testFromCategoryDtoToCategoryJpaEntity_NullInput() {
            CategoryJpaEntity result = CategoryMapper.getInstance().fromCategoryDtoToCategoryJpaEntity(null);
            assertNull(result);
        }

        @Test
        @DisplayName("Test fromCategoryDtoToCategoryJpaEntity with valid CategoryDto should return CategoryJpaEntity")
        void testFromCategoryDtoToCategoryJpaEntity_ValidInput() {
            CategoryDto categoryDto = new CategoryDto(
                    1L,
                    "Category"
            );

            CategoryJpaEntity result = CategoryMapper.getInstance().fromCategoryDtoToCategoryJpaEntity(categoryDto);

            assertAll(
                    () -> assertNotNull(result),
                    () -> assertEquals(1L, result.getId()),
                    () -> assertEquals("Category", result.getNombre()));
        }
    }
}
