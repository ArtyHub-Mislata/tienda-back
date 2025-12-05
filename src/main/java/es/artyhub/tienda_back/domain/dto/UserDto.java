package es.artyhub.tienda_back.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;


public class UserDto {

        private Long id;

        @NotBlank(message = "El nombre no puede ser vacío")
        private String name;

        @NotBlank(message = "El email no puede ser vacío")
        private String email;

        @NotBlank(message = "La contraseña no puede ser vacía")
        private String password;

        @NotBlank(message = "El número de cuenta no puede ser vacío")
        @Size(min = 16, max = 16, message = "El número de cuenta debe tener 16 caracteres")
        private String nAccount;

        @NotNull(message = "La descripción no puede ser nula")
        private String description;

        @NotBlank(message = "La dirección no puede ser vacía")
        private String address;

        @NotNull(message = "La URL de la imagen del perfil no puede ser nula")
        private String imageProfileUrl;

        private List<ArtworkDto> artworks;

    public UserDto() {
    }

    public UserDto(Long id, String name, String email, String password, String nAccount, String description, String address, String imageProfileUrl, List<ArtworkDto> artworks) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.nAccount = nAccount;
        this.description = description;
        this.address = address;
        this.imageProfileUrl = imageProfileUrl;
        this.artworks = artworks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getnAccount() {
        return nAccount;
    }

    public void setnAccount(String nAccount) {
        this.nAccount = nAccount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImageProfileUrl() {
        return imageProfileUrl;
    }

    public void setImageProfileUrl(String imageProfileUrl) {
        this.imageProfileUrl = imageProfileUrl;
    }

    public List<ArtworkDto> getArtworks() {
        return artworks;
    }

    public void setArtworks(List<ArtworkDto> artworks) {
        this.artworks = artworks;
    }

}
