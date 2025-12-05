package es.artyhub.tienda_back.controller.webmodel.response;

import java.math.BigDecimal;

public record ArtworkDetailResponse(
    Long id,
    String name,
    String description,
    String imageUrl,
    BigDecimal price,
    int stock,
    CategoryDetailResponse categoryDetailResponse
) {
    
}
