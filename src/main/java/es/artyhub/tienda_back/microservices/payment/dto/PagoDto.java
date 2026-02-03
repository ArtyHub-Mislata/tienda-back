package es.artyhub.tienda_back.microservices.payment.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Positive;

public record PagoDto(
    @Positive BigDecimal importe,
    String concepto) {
}
