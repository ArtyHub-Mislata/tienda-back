package es.artyhub.tienda_back.domain.dto;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import es.artyhub.tienda_back.domain.exception.ValidationException;
import es.artyhub.tienda_back.domain.validation.DtoValidator;

public class CategoryDtoTest {
    
    @Test
    @DisplayName("should create category dto")
    public void shouldCreateCategoryDto() {
        CategoryDto categoryDto = new CategoryDto(
            1L,
            "Name"
        );

        assertDoesNotThrow(() -> DtoValidator.validate(categoryDto));
    }

    static Stream<CategoryDto> invalidValues() {
        return Stream.of(
            new CategoryDto(1L, ""),
            new CategoryDto(1L, " "),
            new CategoryDto(1L, null)
        );
    }

    @ParameterizedTest
    @MethodSource("invalidValues")
    @DisplayName("should throw exception when category dto is invalid")
    public void shouldThrowExceptionWhenCategoryDtoIsInvalid(CategoryDto invalidDto) {
        assertThrows(ValidationException.class, () -> DtoValidator.validate(invalidDto));
    }
}
