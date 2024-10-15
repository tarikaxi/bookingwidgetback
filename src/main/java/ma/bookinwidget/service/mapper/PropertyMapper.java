package ma.bookinwidget.service.mapper;

import ma.bookinwidget.domain.Property;
import ma.bookinwidget.service.dto.PropertyDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Property} and its DTO {@link PropertyDTO}.
 */
@Mapper(componentModel = "spring")
public interface PropertyMapper extends EntityMapper<PropertyDTO, Property> {}
