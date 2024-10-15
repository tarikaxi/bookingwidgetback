package ma.bookinwidget.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import ma.bookinwidget.domain.enumeration.Language;

/**
 * A DTO for the {@link ma.bookinwidget.domain.Availability} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AvailabilityDTO implements Serializable {

    private Long id;

    private Instant startDate;

    private Instant endDate;

    private Language language;

    private String country;

    private Integer adultNumber;

    private Integer childNumber;

    private Integer flexibilityOnDays;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getAdultNumber() {
        return adultNumber;
    }

    public void setAdultNumber(Integer adultNumber) {
        this.adultNumber = adultNumber;
    }

    public Integer getChildNumber() {
        return childNumber;
    }

    public void setChildNumber(Integer childNumber) {
        this.childNumber = childNumber;
    }

    public Integer getFlexibilityOnDays() {
        return flexibilityOnDays;
    }

    public void setFlexibilityOnDays(Integer flexibilityOnDays) {
        this.flexibilityOnDays = flexibilityOnDays;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AvailabilityDTO)) {
            return false;
        }

        AvailabilityDTO availabilityDTO = (AvailabilityDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, availabilityDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AvailabilityDTO{" +
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
