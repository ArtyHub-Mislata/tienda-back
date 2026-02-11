package es.artyhub.tienda_back.domain.dto;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import es.artyhub.tienda_back.domain.exception.ValidationException;
import es.artyhub.tienda_back.domain.validation.DtoValidator;

public class ArtworkDtoTest {
    
    @Test
    @DisplayName("should create artwork dto")
    public void shouldCreateArtworkDto() {
        ArtworkDto artworkDto = new ArtworkDto(
            1L,
            "name",
            "description",
            "image",
            new BigDecimal(1),
            new CategoryDto(),
            new UserDto(),
            1L
        );

        assertDoesNotThrow(() -> DtoValidator.validate(artworkDto));
    }

    static Stream<ArtworkDto> invalidValues() {
        return Stream.of(
            new ArtworkDto(1L, "", "description", "image", new BigDecimal(1), new CategoryDto(), new UserDto(), 1L),
            new ArtworkDto(1L, " ", "description", "image", new BigDecimal(1), new CategoryDto(), new UserDto(), 1L),
            new ArtworkDto(1L, null, "description", "image", new BigDecimal(1), new CategoryDto(), new UserDto(), 1L),
            new ArtworkDto(1L, "name", null, "image", new BigDecimal(1), new CategoryDto(), new UserDto(), 1L),
            new ArtworkDto(1L, "name", "description", "", new BigDecimal(1), new CategoryDto(), new UserDto(), 1L),
            new ArtworkDto(1L, "name", "description", " ", new BigDecimal(1), new CategoryDto(), new UserDto(), 1L),
            new ArtworkDto(1L, "name", "description", null, new BigDecimal(1), new CategoryDto(), new UserDto(), 1L),
            new ArtworkDto(1L, "name", "description", "image", null, new CategoryDto(), new UserDto(), 1L),
            new ArtworkDto(1L, "name", "description", "image", new BigDecimal(-1), new CategoryDto(), new UserDto(), 1L),
            new ArtworkDto(1L, "name", "description", "image", new BigDecimal(0), new CategoryDto(), new UserDto(), 1L),
            new ArtworkDto(1L, "name", "description", "image", new BigDecimal(1), null, new UserDto(), 1L),
            new ArtworkDto(1L, "name", "description", "image", new BigDecimal(1), new CategoryDto(), null, 1L),
            new ArtworkDto(1L, "name", "description", "image", new BigDecimal(1), new CategoryDto(), new UserDto(), 0L),
            new ArtworkDto(1L, "name", "description", "image", new BigDecimal(1), new CategoryDto(), new UserDto(), -1L),
            new ArtworkDto(1L, null, null, null, null, null, null, 0L)
        );
    }

    @ParameterizedTest
    @MethodSource("invalidValues")
    @DisplayName("should throw exception when artwork dto is invalid")
    public void shouldThrowExceptionWhenArtworkDtoIsInvalid(ArtworkDto invalidDto) {
        assertThrows(ValidationException.class, () -> DtoValidator.validate(invalidDto));
    }
}
