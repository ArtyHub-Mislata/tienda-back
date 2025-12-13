package es.artyhub.tienda_back.domain.dto;

import jakarta.validation.constraints.NotBlank;

public class CredentialsDto {

    @NotBlank(message = "El email no puede ser vacío")
    private String email;
    
    @NotBlank(message = "La contraseña no puede ser vacía")
    private String password;

    public CredentialsDto() {
    }

    public CredentialsDto(String email, String password) {
        this.email = email;
        this.password = password;
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
}
