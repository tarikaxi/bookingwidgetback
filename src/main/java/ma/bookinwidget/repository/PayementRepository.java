package ma.bookinwidget.repository;

import ma.bookinwidget.domain.Payement;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Payement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PayementRepository extends JpaRepository<Payement, Long> {}
