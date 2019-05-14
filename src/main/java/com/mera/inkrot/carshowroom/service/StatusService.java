package com.mera.inkrot.carshowroom.service;

import com.mera.inkrot.carshowroom.model.Status;

public interface StatusService {

    Status getById(Long id);

    Status getByIdOrCode(Long id, String code);
}
