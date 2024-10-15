package ma.bookinwidget.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import ma.bookinwidget.repository.PayementRepository;
import ma.bookinwidget.service.PayementService;
import ma.bookinwidget.service.dto.PayementDTO;
import ma.bookinwidget.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link ma.bookinwidget.domain.Payement}.
 */
@RestController
@RequestMapping("/api/payements")
public class PayementResource {

    private static final Logger LOG = LoggerFactory.getLogger(PayementResource.class);

    private static final String ENTITY_NAME = "payement";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PayementService payementService;

    private final PayementRepository payementRepository;

    public PayementResource(PayementService payementService, PayementRepository payementRepository) {
        this.payementService = payementService;
        this.payementRepository = payementRepository;
    }

    /**
     * {@code POST  /payements} : Create a new payement.
     *
     * @param payementDTO the payementDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new payementDTO, or with status {@code 400 (Bad Request)} if the payement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<PayementDTO> createPayement(@RequestBody PayementDTO payementDTO) throws URISyntaxException {
        LOG.debug("REST request to save Payement : {}", payementDTO);
        if (payementDTO.getId() != null) {
            throw new BadRequestAlertException("A new payement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        payementDTO = payementService.save(payementDTO);
        return ResponseEntity.created(new URI("/api/payements/" + payementDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, payementDTO.getId().toString()))
            .body(payementDTO);
    }

    /**
     * {@code PUT  /payements/:id} : Updates an existing payement.
     *
     * @param id the id of the payementDTO to save.
     * @param payementDTO the payementDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated payementDTO,
     * or with status {@code 400 (Bad Request)} if the payementDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the payementDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<PayementDTO> updatePayement(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PayementDTO payementDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update Payement : {}, {}", id, payementDTO);
        if (payementDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, payementDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!payementRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        payementDTO = payementService.update(payementDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, payementDTO.getId().toString()))
            .body(payementDTO);
    }

    /**
     * {@code PATCH  /payements/:id} : Partial updates given fields of an existing payement, field will ignore if it is null
     *
     * @param id the id of the payementDTO to save.
     * @param payementDTO the payementDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated payementDTO,
     * or with status {@code 400 (Bad Request)} if the payementDTO is not valid,
     * or with status {@code 404 (Not Found)} if the payementDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the payementDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<PayementDTO> partialUpdatePayement(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PayementDTO payementDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update Payement partially : {}, {}", id, payementDTO);
        if (payementDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, payementDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!payementRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PayementDTO> result = payementService.partialUpdate(payementDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, payementDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /payements} : get all the payements.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of payements in body.
     */
    @GetMapping("")
    public ResponseEntity<List<PayementDTO>> getAllPayements(@org.springdoc.core.annotations.ParameterObject Pageable pageable) {
        LOG.debug("REST request to get a page of Payements");
        Page<PayementDTO> page = payementService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /payements/:id} : get the "id" payement.
     *
     * @param id the id of the payementDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the payementDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<PayementDTO> getPayement(@PathVariable("id") Long id) {
        LOG.debug("REST request to get Payement : {}", id);
        Optional<PayementDTO> payementDTO = payementService.findOne(id);
        return ResponseUtil.wrapOrNotFound(payementDTO);
    }

    /**
     * {@code DELETE  /payements/:id} : delete the "id" payement.
     *
     * @param id the id of the payementDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayement(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete Payement : {}", id);
        payementService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
