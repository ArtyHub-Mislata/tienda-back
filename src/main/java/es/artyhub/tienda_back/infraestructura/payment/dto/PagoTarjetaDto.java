package es.artyhub.tienda_back.infraestructura.payment.dto;

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
