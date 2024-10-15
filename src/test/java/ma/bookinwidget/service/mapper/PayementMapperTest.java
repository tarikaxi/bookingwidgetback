package ma.bookinwidget.service.mapper;

import static ma.bookinwidget.domain.PayementAsserts.*;
import static ma.bookinwidget.domain.PayementTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PayementMapperTest {

    private PayementMapper payementMapper;

    @BeforeEach
    void setUp() {
        payementMapper = new PayementMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getPayementSample1();
        var actual = payementMapper.toEntity(payementMapper.toDto(expected));
        assertPayementAllPropertiesEquals(expected, actual);
    }
}
