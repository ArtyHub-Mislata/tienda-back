package es.artyhub.tienda_back.domain.dto;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Date;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import es.artyhub.tienda_back.domain.exception.ValidationException;
import es.artyhub.tienda_back.domain.validation.DtoValidator;

public class SesionDtoTest {
    
    @Test
    @DisplayName("should create sesion dto")
    public void shouldCreateSesionDto() {
        SesionDto sesionDto = new SesionDto(
            "token",
            1L,
            new Date()
        );

        assertDoesNotThrow(() -> DtoValidator.validate(sesionDto));
    }

    static Stream<SesionDto> invalidValues() {
        return Stream.of(
            new SesionDto("", 1L, new Date()),
            new SesionDto(" ", 1L, new Date()),
            new SesionDto(null, 1L, new Date()),
            new SesionDto("token", null, new Date()),
            new SesionDto("token", 1L, null),
            new SesionDto(null, 1L, null)
        );
    }

    @ParameterizedTest
    @MethodSource("invalidValues")
    @DisplayName("should throw exception when sesion dto is invalid")
    public void shouldThrowExceptionWhenSesionDtoIsInvalid(SesionDto invalidDto) {
        assertThrows(ValidationException.class, () -> DtoValidator.validate(invalidDto));
    }
}
