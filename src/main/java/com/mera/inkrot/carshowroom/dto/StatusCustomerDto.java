package com.mera.inkrot.carshowroom.dto;

import io.swagger.annotations.ApiModel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@ApiModel(description = "Class for transferring data of Status and Customer entities.")
@XmlRootElement
public class StatusCustomerDto {

    @XmlElement
    private StatusDto status;

    @XmlElement
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
