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

public class CardDtoTest {
    
    @Test
    @DisplayName("should create card dto")
    public void shouldCreateCardDto() {
        CardDto cardDto = new CardDto(
            1L,
            "1234567890123456",
            "12/12",
            "123",
            "Juan Martinez García"
        );

        assertDoesNotThrow(() -> DtoValidator.validate(cardDto));
    }

    static Stream<CardDto> invalidValues() {
        return Stream.of(
            new CardDto(1L, "", "12/12", "123", "Juan Martinez García"),
            new CardDto(1L, " ", "12/12", "123", "Juan Martinez García"),
            new CardDto(1L, null, "12/12", "123", "Juan Martinez García"),
            new CardDto(1L, "1234", "12/12", "123", "Juan Martinez García"),
            new CardDto(1L, "123456789012345678", "12/12", "123", "Juan Martinez García"),
            new CardDto(1L, "123456790123456", "", "123", "Juan Martinez García"),
            new CardDto(1L, "123456790123456", " ", "123", "Juan Martinez García"),
            new CardDto(1L, "123456790123456", null, "123", "Juan Martinez García"),
            new CardDto(1L, "123456790123456", "12/12", "", "Juan Martinez García"),
            new CardDto(1L, "123456790123456", "12/12", " ", "Juan Martinez García"),
            new CardDto(1L, "123456790123456", "12/12", null, "Juan Martinez García"),
            new CardDto(1L, "123456790123456", "12/12", "12", "Juan Martinez García"),
            new CardDto(1L, "123456790123456", "12/12", "12345", "Juan Martinez García"),
            new CardDto(1L, "123456790123456", "12/12", "000", "Juan Martinez García"),
            new CardDto(1L, "123456790123456", "12/12", "-123", "Juan Martinez García"),
            new CardDto(1L, "123456790123456", "12/12", "123", ""),
            new CardDto(1L, "123456790123456", "12/12", "123", " "),
            new CardDto(1L, "123456790123456", "12/12", "123", null),
            new CardDto(1L, null, null, null, null)
        );
    }

    @ParameterizedTest
    @MethodSource("invalidValues")
    @DisplayName("should throw exception when card dto is invalid")
    public void shouldThrowExceptionWhenCardDtoIsInvalid(CardDto invalidDto) {
        assertThrows(ValidationException.class, () -> DtoValidator.validate(invalidDto));
    }
}
