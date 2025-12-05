package es.artyhub.tienda_back.domain.dto;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import es.artyhub.tienda_back.domain.exception.ValidationException;
import es.artyhub.tienda_back.domain.validation.DtoValidator;

class CategoryDtotest {
    
    @Test
    @DisplayName("Create CategoryDto with valid data should not throw ValidationException")
    void createCategoryDto_WithValidData_ShouldNotThrowValidationException() {
        CategoryDto categoryDto = new CategoryDto(1L, "name");

        assertDoesNotThrow(() -> DtoValidator.validate(categoryDto));
    }

    static Stream<CategoryDto> invalidCategories() {
        return Stream.of(
            new CategoryDto(null, null),
            new CategoryDto(1L, ""),
            new CategoryDto(1L, " ")
        );
    }

    @ParameterizedTest
    @MethodSource("invalidCategories")
    @DisplayName("Create CategoryDto with invalid data should throw ValidationException")
    void createCategoryDto_WithInvalidData_ShouldThrowValidationException(CategoryDto categoryDto) {
        assertThrows(ValidationException.class, () -> DtoValidator.validate(categoryDto));
    }
}
