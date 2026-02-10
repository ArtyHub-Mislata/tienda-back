package es.artyhub.tienda_back.domain.model;

public class Card {

    private Long id;
    private String nTarget;
    private String dateExpiration;
    private String cvv;
    private String holderName;

    public Card() {
    }

    public Card(Long id, String nTarget, String dateExpiration, String cvv, String holderName) {
        this.id = id;
        this.nTarget = nTarget;
        this.dateExpiration = dateExpiration;
        this.cvv = cvv;
        this.holderName = holderName;
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

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }
}
