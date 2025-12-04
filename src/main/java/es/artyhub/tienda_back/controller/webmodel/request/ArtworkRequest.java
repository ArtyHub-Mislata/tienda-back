package es.artyhub.tienda_back.controller.webmodel.request;

public record ArtworkRequest(
    Long id,
    String name,
    String description,
    String imageUrl,
    double price,
    int stock,
    CategoryRequest categoryRequest
) {
    
}
