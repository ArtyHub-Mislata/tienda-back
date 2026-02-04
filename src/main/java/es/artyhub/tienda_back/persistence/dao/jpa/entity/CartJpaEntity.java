package es.artyhub.tienda_back.persistence.dao.jpa.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
public class CartJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItemJpaEntity> cartItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private UserJpaEntity user;


    public UserJpaEntity getUser() {
        return user;
    }

    public CartJpaEntity() {
    }

    public CartJpaEntity(Long id, List<CartItemJpaEntity> cartItems, UserJpaEntity userJpaEntity) {
        this.id = id;
        this.cartItems = cartItems;
        this.user = userJpaEntity;

    }

    public void setUser(UserJpaEntity user) {
        this.user = user;
        if(user.getCart() != this){
            user.setCart(this);
        }
    }

    public List<CartItemJpaEntity> getCartItems() {
        return cartItems;
    }

    //Helper
    public void setCartItems(List<CartItemJpaEntity> cartItems) {
        this.cartItems = cartItems;
        for (CartItemJpaEntity cartItem : cartItems) {
            cartItem.setCart(this);
        }
    }
    public void addItem(CartItemJpaEntity item) {
        cartItems.add(item);
        item.setCart(this);
    }

    public void removeItem(CartItemJpaEntity item) {
        cartItems.remove(item);
        item.setCart(null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
