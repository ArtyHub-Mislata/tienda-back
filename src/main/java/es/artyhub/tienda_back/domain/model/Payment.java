package es.artyhub.tienda_back.domain.model;

import java.math.BigDecimal;

import es.artyhub.tienda_back.domain.enums.Status;

public class Payment {

    private Long id;
    private Card card;
    private String concept;
    private BigDecimal amount;
    private Status status = Status.PENDING;

    public Payment() {
    }

    public Payment(Long id, Card card, String concept, BigDecimal amount, Status status) {
        this.id = id;
        this.card = card;
        this.concept = concept;
        this.amount = amount;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
