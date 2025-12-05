package es.artyhub.tienda_back.controller.webmodel.request;

public record UserRequest(
    String name,
    String email,
    String nAccount,
    String description,
    String address,
    String imageProfileUrl,
    ArtworkRequest[] artworkRequests
) {
    
}
