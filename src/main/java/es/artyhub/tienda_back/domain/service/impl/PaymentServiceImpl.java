package es.artyhub.tienda_back.domain.service.impl;

import org.springframework.stereotype.Service;

import es.artyhub.tienda_back.domain.dto.PaymentDto;
import es.artyhub.tienda_back.domain.enums.Status;
import es.artyhub.tienda_back.domain.service.PaymentService;
import es.artyhub.tienda_back.infraestructura.payment.BankCustomer;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final BankCustomer bankCustomer;

    public PaymentServiceImpl(BankCustomer bankCustomer) {
        this.bankCustomer = bankCustomer;
    }

    @Override
    public Status pay(PaymentDto paymentDto) {
        boolean exito = bankCustomer.callBank(paymentDto);
        if (exito) {
            paymentDto.setStatus(Status.COMPLETED);
            return Status.COMPLETED;
        } else {
            paymentDto.setStatus(Status.FAILED);
            return Status.FAILED;
        }
    }
}
