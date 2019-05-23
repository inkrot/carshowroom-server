package com.mera.inkrot.carshowroom.service.impl;

import com.mera.inkrot.carshowroom.model.Status;
import com.mera.inkrot.carshowroom.repository.StatusRepository;
import com.mera.inkrot.carshowroom.service.StatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StatusServiceImpl implements StatusService {

    private Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    private StatusRepository statusRepository;

    @Autowired
    public StatusServiceImpl(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Override
    public Status getById(Long id) {
        logger.info("get status by id: {}", id);
        Optional<Status> status = statusRepository.findById(id);
        if (! status.isPresent())
            throw new IllegalArgumentException("Status not found");
        return status.get();
    }

    @Override
    public Status getByIdOrCode(Long id, String code) {
        logger.info("get status by id: {} or code {}", id, code);
        Optional<Status> statusById = (id == null ? Optional.empty() : statusRepository.findById(id));
        if (! statusById.isPresent()) {
            Optional<Status> statusByCode = (code == null ? Optional.empty() : statusRepository.findByCode(code));
            if (! statusByCode.isPresent())
                throw new IllegalArgumentException("Status not found");
            return statusByCode.get();
        }
        return statusById.get();
    }
}
