package es.artyhub.tienda_back.controller.mapper;

import es.artyhub.tienda_back.controller.webmodel.response.UserDetailResponse;
import es.artyhub.tienda_back.controller.webmodel.response.UserSummaryResponse;
import es.artyhub.tienda_back.domain.dto.UserDto;

public class UserMapper {
    public static UserSummaryResponse fromUserDtoToUserSummaryResponse(UserDto userDto) {
        if (userDto == null) {
            return null;
        }
        return new UserSummaryResponse(
            userDto.getId(),
            userDto.getName()
        );
    }

    public static UserDetailResponse fromUserDtoToUserDetailResponse(UserDto userDto) {
        if (userDto == null) {
            return null;
        }
        return new UserDetailResponse(
            userDto.getId(),
            userDto.getName(),
            userDto.getEmail(),
            userDto.getnAccount(),
            userDto.getDescription(),
            userDto.getAddress(),
            userDto.getImageProfileUrl(),
            userDto.getArtworks().stream().map(ArtworkMapper::fromArtworkDtoToArtworkSummaryResponse).toList()
        );
    }
}
