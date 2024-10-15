package ma.bookinwidget.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Booking.
 */
@Entity
@Table(name = "booking")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Booking implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "client_first_name")
    private String clientFirstName;

    @Column(name = "client_last_name")
    private String clientLastName;

    @Column(name = "start_date")
    private Instant startDate;

    @Column(name = "end_date")
    private Instant endDate;

    @Column(name = "rooms")
    private String rooms;

    @Column(name = "ref")
    private String ref;

    @Column(name = "client_email")
    private String clientEmail;

    @Column(name = "client_phone")
    private String clientPhone;

    @Column(name = "adult_number")
    private Integer adultNumber;

    @Column(name = "child_number")
    private Integer childNumber;

    @Column(name = "isconfirmed")
    private Boolean isconfirmed;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Booking id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientFirstName() {
        return this.clientFirstName;
    }

    public Booking clientFirstName(String clientFirstName) {
        this.setClientFirstName(clientFirstName);
        return this;
    }

    public void setClientFirstName(String clientFirstName) {
        this.clientFirstName = clientFirstName;
    }

    public String getClientLastName() {
        return this.clientLastName;
    }

    public Booking clientLastName(String clientLastName) {
        this.setClientLastName(clientLastName);
        return this;
    }

    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
    }

    public Instant getStartDate() {
        return this.startDate;
    }

    public Booking startDate(Instant startDate) {
        this.setStartDate(startDate);
        return this;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getEndDate() {
        return this.endDate;
    }

    public Booking endDate(Instant endDate) {
        this.setEndDate(endDate);
        return this;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public String getRooms() {
        return this.rooms;
    }

    public Booking rooms(String rooms) {
        this.setRooms(rooms);
        return this;
    }

    public void setRooms(String rooms) {
        this.rooms = rooms;
    }

    public String getRef() {
        return this.ref;
    }

    public Booking ref(String ref) {
        this.setRef(ref);
        return this;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getClientEmail() {
        return this.clientEmail;
    }

    public Booking clientEmail(String clientEmail) {
        this.setClientEmail(clientEmail);
        return this;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getClientPhone() {
        return this.clientPhone;
    }

    public Booking clientPhone(String clientPhone) {
        this.setClientPhone(clientPhone);
        return this;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public Integer getAdultNumber() {
        return this.adultNumber;
    }

    public Booking adultNumber(Integer adultNumber) {
        this.setAdultNumber(adultNumber);
        return this;
    }

    public void setAdultNumber(Integer adultNumber) {
        this.adultNumber = adultNumber;
    }

    public Integer getChildNumber() {
        return this.childNumber;
    }

    public Booking childNumber(Integer childNumber) {
        this.setChildNumber(childNumber);
        return this;
    }

    public void setChildNumber(Integer childNumber) {
        this.childNumber = childNumber;
    }

    public Boolean getIsconfirmed() {
        return this.isconfirmed;
    }

    public Booking isconfirmed(Boolean isconfirmed) {
        this.setIsconfirmed(isconfirmed);
        return this;
    }

    public void setIsconfirmed(Boolean isconfirmed) {
        this.isconfirmed = isconfirmed;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Booking)) {
            return false;
        }
        return getId() != null && getId().equals(((Booking) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Booking{" +
            "id=" + getId() +
            ", clientFirstName='" + getClientFirstName() + "'" +
            ", clientLastName='" + getClientLastName() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", rooms='" + getRooms() + "'" +
            ", ref='" + getRef() + "'" +
            ", clientEmail='" + getClientEmail() + "'" +
            ", clientPhone='" + getClientPhone() + "'" +
            ", adultNumber=" + getAdultNumber() +
            ", childNumber=" + getChildNumber() +
            ", isconfirmed='" + getIsconfirmed() + "'" +
            "}";
    }
}
