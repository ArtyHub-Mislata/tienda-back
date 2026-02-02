package es.artyhub.tienda_back.persistence.dao.jpa.entity;

import es.artyhub.tienda_back.domain.enums.UserRole;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "users")
public class UserJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private String description;
    private String address;
    @Column(name = "image_profile_url")
    private String imageProfileUrl;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private CartJpaEntity cart;

    public UserJpaEntity(Long id, String name, String email, String password, String description,
                         String address, String imageProfileUrl, UserRole role, CartJpaEntity cart) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.description = description;
        this.address = address;
        this.imageProfileUrl = imageProfileUrl;
        this.role = role;
        this.cart = cart;
    }

    public UserJpaEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImageProfileUrl() {
        return imageProfileUrl;
    }

    public void setImageProfileUrl(String imageProfileUrl) {
        this.imageProfileUrl = imageProfileUrl;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public CartJpaEntity getCart() {
        return cart;
    }

    public void setCart(CartJpaEntity cart) {
        this.cart = cart;
        if(cart.getUserJpaEntity() != this){
            cart.setUserJpaEntity(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof UserJpaEntity that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(email, that.email) && Objects.equals(password, that.password) && Objects.equals(description, that.description) && Objects.equals(address, that.address) && Objects.equals(imageProfileUrl, that.imageProfileUrl) && role == that.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password, description, address, imageProfileUrl, role);
    }
}
