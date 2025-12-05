package es.artyhub.tienda_back.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class PayMethodDto {

        private Long id;

        @NotBlank(message = "El número de la tarjeta no puede ser vacío")
        @Size(min = 16, max = 16, message = "El número de la tarjeta debe tener 16 dígitos")
        private String nTarget;

        @NotBlank(message = "La fecha de expiración no puede ser vacía")
        private String dateExpiration;

        @NotBlank(message = "El CVV no puede ser vacío")
        @Size(min = 3, max = 4, message = "El CVV debe tener entre 3 y 4 dígitos")
        @Positive(message = "El CVV debe ser mayor a 0")
        private String cvv;

    public PayMethodDto() {
    }

    public PayMethodDto(Long id, String nTarget, String dateExpiration, String cvv) {
        this.id = id;
        this.nTarget = nTarget;
        this.dateExpiration = dateExpiration;
        this.cvv = cvv;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getnTarget() {
        return nTarget;
    }

    public void setnTarget(String nTarget) {
        this.nTarget = nTarget;
    }

    public String getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(String dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
}
