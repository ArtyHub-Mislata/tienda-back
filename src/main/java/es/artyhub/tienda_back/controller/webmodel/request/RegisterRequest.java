package es.artyhub.tienda_back.controller.webmodel.request;

import jakarta.validation.constraints.NotBlank;

public class RegisterRequest {
    
    @NotBlank(message = "El email no puede ser vacío")
    private String email;
    
    @NotBlank(message = "La contraseña no puede ser vacía")
    private String password;

    @NotBlank(message = "El nombre no puede ser vacío")
    private String name;

    private String description;

    private String imageProfileUrl;

    private String address;
    public RegisterRequest() {
    }

    public RegisterRequest(String email, String password, String name, String description, String imageProfileUrl, String address) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.description = description;
        this.imageProfileUrl = imageProfileUrl;
        this.address = address;
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

    public String getImageProfileUrl() {
        return imageProfileUrl;
    }

    public void setImageProfileUrl(String imageProfileUrl) {
        this.imageProfileUrl = imageProfileUrl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
