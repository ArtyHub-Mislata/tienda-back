package es.artyhub.tienda_back.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record PayMethodDto(
        Long id,

        @NotBlank(message = "El número de la tarjeta no puede ser vacío")
        @Size(min = 16, max = 16, message = "El número de la tarjeta debe tener 16 dígitos")
        String nTarget,

        @NotBlank(message = "La fecha de expiración no puede ser vacía")
        String dateExpiration,

        @NotBlank(message = "El CVV no puede ser vacío")
        @Size(min = 3, max = 4, message = "El CVV debe tener entre 3 y 4 dígitos")
        @Positive(message = "El CVV debe ser mayor a 0")
        String cvv) {

}
