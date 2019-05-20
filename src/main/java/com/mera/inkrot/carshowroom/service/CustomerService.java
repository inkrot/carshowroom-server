package com.mera.inkrot.carshowroom.service;

import com.mera.inkrot.carshowroom.dto.CustomerDto;
import com.mera.inkrot.carshowroom.model.Customer;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface CustomerService {

    @WebMethod(action = "getById")
    CustomerDto getById(@WebParam(name = "id") Long id);

    @WebMethod(action = "save")
    CustomerDto save(@WebParam(name = "name") String name);

    Customer getByIdOrName(Long id, String name);
}
