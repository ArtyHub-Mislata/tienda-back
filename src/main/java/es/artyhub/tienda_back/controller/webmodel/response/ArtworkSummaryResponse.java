package es.artyhub.tienda_back.controller.webmodel.response;

import java.math.BigDecimal;

public record ArtworkSummaryResponse(
    Long id,
    String name,
    BigDecimal price
) {
    
}
