package es.artyhub.tienda_back.domain.service.impl;

import org.springframework.stereotype.Service;

import es.artyhub.tienda_back.domain.dto.PaymentDto;
import es.artyhub.tienda_back.domain.enums.Status;
import es.artyhub.tienda_back.domain.service.PaymentService;
import es.artyhub.tienda_back.domain.repository.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Status pay(PaymentDto paymentDto) {
        return paymentRepository.pay(paymentDto);
    }
}
