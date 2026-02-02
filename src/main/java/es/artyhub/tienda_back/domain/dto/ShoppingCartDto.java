package es.artyhub.tienda_back.domain.dto;

import java.util.List;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public class ShoppingCartDto {

        private Long id;



        @NotBlank(message = "La lista de detalles no puede ser vacía")
        private List<CartItemDto> details;


    public ShoppingCartDto() {
    }

    public ShoppingCartDto(Long id, List<CartItemDto> details) {
        this.id = id;
        this.details = details;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public List<CartItemDto> getDetails() {
        return details;
    }

    public void setDetails(List<CartItemDto> details) {
        this.details = details;
    }


}
