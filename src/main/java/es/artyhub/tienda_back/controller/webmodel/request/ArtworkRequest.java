package es.artyhub.tienda_back.controller.webmodel.request;

import java.math.BigDecimal;

public record ArtworkRequest(
    Long id,
    String name,
    String description,
    String imageUrl,
    BigDecimal price,
    int stock,
    CategoryRequest categoryRequest
) {
    
}
