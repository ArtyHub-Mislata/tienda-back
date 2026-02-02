package es.artyhub.tienda_back.persistence.dao.jpa;

import es.artyhub.tienda_back.domain.dto.PaymentDto;
import es.artyhub.tienda_back.domain.enums.Status;

public interface PaymentJpaDao {
    Status pay(PaymentDto paymentDto);
}
