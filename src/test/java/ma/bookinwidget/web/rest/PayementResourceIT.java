package ma.bookinwidget.web.rest;

import static ma.bookinwidget.domain.PayementAsserts.*;
import static ma.bookinwidget.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import ma.bookinwidget.IntegrationTest;
import ma.bookinwidget.domain.Payement;
import ma.bookinwidget.domain.enumeration.PayementType;
import ma.bookinwidget.repository.PayementRepository;
import ma.bookinwidget.service.dto.PayementDTO;
import ma.bookinwidget.service.mapper.PayementMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link PayementResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PayementResourceIT {

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final PayementType DEFAULT_PAYEMENT_TYPE = PayementType.CB;
    private static final PayementType UPDATED_PAYEMENT_TYPE = PayementType.CMI;

    private static final String DEFAULT_CARD_NUM = "AAAAAAAAAA";
    private static final String UPDATED_CARD_NUM = "BBBBBBBBBB";

    private static final Instant DEFAULT_EXPIRATION = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_EXPIRATION = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_CRYPTOGRAM = 1;
    private static final Integer UPDATED_CRYPTOGRAM = 2;

    private static final String ENTITY_API_URL = "/api/payements";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private PayementRepository payementRepository;

    @Autowired
    private PayementMapper payementMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPayementMockMvc;

    private Payement payement;

    private Payement insertedPayement;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Payement createEntity() {
        return new Payement()
            .firstName(DEFAULT_FIRST_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .email(DEFAULT_EMAIL)
            .payementType(DEFAULT_PAYEMENT_TYPE)
            .cardNum(DEFAULT_CARD_NUM)
            .expiration(DEFAULT_EXPIRATION)
            .cryptogram(DEFAULT_CRYPTOGRAM);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Payement createUpdatedEntity() {
        return new Payement()
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .email(UPDATED_EMAIL)
            .payementType(UPDATED_PAYEMENT_TYPE)
            .cardNum(UPDATED_CARD_NUM)
            .expiration(UPDATED_EXPIRATION)
            .cryptogram(UPDATED_CRYPTOGRAM);
    }

    @BeforeEach
    public void initTest() {
        payement = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedPayement != null) {
            payementRepository.delete(insertedPayement);
            insertedPayement = null;
        }
    }

    @Test
    @Transactional
    void createPayement() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Payement
        PayementDTO payementDTO = payementMapper.toDto(payement);
        var returnedPayementDTO = om.readValue(
            restPayementMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(payementDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            PayementDTO.class
        );

        // Validate the Payement in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedPayement = payementMapper.toEntity(returnedPayementDTO);
        assertPayementUpdatableFieldsEquals(returnedPayement, getPersistedPayement(returnedPayement));

        insertedPayement = returnedPayement;
    }

    @Test
    @Transactional
    void createPayementWithExistingId() throws Exception {
        // Create the Payement with an existing ID
        payement.setId(1L);
        PayementDTO payementDTO = payementMapper.toDto(payement);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPayementMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(payementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Payement in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllPayements() throws Exception {
        // Initialize the database
        insertedPayement = payementRepository.saveAndFlush(payement);

        // Get all the payementList
        restPayementMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(payement.getId().intValue())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].payementType").value(hasItem(DEFAULT_PAYEMENT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].cardNum").value(hasItem(DEFAULT_CARD_NUM)))
            .andExpect(jsonPath("$.[*].expiration").value(hasItem(DEFAULT_EXPIRATION.toString())))
            .andExpect(jsonPath("$.[*].cryptogram").value(hasItem(DEFAULT_CRYPTOGRAM)));
    }

    @Test
    @Transactional
    void getPayement() throws Exception {
        // Initialize the database
        insertedPayement = payementRepository.saveAndFlush(payement);

        // Get the payement
        restPayementMockMvc
            .perform(get(ENTITY_API_URL_ID, payement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(payement.getId().intValue()))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.payementType").value(DEFAULT_PAYEMENT_TYPE.toString()))
            .andExpect(jsonPath("$.cardNum").value(DEFAULT_CARD_NUM))
            .andExpect(jsonPath("$.expiration").value(DEFAULT_EXPIRATION.toString()))
            .andExpect(jsonPath("$.cryptogram").value(DEFAULT_CRYPTOGRAM));
    }

    @Test
    @Transactional
    void getNonExistingPayement() throws Exception {
        // Get the payement
        restPayementMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingPayement() throws Exception {
        // Initialize the database
        insertedPayement = payementRepository.saveAndFlush(payement);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the payement
        Payement updatedPayement = payementRepository.findById(payement.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedPayement are not directly saved in db
        em.detach(updatedPayement);
        updatedPayement
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .email(UPDATED_EMAIL)
            .payementType(UPDATED_PAYEMENT_TYPE)
            .cardNum(UPDATED_CARD_NUM)
            .expiration(UPDATED_EXPIRATION)
            .cryptogram(UPDATED_CRYPTOGRAM);
        PayementDTO payementDTO = payementMapper.toDto(updatedPayement);

        restPayementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, payementDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(payementDTO))
            )
            .andExpect(status().isOk());

        // Validate the Payement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedPayementToMatchAllProperties(updatedPayement);
    }

    @Test
    @Transactional
    void putNonExistingPayement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        payement.setId(longCount.incrementAndGet());

        // Create the Payement
        PayementDTO payementDTO = payementMapper.toDto(payement);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPayementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, payementDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(payementDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Payement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPayement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        payement.setId(longCount.incrementAndGet());

        // Create the Payement
        PayementDTO payementDTO = payementMapper.toDto(payement);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPayementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(payementDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Payement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPayement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        payement.setId(longCount.incrementAndGet());

        // Create the Payement
        PayementDTO payementDTO = payementMapper.toDto(payement);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPayementMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(payementDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Payement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePayementWithPatch() throws Exception {
        // Initialize the database
        insertedPayement = payementRepository.saveAndFlush(payement);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the payement using partial update
        Payement partialUpdatedPayement = new Payement();
        partialUpdatedPayement.setId(payement.getId());

        partialUpdatedPayement
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .payementType(UPDATED_PAYEMENT_TYPE)
            .cryptogram(UPDATED_CRYPTOGRAM);

        restPayementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPayement.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedPayement))
            )
            .andExpect(status().isOk());

        // Validate the Payement in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPayementUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedPayement, payement), getPersistedPayement(payement));
    }

    @Test
    @Transactional
    void fullUpdatePayementWithPatch() throws Exception {
        // Initialize the database
        insertedPayement = payementRepository.saveAndFlush(payement);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the payement using partial update
        Payement partialUpdatedPayement = new Payement();
        partialUpdatedPayement.setId(payement.getId());

        partialUpdatedPayement
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .email(UPDATED_EMAIL)
            .payementType(UPDATED_PAYEMENT_TYPE)
            .cardNum(UPDATED_CARD_NUM)
            .expiration(UPDATED_EXPIRATION)
            .cryptogram(UPDATED_CRYPTOGRAM);

        restPayementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPayement.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedPayement))
            )
            .andExpect(status().isOk());

        // Validate the Payement in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPayementUpdatableFieldsEquals(partialUpdatedPayement, getPersistedPayement(partialUpdatedPayement));
    }

    @Test
    @Transactional
    void patchNonExistingPayement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        payement.setId(longCount.incrementAndGet());

        // Create the Payement
        PayementDTO payementDTO = payementMapper.toDto(payement);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPayementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, payementDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(payementDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Payement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPayement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        payement.setId(longCount.incrementAndGet());

        // Create the Payement
        PayementDTO payementDTO = payementMapper.toDto(payement);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPayementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(payementDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Payement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPayement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        payement.setId(longCount.incrementAndGet());

        // Create the Payement
        PayementDTO payementDTO = payementMapper.toDto(payement);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPayementMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(payementDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Payement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePayement() throws Exception {
        // Initialize the database
        insertedPayement = payementRepository.saveAndFlush(payement);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the payement
        restPayementMockMvc
            .perform(delete(ENTITY_API_URL_ID, payement.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return payementRepository.count();
    }

    protected void assertIncrementedRepositoryCount(long countBefore) {
        assertThat(countBefore + 1).isEqualTo(getRepositoryCount());
    }

    protected void assertDecrementedRepositoryCount(long countBefore) {
        assertThat(countBefore - 1).isEqualTo(getRepositoryCount());
    }

    protected void assertSameRepositoryCount(long countBefore) {
        assertThat(countBefore).isEqualTo(getRepositoryCount());
    }

    protected Payement getPersistedPayement(Payement payement) {
        return payementRepository.findById(payement.getId()).orElseThrow();
    }

    protected void assertPersistedPayementToMatchAllProperties(Payement expectedPayement) {
        assertPayementAllPropertiesEquals(expectedPayement, getPersistedPayement(expectedPayement));
    }

    protected void assertPersistedPayementToMatchUpdatableProperties(Payement expectedPayement) {
        assertPayementAllUpdatablePropertiesEquals(expectedPayement, getPersistedPayement(expectedPayement));
    }
}
