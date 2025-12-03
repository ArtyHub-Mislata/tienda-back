package es.artyhub.tienda_back.domain.mapper;

import es.artyhub.tienda_back.domain.dto.DetailDto;
import es.artyhub.tienda_back.domain.model.Detail;

public class DetailMapper {
    private static DetailMapper instance;

    public DetailMapper() {
    }

    public static DetailMapper getInstance() {
        if (instance == null) {
            instance = new DetailMapper();
        }
        return instance;
    }

    public Detail fromDetailDtoToDetail(DetailDto detailDto) {
        if (detailDto == null) {
            return null;
        }
        return new Detail(
            detailDto.id(),
            detailDto.quantity(),
            detailDto.price()
        );
    }

    public DetailDto fromDetailToDetailDto(Detail detail) {
        if (detail == null) {
            return null;
        }
        return new DetailDto(
            detail.getId(),
            detail.getQuantity(),
            detail.getPrice()
        );
    }
}
