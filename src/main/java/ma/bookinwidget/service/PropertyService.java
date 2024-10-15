package ma.bookinwidget.service;

import java.util.List;
import java.util.Optional;
import ma.bookinwidget.service.dto.PropertyDTO;

/**
 * Service Interface for managing {@link ma.bookinwidget.domain.Property}.
 */
public interface PropertyService {
    /**
     * Save a property.
     *
     * @param propertyDTO the entity to save.
     * @return the persisted entity.
     */
    PropertyDTO save(PropertyDTO propertyDTO);

    /**
     * Updates a property.
     *
     * @param propertyDTO the entity to update.
     * @return the persisted entity.
     */
    PropertyDTO update(PropertyDTO propertyDTO);

    /**
     * Partially updates a property.
     *
     * @param propertyDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<PropertyDTO> partialUpdate(PropertyDTO propertyDTO);

    /**
     * Get all the properties.
     *
     * @return the list of entities.
     */
    List<PropertyDTO> findAll();

    /**
     * Get the "id" property.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PropertyDTO> findOne(Long id);

    /**
     * Delete the "id" property.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
