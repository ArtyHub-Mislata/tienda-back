package es.artyhub.tienda_back.infraestructura.payment.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PagoDto(
    @NotNull
    @Positive 
    BigDecimal importe,
    String concepto) {
}
