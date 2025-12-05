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

class ArtworkDtoTest {
    
    @Test
    @DisplayName("Create ArtworkDto with valid data should not throw ValidationException")
    void createArtworkDto_WithValidData_ShouldNotThrowValidationException() {
        ArtworkDto artworkDto = new ArtworkDto(
            1L,
            "name",
            "description",
            "image",
            1.0,
            1,
            new CategoryDto(1L, "name")
        );

        assertDoesNotThrow(() -> DtoValidator.validate(artworkDto));
    }

    static Stream<ArtworkDto> invalidArtworks() {
        return Stream.of(
            new ArtworkDto( 1L, null, "description", "image", 1.0, 1, new CategoryDto(1L, "name")),
            new ArtworkDto( 1L, "", "description", "image", 1.0, 1, new CategoryDto(1L, "name")),
            new ArtworkDto( 1L, " ", "description", "image", 1.0, 1, new CategoryDto(1L, "name")),
            new ArtworkDto( 1L, "name", null, "image", 1.0, 1, new CategoryDto(1L, "name")),
            new ArtworkDto( 1L, "name", "description", null, 1.0, 1, new CategoryDto(1L, "name")),
            new ArtworkDto( 1L, "name", "description", "", 1.0, 1, new CategoryDto(1L, "name")),
            new ArtworkDto( 1L, "name", "description", " ", 1.0, 1, new CategoryDto(1L, "name")),
            new ArtworkDto( 1L, "name", "description", "image", null, 1, new CategoryDto(1L, "name")),
            new ArtworkDto( 1L, "name", "description", "image", -1.0, 1, new CategoryDto(1L, "name")),
            new ArtworkDto( 1L, "name", "description", "image", 0.0, 1, new CategoryDto(1L, "name")),
            new ArtworkDto( 1L, "name", "description", "image", 1.0, null, new CategoryDto(1L, "name")),
            new ArtworkDto( 1L, "name", "description", "image", 1.0, 0, new CategoryDto(1L, "name")),
            new ArtworkDto( 1L, "name", "description", "image", 1.0, -1, new CategoryDto(1L, "name")),
            new ArtworkDto( 1L, "name", "description", "image", 1.0, 1, new CategoryDto(null, null)),
            new ArtworkDto( 1L, "name", "description", "image", 1.0, 1, new CategoryDto(1L, "")),
            new ArtworkDto( 1L, "name", "description", "image", 1.0, 1, new CategoryDto(1L, " "))
        );
    }

    @ParameterizedTest
    @MethodSource("invalidArtworks")
    @DisplayName("Create ArtworkDto with invalid data should throw ValidationException")
    void createArtworkDto_WithInvalidData_ShouldThrowValidationException(ArtworkDto artworkDto) {
        assertThrows(ValidationException.class, () -> DtoValidator.validate(artworkDto));
    }
}
