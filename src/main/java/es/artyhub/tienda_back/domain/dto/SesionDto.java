package es.artyhub.tienda_back.domain.dto;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SesionDto {
    @NotBlank(message = "El campo token no puede estar vacío")
    private String token;

    @NotNull(message = "El campo userId no puede ser nulo")
    private Long userId;

    @NotNull(message = "El campo createdAt no puede ser nulo")
    private Date createdAt;

    public SesionDto() {
    }

    public SesionDto(String token, Long userId, Date createdAt) {
        this.token = token;
        this.userId = userId;
        this.createdAt = createdAt;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
