package com.mera.inkrot.carshowroom.service;

import com.mera.inkrot.carshowroom.dto.CustomerDto;
import com.mera.inkrot.carshowroom.dto.OptionDto;
import com.mera.inkrot.carshowroom.dto.OrderDto;
import com.mera.inkrot.carshowroom.dto.StatusDto;
import com.mera.inkrot.carshowroom.model.Option;
import com.mera.inkrot.carshowroom.model.Order;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;
import java.util.Set;

@WebService
public interface OrderService {

    @WebMethod(action = "save")
    @XmlElement(name = "order")
    OrderDto save(@WebParam(name = "order") OrderDto order);

    @WebMethod(action = "update")
    @XmlElement(name = "order")
    OrderDto update(@WebParam(name = "id") Long id, @WebParam(name = "order") OrderDto order);

    @WebMethod(action = "delete")
    String delete(@WebParam(name = "id") Long id);

    @WebMethod(action = "getAll")
    @XmlElementWrapper(name = "orders")
    @XmlElement(name = "order")
    List<OrderDto> getAll();

    @WebMethod(action = "getAllByStatusAndCustomer")
    @XmlElementWrapper(name = "orders")
    @XmlElement(name = "order")
    List<OrderDto> getAllByStatusAndCustomer(@WebParam(name = "status") StatusDto statusDto, @WebParam(name = "customer") CustomerDto customerDto);

    @WebMethod(action = "getById")
    @XmlElement(name = "order")
    OrderDto getById(@WebParam(name = "id") Long id);

    @XmlTransient
    void addOption(Order order, Option option);

    @XmlTransient
    void setOptions(Order order, Set<Option> options);

    @XmlTransient
    void removeOption(Order order, Option option);

    @XmlTransient
    Set<OptionDto> getOptions(Long orderId);
}
