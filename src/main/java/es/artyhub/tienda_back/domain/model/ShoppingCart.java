package es.artyhub.tienda_back.domain.model;

import java.util.List;

public class ShoppingCart {

    private Long id;
    private List<CartItem> cartItems;

    public ShoppingCart() {
    }

    public ShoppingCart(Long id, List<CartItem> cartItems) {
        this.id = id;

        this.cartItems = cartItems;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CartItem> getDetails() {
        return cartItems;
    }

    public void setDetails(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }


}
