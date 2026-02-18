package es.artyhub.tienda_back.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.artyhub.tienda_back.domain.dto.PaymentDto;
import es.artyhub.tienda_back.domain.enums.Status;
import es.artyhub.tienda_back.domain.service.PaymentService;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/pay")
    public ResponseEntity<Boolean> pay(@RequestBody PaymentDto paymentDto) {
        Status status = paymentService.pay(paymentDto);

        boolean pagoCorrecto = status.equals(Status.COMPLETED);
        return new ResponseEntity<>(pagoCorrecto, HttpStatus.OK);
    }
}
