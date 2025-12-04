package es.artyhub.tienda_back.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ArtworkDto(

        Long id,

        @NotBlank(message = "El nombre no puede ser vacío")
        String name,

        @NotNull(message = "La descripción no puede ser nula")
        String description,

        @NotBlank(message = "La imagen no puede ser vacía")
        String image,

        @NotBlank(message = "El precio no puede ser vacío")
        @Positive(message = "El precio debe ser mayor a 0")
        Double price,

        @NotBlank(message = "El stock no puede ser vacío")
        @Positive(message = "El stock debe ser mayor a 0")
        int stock,
        
        @NotBlank(message = "La categoría no puede ser vacía")
        CategoryDto categoryDto) {

}
