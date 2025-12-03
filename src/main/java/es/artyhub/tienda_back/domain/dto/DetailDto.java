package es.artyhub.tienda_back.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DetailDto(
        Long id,

        @NotNull(message = "La cantidad no puede ser nula")
        @Positive(message = "La cantidad debe ser mayor a 0")
        int quantity,

        @NotBlank(message = "El precio no puede ser vacío")
        @Positive(message = "El precio debe ser mayor a 0")
        double price) {
}
