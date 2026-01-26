package es.artyhub.tienda_back.domain.dto;

import jakarta.validation.constraints.NotBlank;

public class RegisterDto {
    
    @NotBlank(message = "El email no puede ser vacío")
    private String email;
    
    @NotBlank(message = "La contraseña no puede ser vacía")
    private String password;

    @NotBlank(message = "El nombre no puede ser vacío")
    private String name;

    private String description;

    public RegisterDto() {
    }

    public RegisterDto(String email, String password, String name, String description) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.description = description;
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
}
