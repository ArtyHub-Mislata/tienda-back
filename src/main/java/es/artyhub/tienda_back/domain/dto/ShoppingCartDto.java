package es.artyhub.tienda_back.domain.dto;

import java.util.List;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import es.artyhub.tienda_back.domain.model.User;

public record ShoppingCartDto(
        Long id,

        @NotBlank(message = "El id del usuario no puede ser vacío")
        @Positive(message = "El id del usuario debe ser mayor a 0")
        Long userId,

        @NotBlank(message = "La lista de detalles no puede ser vacía")
        List<DetailDto> details,

        @NotBlank(message = "El método de pago no puede ser vacío")
        PayMethodDto payMethodDto,

        @NotBlank(message = "El total no puede ser vacío")
        @Positive(message = "El total debe ser mayor a 0")
        double total,

        @NotBlank(message = "El estado no puede ser vacío")
        String state) {

                public User findUserById(Long userId) {
                        return new User(userId);
                }
}
