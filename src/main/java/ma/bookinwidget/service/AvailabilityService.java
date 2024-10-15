package ma.bookinwidget.service;

import java.util.List;
import java.util.Optional;
import ma.bookinwidget.service.dto.AvailabilityDTO;

/**
 * Service Interface for managing {@link ma.bookinwidget.domain.Availability}.
 */
public interface AvailabilityService {
    /**
     * Save a availability.
     *
     * @param availabilityDTO the entity to save.
     * @return the persisted entity.
     */
    AvailabilityDTO save(AvailabilityDTO availabilityDTO);

    /**
     * Updates a availability.
     *
     * @param availabilityDTO the entity to update.
     * @return the persisted entity.
     */
    AvailabilityDTO update(AvailabilityDTO availabilityDTO);

    /**
     * Partially updates a availability.
     *
     * @param availabilityDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<AvailabilityDTO> partialUpdate(AvailabilityDTO availabilityDTO);

    /**
     * Get all the availabilities.
     *
     * @return the list of entities.
     */
    List<AvailabilityDTO> findAll();

    /**
     * Get the "id" availability.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AvailabilityDTO> findOne(Long id);

    /**
     * Delete the "id" availability.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
