package es.artyhub.tienda_back.infraestructura.dto;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import es.artyhub.tienda_back.domain.exception.ValidationException;
import es.artyhub.tienda_back.domain.validation.DtoValidator;
import es.artyhub.tienda_back.infraestructura.payment.dto.DestinoDto;

public class DestinoDtoTest {
    
    @Test
    @DisplayName("should create destino dto")
    public void shouldCreateDestinoDto() {
        DestinoDto destinoDto = new DestinoDto(
            "ES1234567890123456789012"
        );

        assertDoesNotThrow(() -> DtoValidator.validate(destinoDto));
    }

    static Stream<DestinoDto> invalidValues() {
        return Stream.of(
            new DestinoDto(null),
            new DestinoDto("ES123456789012345678901"),
            new DestinoDto("ES12345678901234567890123")
        );
    }

    @ParameterizedTest
    @MethodSource("invalidValues")
    @DisplayName("should throw exception when destino dto is invalid")
    public void shouldThrowExceptionWhenDestinoDtoIsInvalid(DestinoDto invalidDto) {
        assertThrows(ValidationException.class, () -> DtoValidator.validate(invalidDto));
    }
}
