package es.artyhub.tienda_back.domain.model;

import java.util.List;
import java.math.BigDecimal;

public class ShoppingCart {

    private Long id;
    private User user;
    private List<Detail> details;
    private PayMethod payMethod;
    private BigDecimal total;
    private String state;

    public ShoppingCart() {
    }

    public ShoppingCart(Long id, User user, List<Detail> details, PayMethod payMethod, BigDecimal total, String state) {
        this.id = id;
        this.user = user;
        this.details = details;
        this.payMethod = payMethod;
        this.total = total;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Detail> getDetails() {
        return details;
    }

    public void setDetails(List<Detail> details) {
        this.details = details;
    }

    public PayMethod getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(PayMethod payMethod) {
        this.payMethod = payMethod;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
