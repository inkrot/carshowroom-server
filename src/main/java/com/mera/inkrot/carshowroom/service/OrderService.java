package com.mera.inkrot.carshowroom.service;

import com.mera.inkrot.carshowroom.dto.OrderDto;
import com.mera.inkrot.carshowroom.model.Option;
import com.mera.inkrot.carshowroom.model.Order;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;
import java.util.Set;

@WebService
public interface OrderService {

    @WebMethod(action = "getById")
    @XmlElement(name = "order")
    Order getById(@WebParam(name = "id") Long id);

    @WebMethod(action = "getAll")
    @XmlElementWrapper(name = "orders")
    @XmlElement(name = "order")
    List<Order> getAll();

    @WebMethod(action = "save")
    @XmlElement(name = "order")
    Order save(@WebParam(name = "order") OrderDto order);

    @WebMethod(action = "delete")
    void delete(@WebParam(name = "id") Long id);

    void addOption(Order order, Option option);

    void setOptions(Order order, Set<Option> options);

    void removeOption(Order order, Option option);

    Set<Option> getOptions(Long orderId);
}
