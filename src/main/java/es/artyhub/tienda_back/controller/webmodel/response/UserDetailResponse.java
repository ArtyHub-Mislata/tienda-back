package es.artyhub.tienda_back.controller.webmodel.response;

import java.util.List;

public record UserDetailResponse(
    Long id,
    String name,
    String email,
    String nAccount,
    String description,
    String address,
    String imageProfileUrl,
    List<ArtworkSummaryResponse> artworks
) {
    
}
