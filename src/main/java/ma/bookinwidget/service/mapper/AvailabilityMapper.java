package ma.bookinwidget.service.mapper;

import ma.bookinwidget.domain.Availability;
import ma.bookinwidget.service.dto.AvailabilityDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Availability} and its DTO {@link AvailabilityDTO}.
 */
@Mapper(componentModel = "spring")
public interface AvailabilityMapper extends EntityMapper<AvailabilityDTO, Availability> {}
