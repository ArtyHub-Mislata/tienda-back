package es.artyhub.tienda_back.persistence.dao.jpa.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "carts")
public class CartJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private UserJpaEntity userJpaEntity;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItemJpaEntity> cartItems;

    public UserJpaEntity getUserJpaEntity() {
        return userJpaEntity;
    }

    public CartJpaEntity() {
    }

    public CartJpaEntity(Long id, UserJpaEntity userJpaEntity, List<CartItemJpaEntity> cartItems) {
        this.id = id;
        this.userJpaEntity = userJpaEntity;
        this.cartItems = cartItems;
    }

    public void setUserJpaEntity(UserJpaEntity userJpaEntity) {
        this.userJpaEntity = userJpaEntity;
        if(userJpaEntity.getCart() != this){
            userJpaEntity.setCart(this);
        }
    }

    public List<CartItemJpaEntity> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemJpaEntity> cartItems) {
        this.cartItems = cartItems;
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
