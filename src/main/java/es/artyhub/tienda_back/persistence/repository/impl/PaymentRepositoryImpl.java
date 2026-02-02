package es.artyhub.tienda_back.persistence.repository.impl;

import es.artyhub.tienda_back.domain.dto.PaymentDto;
import es.artyhub.tienda_back.domain.enums.Status;
import es.artyhub.tienda_back.domain.repository.PaymentRepository;
import es.artyhub.tienda_back.persistence.dao.jpa.PaymentJpaDao;

public class PaymentRepositoryImpl implements PaymentRepository {

    private final PaymentJpaDao paymentJpaDao;

    public PaymentRepositoryImpl(PaymentJpaDao paymentJpaDao) {
        this.paymentJpaDao = paymentJpaDao;
    }

    @Override
    public Status pay(PaymentDto paymentDto) {
        return paymentJpaDao.pay(paymentDto);
    }
    
}
