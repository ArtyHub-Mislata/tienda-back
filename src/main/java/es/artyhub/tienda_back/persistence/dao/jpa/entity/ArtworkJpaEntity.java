package es.artyhub.tienda_back.persistence.dao.jpa.entity;

import es.artyhub.tienda_back.domain.model.Category;
import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name= "artworks")
public class ArtworkJpaEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private String description;
    @Column(name = "img_url")
    private String imageUrl;
    private BigDecimal price;
    private int stock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")  // Esto crea categoria_id en tabla producto
    private CategoryJpaEntity category;

    public ArtworkJpaEntity(Long id, String name, String description, String imageUrl, BigDecimal price, int stock, CategoryJpaEntity category) {
        Id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }

    public ArtworkJpaEntity() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public CategoryJpaEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryJpaEntity category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ArtworkJpaEntity that)) return false;
        return stock == that.stock && Objects.equals(Id, that.Id) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(imageUrl, that.imageUrl) && Objects.equals(price, that.price) && Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, name, description, imageUrl, price, stock, category);
    }
}
