package es.artyhub.tienda_back.controller.webmodel.response;

import es.artyhub.tienda_back.domain.enums.UserRole;

public record UserSummaryResponse (
        Long id,
        String name,
        String email,
        String description,
        String address,
        String imageProfileUrl,
        UserRole role
){}
