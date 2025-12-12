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
    @DisplayName("Create CredentialsDto with valid data should not throw ValidationException")
    void createCredentialsDto_WithValidData_ShouldNotThrowValidationException() {
        CredentialsDto credentialsDto = new CredentialsDto("email", "password");
        assertDoesNotThrow(() -> DtoValidator.validate(credentialsDto));
    }

    static Stream<CredentialsDto> invalidCredentials() {
        return Stream.of(
            new CredentialsDto(null, "password"),
            new CredentialsDto("", "password"),
            new CredentialsDto(" ", "password"),
            new CredentialsDto("email", null),
            new CredentialsDto("email", ""),
            new CredentialsDto("email", " ")
        );
    }

    @ParameterizedTest
    @MethodSource("invalidCredentials")
    @DisplayName("Create CredentialsDto with invalid data should throw ValidationException")
    void createCredentialsDto_WithInvalidData_ShouldThrowValidationException(CredentialsDto credentialsDto) {
        assertThrows(ValidationException.class, () -> DtoValidator.validate(credentialsDto));
    }
}
