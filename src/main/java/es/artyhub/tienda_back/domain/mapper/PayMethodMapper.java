package es.artyhub.tienda_back.domain.mapper;

import es.artyhub.tienda_back.domain.dto.PayMethodDto;
import es.artyhub.tienda_back.domain.model.PayMethod;

public class PayMethodMapper {
    private static PayMethodMapper instance;

    public PayMethodMapper() {
    }

    public static PayMethodMapper getInstance() {
        if (instance == null) {
            instance = new PayMethodMapper();
        }
        return instance;
    }

    public PayMethod fromPayMethodDtoToPayMethod(PayMethodDto payMethodDto) {
        if (payMethodDto == null) {
            return null;
        }
        return new PayMethod(
            payMethodDto.id(),
            payMethodDto.nTarget(),
            payMethodDto.dateExpiration(),
            payMethodDto.cvv()
        );
    }

    public PayMethodDto fromPayMethodToPayMethodDto(PayMethod payMethod) {
        if (payMethod == null) {
            return null;
        }
        return new PayMethodDto(
            payMethod.getId(),
            payMethod.getnTarget(),
            payMethod.getDateExpiration(),
            payMethod.getCvv()
        );
    }
}
