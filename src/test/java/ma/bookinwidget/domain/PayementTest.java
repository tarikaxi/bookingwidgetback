package ma.bookinwidget.domain;

import static ma.bookinwidget.domain.PayementTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import ma.bookinwidget.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PayementTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Payement.class);
        Payement payement1 = getPayementSample1();
        Payement payement2 = new Payement();
        assertThat(payement1).isNotEqualTo(payement2);

        payement2.setId(payement1.getId());
        assertThat(payement1).isEqualTo(payement2);

        payement2 = getPayementSample2();
        assertThat(payement1).isNotEqualTo(payement2);
    }
}
