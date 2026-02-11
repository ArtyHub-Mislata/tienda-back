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

public class CredentialsDtoTest {
    
    @Test
    @DisplayName("should create credentials dto")
    public void shouldCreateCredentialsDto() {
        CredentialsDto credentialsDto = new CredentialsDto(
            "Email",
            "Password"
        );

        assertDoesNotThrow(() -> DtoValidator.validate(credentialsDto));
    }

    static Stream<CredentialsDto> invalidValues() {
        return Stream.of(
            new CredentialsDto("", "Password"),
            new CredentialsDto(" ", "Password"),
            new CredentialsDto(null, "Password"),
            new CredentialsDto("Email", ""),
            new CredentialsDto("Email", " "),
            new CredentialsDto("Email", null),
            new CredentialsDto(null, null)
        );
    }

    @ParameterizedTest
    @MethodSource("invalidValues")
    @DisplayName("should throw exception when credentials dto is invalid")
    public void shouldThrowExceptionWhenCredentialsDtoIsInvalid(CredentialsDto invalidDto) {
        assertThrows(ValidationException.class, () -> DtoValidator.validate(invalidDto));
    }
}
