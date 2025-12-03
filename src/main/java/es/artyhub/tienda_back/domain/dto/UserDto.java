package es.artyhub.tienda_back.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

public record UserDto(
        Long id,

        @NotBlank(message = "El nombre no puede ser vacío")
        String name,

        @NotBlank(message = "El email no puede ser vacío")
        String email,

        @NotBlank(message = "La contraseña no puede ser vacía")
        String password,

        @NotBlank(message = "El número de cuenta no puede ser vacío")
        @Size(min = 16, max = 16, message = "El número de cuenta debe tener 16 caracteres")
        String nAccount,

        @NotNull(message = "La descripción no puede ser nula")
        String description,

        @NotBlank(message = "La dirección no puede ser vacía")
        String address,

        @NotNull(message = "La URL de la imagen del perfil no puede ser nula")
        String imageProfileUrl,

        List<ArtworkDto> artworks) {
}
