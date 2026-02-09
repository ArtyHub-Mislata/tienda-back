package es.artyhub.tienda_back.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class CardDto {



        @JsonProperty("nTarget")
        @NotBlank(message = "El número de la tarjeta no puede ser vacío")
        @Size(min = 16, max = 16, message = "El número de la tarjeta debe tener 16 dígitos")
        private String nTarget;

        @NotBlank(message = "La fecha de expiración no puede ser vacía")
        private String dateExpiration;

        @NotBlank(message = "El CVV no puede ser vacío")
        @Size(min = 3, max = 4, message = "El CVV debe tener entre 3 y 4 dígitos")
        @Positive(message = "El CVV debe ser mayor a 0")
        private String cvv;

        @NotBlank(message = "El nombre del titular no puede ser vacío")
        private String holderName;

    public CardDto() {
    }

    public CardDto( String nTarget, String dateExpiration, String cvv, String holderName) {

        this.nTarget = nTarget;
        this.dateExpiration = dateExpiration;
        this.cvv = cvv;
        this.holderName = holderName;
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

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }
}
