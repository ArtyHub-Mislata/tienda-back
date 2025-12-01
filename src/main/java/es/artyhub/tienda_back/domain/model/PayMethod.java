package es.artyhub.tienda_back.domain.model;

public class PayMethod {

    private Long id;
    private String nTarget;
    private String dateExpiration;
    private String cvv;

    public PayMethod() {
    }

    public PayMethod(Long id, String nTarget, String dateExpiration, String cvv) {
        this.id = id;
        this.nTarget = nTarget;
        this.dateExpiration = dateExpiration;
        this.cvv = cvv;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getnTarget() {
        return nTarget;
    }

    public void setnTarget(String nTarget) {
        this.nTarget = nTarget;
    }

    public String getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(String dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
}
