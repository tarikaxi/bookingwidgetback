package ma.bookinwidget.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import ma.bookinwidget.domain.enumeration.PayementType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Payement.
 */
@Entity
@Table(name = "payement")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Payement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "payement_type")
    private PayementType payementType;

    @Column(name = "card_num")
    private String cardNum;

    @Column(name = "expiration")
    private Instant expiration;

    @Column(name = "cryptogram")
    private Integer cryptogram;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Payement id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public Payement firstName(String firstName) {
        this.setFirstName(firstName);
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public Payement lastName(String lastName) {
        this.setLastName(lastName);
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public Payement email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PayementType getPayementType() {
        return this.payementType;
    }

    public Payement payementType(PayementType payementType) {
        this.setPayementType(payementType);
        return this;
    }

    public void setPayementType(PayementType payementType) {
        this.payementType = payementType;
    }

    public String getCardNum() {
        return this.cardNum;
    }

    public Payement cardNum(String cardNum) {
        this.setCardNum(cardNum);
        return this;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public Instant getExpiration() {
        return this.expiration;
    }

    public Payement expiration(Instant expiration) {
        this.setExpiration(expiration);
        return this;
    }

    public void setExpiration(Instant expiration) {
        this.expiration = expiration;
    }

    public Integer getCryptogram() {
        return this.cryptogram;
    }

    public Payement cryptogram(Integer cryptogram) {
        this.setCryptogram(cryptogram);
        return this;
    }

    public void setCryptogram(Integer cryptogram) {
        this.cryptogram = cryptogram;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Payement)) {
            return false;
        }
        return getId() != null && getId().equals(((Payement) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Payement{" +
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
