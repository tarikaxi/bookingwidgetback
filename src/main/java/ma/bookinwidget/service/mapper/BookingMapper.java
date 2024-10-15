package ma.bookinwidget.service.mapper;

import ma.bookinwidget.domain.Booking;
import ma.bookinwidget.service.dto.BookingDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Booking} and its DTO {@link BookingDTO}.
 */
@Mapper(componentModel = "spring")
public interface BookingMapper extends EntityMapper<BookingDTO, Booking> {}
