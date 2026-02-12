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
import es.artyhub.tienda_back.domain.enums.Status;

public class PaymentDtoTest {
    
    @Test
    @DisplayName("should create payment dto")
    public void shouldCreatePaymentDto() {
        PaymentDto paymentDto = new PaymentDto(
            1L,
            new CardDto( "1234567890123456", "12/25", "123", "John Doe"),
            "Concept",
            new BigDecimal(100),
            Status.PENDING
        );

        assertDoesNotThrow(() -> DtoValidator.validate(paymentDto));
    }

    static Stream<PaymentDto> invalidValues() {
        return Stream.of(
            new PaymentDto(1L, null, "Concept", new BigDecimal(100), Status.PENDING),
            new PaymentDto(1L, new CardDto( "1234567890123456", "12/25", "123", "John Doe"), "Concept", null, Status.PENDING),
            new PaymentDto(1L, null, "Concept", null, Status.PENDING)
        );
    }

    @ParameterizedTest
    @MethodSource("invalidValues")
    @DisplayName("should throw exception when payment dto is invalid")
    public void shouldThrowExceptionWhenPaymentDtoIsInvalid(PaymentDto invalidDto) {
        assertThrows(ValidationException.class, () -> DtoValidator.validate(invalidDto));
    }
}
