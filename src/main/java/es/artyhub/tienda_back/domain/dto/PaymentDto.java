package es.artyhub.tienda_back.domain.dto;

import java.math.BigDecimal;

import es.artyhub.tienda_back.domain.enums.Status;
import jakarta.validation.constraints.NotNull;

public class PaymentDto {

    private Long id;

    @NotNull(message = "La tarjeta no puede ser nula")
    private CardDto cardDto;
    
    private String concept;

    @NotNull(message = "El importe no puede ser nulo")
    private BigDecimal amount;
    
    private Status status = Status.PENDING;

    public PaymentDto() {
    }

    public PaymentDto(Long id, CardDto cardDto, String concept, BigDecimal amount, Status status) {
        this.id = id;
        this.cardDto = cardDto;
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

    public CardDto getCardDto() {
        return cardDto;
    }

    public void setCardDto(CardDto cardDto) {
        this.cardDto = cardDto;
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
