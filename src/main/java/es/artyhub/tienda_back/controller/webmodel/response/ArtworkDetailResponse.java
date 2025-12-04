package es.artyhub.tienda_back.controller.webmodel.response;

public record ArtworkDetailResponse(
    Long id,
    String name,
    String description,
    String imageUrl,
    double price,
    int stock,
    CategoryDetailResponse categoryDetailResponse
) {
    
}
