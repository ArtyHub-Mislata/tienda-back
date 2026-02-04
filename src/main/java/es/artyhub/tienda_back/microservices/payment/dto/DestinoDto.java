package es.artyhub.tienda_back.microservices.payment.dto;

import jakarta.validation.constraints.NotNull;

public record DestinoDto(
    @NotNull
    String iban) {
}
