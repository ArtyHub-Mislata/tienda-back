package es.artyhub.tienda_back.domain.model;

public class CartItem {

    private Long id;
    private Long quantity;
    private Cart cart;
    private Artwork artwork;

    public CartItem() {
    }

    public CartItem(Long id, Long quantity, Cart cart, Artwork artwork) {
        this.id = id;
        this.quantity = quantity;
        this.cart = cart;
        this.artwork = artwork;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Artwork getArtwork() {
        return artwork;
    }

    public void setArtwork(Artwork artwork) {
        this.artwork = artwork;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
