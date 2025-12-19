package es.artyhub.tienda_back.domain.model;

import java.math.BigDecimal;

public class Detail {

    private Long id;
    private int quantity;
    private BigDecimal price;

    public Detail() {
    }

    public Detail(Long id, int quantity, BigDecimal price) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
