package ma.bookinwidget.service;

import java.util.Optional;
import ma.bookinwidget.service.dto.PayementDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link ma.bookinwidget.domain.Payement}.
 */
public interface PayementService {
    /**
     * Save a payement.
     *
     * @param payementDTO the entity to save.
     * @return the persisted entity.
     */
    PayementDTO save(PayementDTO payementDTO);

    /**
     * Updates a payement.
     *
     * @param payementDTO the entity to update.
     * @return the persisted entity.
     */
    PayementDTO update(PayementDTO payementDTO);

    /**
     * Partially updates a payement.
     *
     * @param payementDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<PayementDTO> partialUpdate(PayementDTO payementDTO);

    /**
     * Get all the payements.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PayementDTO> findAll(Pageable pageable);

    /**
     * Get the "id" payement.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PayementDTO> findOne(Long id);

    /**
     * Delete the "id" payement.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
