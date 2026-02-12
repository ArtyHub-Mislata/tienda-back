package es.artyhub.tienda_back.infraestructura.payment;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import es.artyhub.tienda_back.domain.dto.PaymentDto;
import es.artyhub.tienda_back.infraestructura.payment.dto.AutorizacionDto;
import es.artyhub.tienda_back.infraestructura.payment.dto.DestinoDto;
import es.artyhub.tienda_back.infraestructura.payment.dto.OrigenDto;
import es.artyhub.tienda_back.infraestructura.payment.dto.PagoDto;
import es.artyhub.tienda_back.infraestructura.payment.dto.PagoTarjetaDto;

@Component 
public class BankCustomer {

    private final RestTemplate restTemplate;

    public BankCustomer(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private final String BANCO_URL = "http://bank-back-artyhub.preproducciondaw.cip.fpmislata.com:8081/api/pagos/pago_tarjeta"; 

    private final String MI_TIENDA_IBAN = "ES5521000418450200051339";
    private final String MI_TIENDA_LOGIN = "artyhub";
    private final String MI_API_TOKEN = "token_artyhub_123";

    public boolean callBank(PaymentDto paymentDto) {
        try {
            var autorizacion = new AutorizacionDto(MI_TIENDA_LOGIN, MI_API_TOKEN);
            
            var origen = new OrigenDto(
                paymentDto.getCardDto().getnTarget(),
                paymentDto.getCardDto().getDateExpiration(),
                paymentDto.getCardDto().getCvv(),
                paymentDto.getCardDto().getHolderName()
            );
            
            var destino = new DestinoDto(MI_TIENDA_IBAN);
            
            var pago = new PagoDto(paymentDto.getAmount(), paymentDto.getConcept());

            var pagoTarjetaDto = new PagoTarjetaDto(autorizacion, origen, destino, pago);

            restTemplate.postForEntity(BANCO_URL, pagoTarjetaDto, Void.class);

            return true;

        } catch (Exception e) {
            System.err.println("ERROR LLAMANDO AL BANCO: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
