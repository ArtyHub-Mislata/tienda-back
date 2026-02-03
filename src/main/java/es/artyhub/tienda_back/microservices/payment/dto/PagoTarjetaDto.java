package es.artyhub.tienda_back.microservices.payment.dto;

import jakarta.validation.constraints.NotNull;

public record PagoTarjetaDto(
    @NotNull
    AutorizacionDto autorizacion,
    @NotNull
    OrigenDto origen,
    @NotNull
    DestinoDto destino,
    @NotNull
    PagoDto pago) {
}
