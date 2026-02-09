package es.artyhub.tienda_back.domain.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;

public class CartDto {

    private Long id;

    @NotNull(message = "La lista de detalles no puede ser vacía")
    private List<CartItemDto> cartItems;

    @NotNull(message = "El carrito tiene que tener usuario")
    private UserDto user;

    public CartDto() {
    }

    public CartDto(Long id, List<CartItemDto> details, UserDto user) {
        this.id = id;
        this.cartItems = details;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public List<CartItemDto> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemDto> cartItems) {
        this.cartItems = cartItems;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
