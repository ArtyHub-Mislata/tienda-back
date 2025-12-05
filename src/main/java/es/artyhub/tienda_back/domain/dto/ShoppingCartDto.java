package es.artyhub.tienda_back.domain.dto;

import java.util.List;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import es.artyhub.tienda_back.domain.model.User;

public class ShoppingCartDto {

        private Long id;

        @NotBlank(message = "El id del usuario no puede ser vacío")
        @Positive(message = "El id del usuario debe ser mayor a 0")
        private Long userId;

        @NotBlank(message = "La lista de detalles no puede ser vacía")
        private List<DetailDto> details;

        @NotBlank(message = "El método de pago no puede ser vacío")
        private PayMethodDto payMethodDto;

        @NotBlank(message = "El total no puede ser vacío")
        @Positive(message = "El total debe ser mayor a 0")
        private BigDecimal total;

        @NotBlank(message = "El estado no puede ser vacío")
        private String state;

    public ShoppingCartDto() {
    }

    public ShoppingCartDto(Long id, Long userId, List<DetailDto> details, PayMethodDto payMethodDto, BigDecimal total, String state) {
        this.id = id;
        this.userId = userId;
        this.details = details;
        this.payMethodDto = payMethodDto;
        this.total = total;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<DetailDto> getDetails() {
        return details;
    }

    public void setDetails(List<DetailDto> details) {
        this.details = details;
    }

    public PayMethodDto getPayMethodDto() {
        return payMethodDto;
    }

    public void setPayMethodDto(PayMethodDto payMethodDto) {
        this.payMethodDto = payMethodDto;
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

    public User findUserById(Long userId) {
        return new User(userId);
    }
}
