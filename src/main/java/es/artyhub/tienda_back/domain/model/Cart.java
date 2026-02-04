package es.artyhub.tienda_back.domain.model;

import java.util.List;

public class Cart {

    private Long id;
    private List<CartItem> cartItems;
    private User user;

    public Cart() {
    }

    public Cart(Long id, List<CartItem> cartItems, User user) {
        this.id = id;
        this.user = user;
        this.cartItems = cartItems;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
