package ma.bookinwidget.service.mapper;

import ma.bookinwidget.domain.Payement;
import ma.bookinwidget.service.dto.PayementDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Payement} and its DTO {@link PayementDTO}.
 */
@Mapper(componentModel = "spring")
public interface PayementMapper extends EntityMapper<PayementDTO, Payement> {}
