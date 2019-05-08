package com.mera.inkrot.carshowroom.service;

import com.mera.inkrot.carshowroom.model.Customer;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface CustomerService {

    @WebMethod(action = "getById")
    Customer getById(@WebParam(name = "id") Long id);

    @WebMethod(action = "save")
    Customer save(@WebParam(name = "name") String name);
}
