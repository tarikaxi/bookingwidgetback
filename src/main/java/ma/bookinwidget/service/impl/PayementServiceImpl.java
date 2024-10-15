package ma.bookinwidget.service.impl;

import java.util.Optional;
import ma.bookinwidget.domain.Payement;
import ma.bookinwidget.repository.PayementRepository;
import ma.bookinwidget.service.PayementService;
import ma.bookinwidget.service.dto.PayementDTO;
import ma.bookinwidget.service.mapper.PayementMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ma.bookinwidget.domain.Payement}.
 */
@Service
@Transactional
public class PayementServiceImpl implements PayementService {

    private static final Logger LOG = LoggerFactory.getLogger(PayementServiceImpl.class);

    private final PayementRepository payementRepository;

    private final PayementMapper payementMapper;

    public PayementServiceImpl(PayementRepository payementRepository, PayementMapper payementMapper) {
        this.payementRepository = payementRepository;
        this.payementMapper = payementMapper;
    }

    @Override
    public PayementDTO save(PayementDTO payementDTO) {
        LOG.debug("Request to save Payement : {}", payementDTO);
        Payement payement = payementMapper.toEntity(payementDTO);
        payement = payementRepository.save(payement);
        return payementMapper.toDto(payement);
    }

    @Override
    public PayementDTO update(PayementDTO payementDTO) {
        LOG.debug("Request to update Payement : {}", payementDTO);
        Payement payement = payementMapper.toEntity(payementDTO);
        payement = payementRepository.save(payement);
        return payementMapper.toDto(payement);
    }

    @Override
    public Optional<PayementDTO> partialUpdate(PayementDTO payementDTO) {
        LOG.debug("Request to partially update Payement : {}", payementDTO);

        return payementRepository
            .findById(payementDTO.getId())
            .map(existingPayement -> {
                payementMapper.partialUpdate(existingPayement, payementDTO);

                return existingPayement;
            })
            .map(payementRepository::save)
            .map(payementMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PayementDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all Payements");
        return payementRepository.findAll(pageable).map(payementMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PayementDTO> findOne(Long id) {
        LOG.debug("Request to get Payement : {}", id);
        return payementRepository.findById(id).map(payementMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete Payement : {}", id);
        payementRepository.deleteById(id);
    }
}
