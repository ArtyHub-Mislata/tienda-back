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
import es.artyhub.tienda_back.infraestructura.payment.dto.OrigenDto;

public class OrigenDtoTest {
    
    @Test
    @DisplayName("should create origen dto")
    public void shouldCreateOrigenDto() {
        OrigenDto origenDto = new OrigenDto(
            "1234567890123456",
            "12/24",
            "123",
            "Juan Perez"
        );

        assertDoesNotThrow(() -> DtoValidator.validate(origenDto));
    }

    static Stream<OrigenDto> invalidValues() {
        return Stream.of(
            new OrigenDto("", "12/24", "123", "Juan Perez"),
            new OrigenDto(" ", "12/24", "123", "Juan Perez"),
            new OrigenDto(null, "12/24", "123", "Juan Perez"),
            new OrigenDto("123456789012345", "12/24", "123", "Juan Perez"),
            new OrigenDto("12345678901234567", "12/24", "123", "Juan Perez"),
            new OrigenDto("1234567890123456", null, "123", "Juan Perez"),
            new OrigenDto("12345678901234567", "12/24", null, "Juan Perez"),
            new OrigenDto("12345678901234567", "12/24", "123", null),
            new OrigenDto(null, null, null, null)
        );
    }

    @ParameterizedTest
    @MethodSource("invalidValues")
    @DisplayName("should throw exception when origen dto is invalid")
    public void shouldThrowExceptionWhenOrigenDtoIsInvalid(OrigenDto invalidDto) {
        assertThrows(ValidationException.class, () -> DtoValidator.validate(invalidDto));
    }
}
