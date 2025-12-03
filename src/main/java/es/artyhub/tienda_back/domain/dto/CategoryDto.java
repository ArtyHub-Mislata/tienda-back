package es.artyhub.tienda_back.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoryDto(
        Long id,

        @NotBlank(message = "El nombre no puede ser vacío")
        String name) {

}
