package es.artyhub.tienda_back.domain.dto;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import es.artyhub.tienda_back.domain.exception.ValidationException;
import es.artyhub.tienda_back.domain.validation.DtoValidator;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserDtoTest {
    
    @Test
    @DisplayName("Create UserDto with valid data should not throw ValidationException")
    void createUserDto_WithValidData_ShouldNotThrowValidationException() {
        UserDto userDto = new UserDto(1L, "name", "email", "password", "nAccount", "description", "address", "imageUrl", List.of(new ArtworkDto(1L, "name", "description", "image", new BigDecimal(1.0), 1, new CategoryDto(1L, "name"))));

        assertDoesNotThrow(() -> DtoValidator.validate(userDto));
    }

    static Stream<UserDto> invalidUsers() {
        return Stream.of(
            new UserDto(1L, null, "email", "password", "****************", "description", "address", "imageUrl", List.of(new ArtworkDto(1L, "name", "description", "image", new BigDecimal(1.0), 1, new CategoryDto(1L, "name")))),
            new UserDto(1L, "", "email", "password", "****************", "description", "address", "imageUrl", List.of(new ArtworkDto(1L, "name", "description", "image", new BigDecimal(1.0), 1, new CategoryDto(1L, "name")))),
            new UserDto(1L, " ", "email", "password", "****************", "description", "address", "imageUrl", List.of(new ArtworkDto(1L, "name", "description", "image", new BigDecimal(1.0), 1, new CategoryDto(1L, "name")))),
            new UserDto(1L, "name", null, "password", "****************", "description", "address", "imageUrl", List.of(new ArtworkDto(1L, "name", "description", "image", new BigDecimal(1.0), 1, new CategoryDto(1L, "name")))),
            new UserDto(1L, "name", "", "password", "****************", "description", "address", "imageUrl", List.of(new ArtworkDto(1L, "name", "description", "image", new BigDecimal(1.0), 1, new CategoryDto(1L, "name")))),
            new UserDto(1L, "name", " ", "password", "****************", "description", "address", "imageUrl", List.of(new ArtworkDto(1L, "name", "description", "image", new BigDecimal(1.0), 1, new CategoryDto(1L, "name")))),
            new UserDto(1L, "name", "email", null, "****************", "description", "address", "imageUrl", List.of(new ArtworkDto(1L, "name", "description", "image", new BigDecimal(1.0), 1, new CategoryDto(1L, "name")))),
            new UserDto(1L, "name", "email", "", "****************", "description", "", "imageUrl", List.of(new ArtworkDto(1L, "name", "description", "image", new BigDecimal(1.0), 1, new CategoryDto(1L, "name")))),
            new UserDto(1L, "name", "email", " ", "****************", "description", "address", "imageUrl", List.of(new ArtworkDto(1L, "name", "description", "image", new BigDecimal(1.0), 1, new CategoryDto(1L, "name")))),
            new UserDto(1L, "name", "email", "password", null, "description", "address", "imageUrl", List.of(new ArtworkDto(1L, "name", "description", "image", new BigDecimal(1.0), 1, new CategoryDto(1L, "name")))),
            new UserDto(1L, "name", "email", "password", "", "description", "address", "imageUrl", List.of(new ArtworkDto(1L, "name", "description", "image", new BigDecimal(1.0), 1, new CategoryDto(1L, "name")))),
            new UserDto(1L, "name", "email", "password", " ", "description", "address", "imageUrl", List.of(new ArtworkDto(1L, "name", "description", "image", new BigDecimal(1.0), 1, new CategoryDto(1L, "name")))),
            new UserDto(1L, "name", "email", "password", "****", "description", "address", "imageUrl", List.of(new ArtworkDto(1L, "name", "description", "image", new BigDecimal(1.0), 1, new CategoryDto(1L, "name")))),
            new UserDto(1L, "name", "email", "password", "*****************", "description", "address", "imageUrl", List.of(new ArtworkDto(1L, "name", "description", "image", new BigDecimal(1.0), 1, new CategoryDto(1L, "name")))),
            new UserDto(1L, "name", "email", "password", "****************", null, "address", "imageUrl", List.of(new ArtworkDto(1L, "name", "description", "image", new BigDecimal(1.0), 1, new CategoryDto(1L, "name")))),
            new UserDto(1L, "name", "email", "password", "****************", "description", null, "imageUrl", List.of(new ArtworkDto(1L, "name", "description", "image", new BigDecimal(1.0), 1, new CategoryDto(1L, "name")))),
            new UserDto(1L, "name", "email", "password", "****************", "description", "", "imageUrl", List.of(new ArtworkDto(1L, "name", "description", "image", new BigDecimal(1.0), 1, new CategoryDto(1L, "name")))),
            new UserDto(1L, "name", "email", "password", "****************", "description", " ", "imageUrl", List.of(new ArtworkDto(1L, "name", "description", "image", new BigDecimal(1.0), 1, new CategoryDto(1L, "name")))),
            new UserDto(1L, "name", "email", "password", "****************", "description", "address", null, List.of(new ArtworkDto(1L, "name", "description", "image", new BigDecimal(1.0), 1, new CategoryDto(1L, "name"))))
        );
    }

    @ParameterizedTest
    @MethodSource("invalidUsers")
    @DisplayName("Create UserDto with invalid data should throw ValidationException")
    void createUserDto_WithInvalidData_ShouldThrowValidationException(UserDto userDto) {
        assertThrows(ValidationException.class, () -> DtoValidator.validate(userDto));
    }
}
