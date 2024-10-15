package ma.bookinwidget.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import ma.bookinwidget.domain.enumeration.Language;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Availability.
 */
@Entity
@Table(name = "availability")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Availability implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "start_date")
    private Instant startDate;

    @Column(name = "end_date")
    private Instant endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "language")
    private Language language;

    @Column(name = "country")
    private String country;

    @Column(name = "adult_number")
    private Integer adultNumber;

    @Column(name = "child_number")
    private Integer childNumber;

    @Column(name = "flexibility_on_days")
    private Integer flexibilityOnDays;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Availability id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getStartDate() {
        return this.startDate;
    }

    public Availability startDate(Instant startDate) {
        this.setStartDate(startDate);
        return this;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getEndDate() {
        return this.endDate;
    }

    public Availability endDate(Instant endDate) {
        this.setEndDate(endDate);
        return this;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public Language getLanguage() {
        return this.language;
    }

    public Availability language(Language language) {
        this.setLanguage(language);
        return this;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getCountry() {
        return this.country;
    }

    public Availability country(String country) {
        this.setCountry(country);
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getAdultNumber() {
        return this.adultNumber;
    }

    public Availability adultNumber(Integer adultNumber) {
        this.setAdultNumber(adultNumber);
        return this;
    }

    public void setAdultNumber(Integer adultNumber) {
        this.adultNumber = adultNumber;
    }

    public Integer getChildNumber() {
        return this.childNumber;
    }

    public Availability childNumber(Integer childNumber) {
        this.setChildNumber(childNumber);
        return this;
    }

    public void setChildNumber(Integer childNumber) {
        this.childNumber = childNumber;
    }

    public Integer getFlexibilityOnDays() {
        return this.flexibilityOnDays;
    }

    public Availability flexibilityOnDays(Integer flexibilityOnDays) {
        this.setFlexibilityOnDays(flexibilityOnDays);
        return this;
    }

    public void setFlexibilityOnDays(Integer flexibilityOnDays) {
        this.flexibilityOnDays = flexibilityOnDays;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Availability)) {
            return false;
        }
        return getId() != null && getId().equals(((Availability) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Availability{" +
            "id=" + getId() +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", language='" + getLanguage() + "'" +
            ", country='" + getCountry() + "'" +
            ", adultNumber=" + getAdultNumber() +
            ", childNumber=" + getChildNumber() +
            ", flexibilityOnDays=" + getFlexibilityOnDays() +
            "}";
    }
}
