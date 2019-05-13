package com.mera.inkrot.carshowroom.service.impl;

import com.mera.inkrot.carshowroom.model.Status;
import com.mera.inkrot.carshowroom.repository.StatusRepository;
import com.mera.inkrot.carshowroom.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusServiceImpl implements StatusService {

    @Autowired
    StatusRepository statusRepository;

    @Override
    public Status getById(Long id) {
        return statusRepository.getOne(id);
    }
}
