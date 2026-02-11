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
import es.artyhub.tienda_back.infraestructura.payment.dto.AutorizacionDto;

public class AutorizacionDtoTest {
    
    @Test
    @DisplayName("should create autorizacion dto")
    public void shouldCreateAutorizacionDto() {
        AutorizacionDto autorizacionDto = new AutorizacionDto(
            "login",
            "api_token"
        );

        assertDoesNotThrow(() -> DtoValidator.validate(autorizacionDto));
    }

    static Stream<AutorizacionDto> invalidValues() {
        return Stream.of(
            new AutorizacionDto("", "api_token"),
            new AutorizacionDto(" ", "api_token"),
            new AutorizacionDto(null, "api_token"),
            new AutorizacionDto("login", ""),
            new AutorizacionDto("login", " "),
            new AutorizacionDto("login", null),
            new AutorizacionDto(null, null),
            new AutorizacionDto(null, ""),
            new AutorizacionDto("", null),
            new AutorizacionDto("", ""),
            new AutorizacionDto("", " ")
        );
    }

    @ParameterizedTest
    @MethodSource("invalidValues")
    @DisplayName("should throw exception when autorizacion dto is invalid")
    public void shouldThrowExceptionWhenAutorizacionDtoIsInvalid(AutorizacionDto invalidDto) {
        assertThrows(ValidationException.class, () -> DtoValidator.validate(invalidDto));
    }
}
