package es.artyhub.tienda_back.infraestructura.dto;

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
import es.artyhub.tienda_back.infraestructura.payment.dto.PagoDto;

public class PagoDtoTest {
    
    @Test
    @DisplayName("should create pago dto")
    public void shouldCreatePagoDto() {
        PagoDto pagoDto = new PagoDto(
            new BigDecimal(10),
            "concepto"
        );

        assertDoesNotThrow(() -> DtoValidator.validate(pagoDto));
    }

    static Stream<PagoDto> invalidValues() {
        return Stream.of(
            new PagoDto(new BigDecimal(0), "concepto"),
            new PagoDto(new BigDecimal(-1), "concepto"),
            new PagoDto(null, "concepto")
        );
    }

    @ParameterizedTest
    @MethodSource("invalidValues")
    @DisplayName("should throw exception when pago dto is invalid")
    public void shouldThrowExceptionWhenPagoDtoIsInvalid(PagoDto invalidDto) {
        assertThrows(ValidationException.class, () -> DtoValidator.validate(invalidDto));
    }
}
