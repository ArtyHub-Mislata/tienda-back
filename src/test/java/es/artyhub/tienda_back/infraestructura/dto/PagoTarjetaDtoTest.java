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
import es.artyhub.tienda_back.infraestructura.payment.dto.AutorizacionDto;
import es.artyhub.tienda_back.infraestructura.payment.dto.DestinoDto;
import es.artyhub.tienda_back.infraestructura.payment.dto.PagoDto;
import es.artyhub.tienda_back.infraestructura.payment.dto.PagoTarjetaDto;
import es.artyhub.tienda_back.infraestructura.payment.dto.OrigenDto;

public class PagoTarjetaDtoTest {
    
    @Test
    @DisplayName("should create pago tarjeta dto")
    public void shouldCreatePagoTarjetaDto() {
        PagoTarjetaDto pagoTarjetaDto = new PagoTarjetaDto(
            new AutorizacionDto("login", "api_token"),
            new OrigenDto("1234567890123456", "12/12", "123", "Juan Doe"),
            new DestinoDto("ES1234567890123456789012"),
            new PagoDto(new BigDecimal(10), "concepto")
        );

        assertDoesNotThrow(() -> DtoValidator.validate(pagoTarjetaDto));
    }

    static Stream<PagoTarjetaDto> invalidValues() {
        return Stream.of(
            new PagoTarjetaDto(null, new OrigenDto("1234567890123456", "12/12", "123", "Juan Doe"), new DestinoDto("ES1234567890123456789012"), new PagoDto(new BigDecimal(10), "concepto")),
            new PagoTarjetaDto(new AutorizacionDto("login", "api_token"), null, new DestinoDto("ES1234567890123456789012"), new PagoDto(new BigDecimal(10), "concepto")),
            new PagoTarjetaDto(new AutorizacionDto("login", "api_token"), new OrigenDto("1234567890123456", "12/12", "123", "Juan Doe"), null, new PagoDto(new BigDecimal(10), "concepto")),
            new PagoTarjetaDto(new AutorizacionDto("login", "api_token"), new OrigenDto("1234567890123456", "12/12", "123", "Juan Doe"), new DestinoDto("ES1234567890123456789012"), null),
            new PagoTarjetaDto(null, null, null, null)
        );
    }

    @ParameterizedTest
    @MethodSource("invalidValues")
    @DisplayName("should throw exception when pago tarjeta dto is invalid")
    public void shouldThrowExceptionWhenPagoTarjetaDtoIsInvalid(PagoTarjetaDto invalidDto) {
        assertThrows(ValidationException.class, () -> DtoValidator.validate(invalidDto));
    }
}
