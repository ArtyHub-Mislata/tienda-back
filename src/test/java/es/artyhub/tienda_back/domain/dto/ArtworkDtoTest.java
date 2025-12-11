package es.artyhub.tienda_back.domain.dto;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import es.artyhub.tienda_back.domain.exception.ValidationException;
import es.artyhub.tienda_back.domain.validation.DtoValidator;
import java.math.BigDecimal;
import es.artyhub.tienda_back.domain.enums.UserRole;

class ArtworkDtoTest {
    
    @Test
    @DisplayName("Create ArtworkDto with valid data should not throw ValidationException")
    void createArtworkDto_WithValidData_ShouldNotThrowValidationException() {
        ArtworkDto artworkDto = new ArtworkDto(
            1L,
            "name",
            "description",
            "image",
            new BigDecimal(1.0),
            new CategoryDto(1L, "name"),
            new UserDto(1L, "name", "email", "password", "nAccount", "description", "address", "imageProfileUrl", List.of(), UserRole.ADMIN)
        );

        assertDoesNotThrow(() -> DtoValidator.validate(artworkDto));
    }

    static Stream<ArtworkDto> invalidArtworks() {
        return Stream.of(
            new ArtworkDto( 1L, null, "description", "image", new BigDecimal(1.0), new CategoryDto(1L, "name"), new UserDto(1L, "name", "email", "password", "nAccount", "description", "address", "imageProfileUrl", List.of(), UserRole.ADMIN)),
            new ArtworkDto( 1L, "", "description", "image", new BigDecimal(1.0), new CategoryDto(1L, "name"), new UserDto(1L, "name", "email", "password", "nAccount", "description", "address", "imageProfileUrl", List.of(), UserRole.ADMIN)),
            new ArtworkDto( 1L, " ", "description", "image", new BigDecimal(1.0), new CategoryDto(1L, "name"), new UserDto(1L, "name", "email", "password", "nAccount", "description", "address", "imageProfileUrl", List.of(), UserRole.ADMIN)),
            new ArtworkDto( 1L, "name", null, "image", new BigDecimal(1.0), new CategoryDto(1L, "name"), new UserDto(1L, "name", "email", "password", "nAccount", "description", "address", "imageProfileUrl", List.of(), UserRole.ADMIN)),
            new ArtworkDto( 1L, "name", "description", null, new BigDecimal(1.0), new CategoryDto(1L, "name"), new UserDto(1L, "name", "email", "password", "nAccount", "description", "address", "imageProfileUrl", List.of(), UserRole.ADMIN)),
            new ArtworkDto( 1L, "name", "description", "", new BigDecimal(1.0), new CategoryDto(1L, "name"), new UserDto(1L, "name", "email", "password", "nAccount", "description", "address", "imageProfileUrl", List.of(), UserRole.ADMIN)),
            new ArtworkDto( 1L, "name", "description", " ", new BigDecimal(1.0), new CategoryDto(1L, "name"), new UserDto(1L, "name", "email", "password", "nAccount", "description", "address", "imageProfileUrl", List.of(), UserRole.ADMIN)),
            new ArtworkDto( 1L, "name", "description", "image", null, new CategoryDto(1L, "name"), new UserDto(1L, "name", "email", "password", "nAccount", "description", "address", "imageProfileUrl", List.of(), UserRole.ADMIN)),
            new ArtworkDto( 1L, "name", "description", "image", new BigDecimal(-1.0), new CategoryDto(1L, "name"), new UserDto(1L, "name", "email", "password", "nAccount", "description", "address", "imageProfileUrl", List.of(), UserRole.ADMIN)),
            new ArtworkDto( 1L, "name", "description", "image", new BigDecimal(0.0), new CategoryDto(1L, "name"), new UserDto(1L, "name", "email", "password", "nAccount", "description", "address", "imageProfileUrl", List.of(), UserRole.ADMIN)),
            new ArtworkDto( 1L, "name", "description", "image", new BigDecimal(1.0), new CategoryDto(null, null), new UserDto(1L, "name", "email", "password", "nAccount", "description", "address", "imageProfileUrl", List.of(), UserRole.ADMIN)),
            new ArtworkDto( 1L, "name", "description", "image", new BigDecimal(1.0), new CategoryDto(1L, ""), new UserDto(1L, "name", "email", "password", "nAccount", "description", "address", "imageProfileUrl", List.of(), UserRole.ADMIN)),
            new ArtworkDto( 1L, "name", "description", "image", new BigDecimal(1.0), new CategoryDto(1L, " "), new UserDto(1L, "name", "email", "password", "nAccount", "description", "address", "imageProfileUrl", List.of(), UserRole.ADMIN)),
            new ArtworkDto( 1L, "name", "description", "image", new BigDecimal(1.0), new CategoryDto(1L, "name"), new UserDto(null, null, null, null, null, null, null, null, null, null)),
            new ArtworkDto( 1L, "name", "description", "image", new BigDecimal(1.0), new CategoryDto(1L, "name"), new UserDto(1L, "", "email", "password", "nAccount", "description", "address", "imageProfileUrl", List.of(), UserRole.ADMIN)),
            new ArtworkDto( 1L, "name", "description", "image", new BigDecimal(1.0), new CategoryDto(1L, "name"), new UserDto(1L, " ", "email", "password", "nAccount", "description", "address", "imageProfileUrl", List.of(), UserRole.ADMIN)),
            new ArtworkDto( 1L, "name", "description", "image", new BigDecimal(1.0), new CategoryDto(1L, "name"), new UserDto(1L, "name", "", "password", "nAccount", "description", "address", "imageProfileUrl", List.of(), UserRole.ADMIN)),
            new ArtworkDto( 1L, "name", "description", "image", new BigDecimal(1.0), new CategoryDto(1L, "name"), new UserDto(1L, "name", " ", "password", "nAccount", "description", "address", "imageProfileUrl", List.of(), UserRole.ADMIN)),
            new ArtworkDto( 1L, "name", "description", "image", new BigDecimal(1.0), new CategoryDto(1L, "name"), new UserDto(1L, "name", "email", "", "nAccount", "description", "address", "imageProfileUrl", List.of(), UserRole.ADMIN)),
            new ArtworkDto( 1L, "name", "description", "image", new BigDecimal(1.0), new CategoryDto(1L, "name"), new UserDto(1L, "name", "email", " ", "nAccount", "description", "address", "imageProfileUrl", List.of(), UserRole.ADMIN)),
            new ArtworkDto( 1L, "name", "description", "image", new BigDecimal(1.0), new CategoryDto(1L, "name"), new UserDto(1L, "name", "email", "password", "", "description", "address", "imageProfileUrl", List.of(), UserRole.ADMIN)),
            new ArtworkDto( 1L, "name", "description", "image", new BigDecimal(1.0), new CategoryDto(1L, "name"), new UserDto(1L, "name", "email", "password", " ", "description", "address", "imageProfileUrl", List.of(), UserRole.ADMIN)),
            new ArtworkDto( 1L, "name", "description", "image", new BigDecimal(1.0), new CategoryDto(1L, "name"), new UserDto(1L, "name", "email", "password", "nAccount", "", "address", "imageProfileUrl", List.of(), UserRole.ADMIN)),
            new ArtworkDto( 1L, "name", "description", "image", new BigDecimal(1.0), new CategoryDto(1L, "name"), new UserDto(1L, "name", "email", "password", "nAccount", " ", "address", "imageProfileUrl", List.of(), UserRole.ADMIN)),
            new ArtworkDto( 1L, "name", "description", "image", new BigDecimal(1.0), new CategoryDto(1L, "name"), new UserDto(1L, "name", "email", "password", "nAccount", "description", "", "imageProfileUrl", List.of(), UserRole.ADMIN)),
            new ArtworkDto( 1L, "name", "description", "image", new BigDecimal(1.0), new CategoryDto(1L, "name"), new UserDto(1L, "name", "email", "password", "nAccount", "description", " ", "imageProfileUrl", List.of(), UserRole.ADMIN)),
            new ArtworkDto( 1L, "name", "description", "image", new BigDecimal(1.0), new CategoryDto(1L, "name"), new UserDto(1L, "name", "email", "password", "nAccount", "description", "address", "", List.of(), UserRole.ADMIN)),
            new ArtworkDto( 1L, "name", "description", "image", new BigDecimal(1.0), new CategoryDto(1L, "name"), new UserDto(1L, "name", "email", "password", "nAccount", "description", "address", " ", List.of(), UserRole.ADMIN)),
            new ArtworkDto( 1L, "name", "description", "image", new BigDecimal(1.0), new CategoryDto(1L, "name"), new UserDto(1L, "name", "email", "password", "nAccount", "description", "address", "imageProfileUrl", null, UserRole.ADMIN)),
            new ArtworkDto( 1L, "name", "description", "image", new BigDecimal(1.0), new CategoryDto(1L, "name"), new UserDto(1L, "name", "email", "password", "nAccount", "description", "address", "imageProfileUrl", List.of(), null))
        );
    }

    @ParameterizedTest
    @MethodSource("invalidArtworks")
    @DisplayName("Create ArtworkDto with invalid data should throw ValidationException")
    void createArtworkDto_WithInvalidData_ShouldThrowValidationException(ArtworkDto artworkDto) {
        assertThrows(ValidationException.class, () -> DtoValidator.validate(artworkDto));
    }
}
