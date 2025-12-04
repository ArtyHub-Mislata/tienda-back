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
            userDto.id(),
            userDto.name()
        );
    }

    public static UserDetailResponse fromUserDtoToUserDetailResponse(UserDto userDto) {
        if (userDto == null) {
            return null;
        }
        return new UserDetailResponse(
            userDto.id(),
            userDto.name(),
            userDto.email(),
            userDto.nAccount(),
            userDto.description(),
            userDto.address(),
            userDto.imageProfileUrl(),
            userDto.artworks().stream().map(ArtworkMapper::fromArtworkDtoToArtworkSummaryResponse).toList()
        );
    }
}
