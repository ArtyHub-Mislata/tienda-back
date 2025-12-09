package es.artyhub.tienda_back.controller.webmodel.request;

import java.math.BigDecimal;

public record ArtworkRequest(
    String name,
    String description,
    String imageUrl,
    BigDecimal price,
    CategoryRequest categoryRequest,
    UserRequest userRequest
) {
    
}
