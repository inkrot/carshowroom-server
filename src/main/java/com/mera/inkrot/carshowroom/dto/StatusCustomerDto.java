package com.mera.inkrot.carshowroom.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class StatusCustomerDto {

    private StatusDto status;
    private CustomerDto customer;

    public StatusDto getStatus() {
        return status;
    }

    public void setStatus(StatusDto status) {
        this.status = status;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }
}
