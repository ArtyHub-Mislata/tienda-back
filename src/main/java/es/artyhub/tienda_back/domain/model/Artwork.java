package es.artyhub.tienda_back.domain.model;

import es.artyhub.tienda_back.domain.dto.CategoryDto;

public class Artwork {

    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private double price;
    private int stock;
    private CategoryDto categoryDto;

    public Artwork() {
    }

    public Artwork(Long id, String name, String description, String imageUrl, double price, int stock,
            CategoryDto categoryDto) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
        this.stock = stock;
        this.categoryDto = categoryDto;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public CategoryDto getCategoryDto() {
        return categoryDto;
    }

    public void setCategoryDto(CategoryDto categoryDto) {
        this.categoryDto = categoryDto;
    }

}
