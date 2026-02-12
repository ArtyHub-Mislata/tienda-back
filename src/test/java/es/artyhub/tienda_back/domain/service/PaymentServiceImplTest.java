package es.artyhub.tienda_back.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import es.artyhub.tienda_back.domain.dto.CardDto;
import es.artyhub.tienda_back.domain.dto.PaymentDto;
import es.artyhub.tienda_back.domain.enums.Status;
import es.artyhub.tienda_back.domain.service.impl.PaymentServiceImpl;
import es.artyhub.tienda_back.infraestructura.payment.BankCustomer;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceImplTest {
    
    @Mock
    private BankCustomer bankCustomer;
    
    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Test
    @DisplayName("should return status when pay is called with valid arguments")
    public void pay_WhenPayIsCalledWithValidArguments() {
        CardDto cardDto = new CardDto( "1234567890123456", "12/25", "123", "John Doe");
        PaymentDto paymentDto = new PaymentDto(1L, cardDto, "concept", new BigDecimal(10), Status.PENDING);
        
        when(bankCustomer.callBank(paymentDto)).thenReturn(true);

        Status status = paymentService.pay(paymentDto);

        assertEquals(Status.COMPLETED, status);
    }
}
