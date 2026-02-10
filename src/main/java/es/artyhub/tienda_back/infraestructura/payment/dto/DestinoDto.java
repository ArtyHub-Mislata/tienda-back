package es.artyhub.tienda_back.infraestructura.payment.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DestinoDto(
    @NotNull
    @Size(min = 24, max = 24)
    String iban) {
}
