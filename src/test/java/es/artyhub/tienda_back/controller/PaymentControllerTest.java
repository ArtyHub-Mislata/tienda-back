package es.artyhub.tienda_back.controller;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.artyhub.tienda_back.domain.dto.CardDto;
import es.artyhub.tienda_back.domain.dto.PaymentDto;
import es.artyhub.tienda_back.domain.enums.Status;
import es.artyhub.tienda_back.domain.exception.ValidationException;
import es.artyhub.tienda_back.domain.service.PaymentService;
import es.artyhub.tienda_back.domain.service.SesionService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PaymentController.class)
@AutoConfigureMockMvc(addFilters = false)
public class PaymentControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PaymentService paymentService;

    @MockitoBean
    private SesionService sessionService;

    @Autowired
    private ObjectMapper objectMapper;

    @Nested
    @DisplayName("pay")
    public class Pay {
        


        @Test
        @DisplayName("should return validation exception if payment is null")
        public void shouldReturnValidationException_IfPaymentIsNull() throws Exception {
            PaymentDto paymentDto = null;

            when(paymentService.pay(paymentDto)).thenThrow(new ValidationException("Payment is null"));
            
            mockMvc.perform(post("/api/payments/pay")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(paymentDto)))
                .andExpect(status().isBadRequest());
        }
    }
}
