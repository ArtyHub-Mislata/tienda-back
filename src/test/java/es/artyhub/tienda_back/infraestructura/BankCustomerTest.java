package es.artyhub.tienda_back.infraestructura;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import es.artyhub.tienda_back.domain.dto.CardDto;
import es.artyhub.tienda_back.domain.dto.PaymentDto;
import es.artyhub.tienda_back.domain.enums.Status;
import es.artyhub.tienda_back.infraestructura.payment.BankCustomer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
public class BankCustomerTest {
    
    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private BankCustomer bankCustomer;

    @Nested
    @DisplayName("callBank")
    public class CallBank {
        @Test
        @DisplayName("should return true when callBank works")
        public void callBank_WhenWorks() {
            CardDto cardDto = new CardDto( "1234567890123456", "12/24", "123", "John Doe");
            PaymentDto paymentDto = new PaymentDto(1L, cardDto, "concepto", new BigDecimal(100.0), Status.PENDING);
            Boolean result = bankCustomer.callBank(paymentDto);
            
            assertTrue(result);
        }

        @Test
        @DisplayName("should return false when callBank fails")
        public void callBank_WhenFails() {
            Boolean result = bankCustomer.callBank(new PaymentDto());
            
            assertFalse(result);
        }
    }
}
