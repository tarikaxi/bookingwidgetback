package ma.bookinwidget.domain;

import static ma.bookinwidget.domain.PropertyTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import ma.bookinwidget.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PropertyTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Property.class);
        Property property1 = getPropertySample1();
        Property property2 = new Property();
        assertThat(property1).isNotEqualTo(property2);

        property2.setId(property1.getId());
        assertThat(property1).isEqualTo(property2);

        property2 = getPropertySample2();
        assertThat(property1).isNotEqualTo(property2);
    }
}
