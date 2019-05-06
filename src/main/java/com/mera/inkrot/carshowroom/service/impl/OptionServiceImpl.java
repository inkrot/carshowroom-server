package com.mera.inkrot.carshowroom.service.impl;

import com.mera.inkrot.carshowroom.model.Option;
import com.mera.inkrot.carshowroom.repository.OptionRepository;
import com.mera.inkrot.carshowroom.service.OptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OptionServiceImpl implements OptionService {

    Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    OptionRepository optionRepository;

    @Override
    public Option getById(Long id) {
        logger.info("get option by id: {}", id);
        Optional<Option> option = optionRepository.findById(id);
        if (! option.isPresent())
            throw new IllegalArgumentException("Option not found");
        return option.get();
    }
}
