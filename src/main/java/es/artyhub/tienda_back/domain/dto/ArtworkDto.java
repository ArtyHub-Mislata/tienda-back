package es.artyhub.tienda_back.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public class ArtworkDto {

        private Long id;

        @NotBlank(message = "El nombre no puede ser vacío")
        private String name;

        @NotNull(message = "La descripción no puede ser nula")
        private String description;

        @NotBlank(message = "La imagen no puede ser vacía")
        private String image;

        @NotNull(message = "El precio no puede ser nulo")
        @Positive(message = "El precio debe ser mayor a 0")
        private BigDecimal price;
        
        @NotNull(message = "La categoría no puede ser vacía")
        private CategoryDto categoryDto;

        @NotNull
        private UserDto userDto;

    public ArtworkDto() {
    }

    public ArtworkDto(Long id, String name, String description, String image, BigDecimal price, CategoryDto categoryDto, UserDto userDto) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.categoryDto = categoryDto;
        this.userDto = userDto;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public CategoryDto getCategoryDto() {
        return categoryDto;
    }

    public void setCategoryDto(CategoryDto categoryDto) {
        this.categoryDto = categoryDto;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }
}
