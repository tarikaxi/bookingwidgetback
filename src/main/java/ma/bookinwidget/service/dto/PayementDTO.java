package ma.bookinwidget.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import ma.bookinwidget.domain.enumeration.PayementType;

/**
 * A DTO for the {@link ma.bookinwidget.domain.Payement} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PayementDTO implements Serializable {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private PayementType payementType;

    private String cardNum;

    private Instant expiration;

    private Integer cryptogram;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PayementType getPayementType() {
        return payementType;
    }

    public void setPayementType(PayementType payementType) {
        this.payementType = payementType;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public Instant getExpiration() {
        return expiration;
    }

    public void setExpiration(Instant expiration) {
        this.expiration = expiration;
    }

    public Integer getCryptogram() {
        return cryptogram;
    }

    public void setCryptogram(Integer cryptogram) {
        this.cryptogram = cryptogram;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PayementDTO)) {
            return false;
        }

        PayementDTO payementDTO = (PayementDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, payementDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PayementDTO{" +
            "id=" + getId() +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", email='" + getEmail() + "'" +
            ", payementType='" + getPayementType() + "'" +
            ", cardNum='" + getCardNum() + "'" +
            ", expiration='" + getExpiration() + "'" +
            ", cryptogram=" + getCryptogram() +
            "}";
    }
}
