package ma.bookinwidget.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import ma.bookinwidget.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PayementDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PayementDTO.class);
        PayementDTO payementDTO1 = new PayementDTO();
        payementDTO1.setId(1L);
        PayementDTO payementDTO2 = new PayementDTO();
        assertThat(payementDTO1).isNotEqualTo(payementDTO2);
        payementDTO2.setId(payementDTO1.getId());
        assertThat(payementDTO1).isEqualTo(payementDTO2);
        payementDTO2.setId(2L);
        assertThat(payementDTO1).isNotEqualTo(payementDTO2);
        payementDTO1.setId(null);
        assertThat(payementDTO1).isNotEqualTo(payementDTO2);
    }
}
