package com.mera.inkrot.carshowroom.dto;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.Set;

@XmlRootElement(name="order")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderDto implements Serializable {

    private String customerName;
    private String modelName;
    private String brandName;
    @XmlElementWrapper(name = "options")
    @XmlElement(name = "id")
    private Set<Long> options;

    public OrderDto() {
    }

    public OrderDto(String customerName, String carName, String brandName, Set<Long> options) {
        this.customerName = customerName;
        this.modelName = carName;
        this.brandName = brandName;
        this.options = options;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Set<Long> getOptions() {
        return options;
    }

    public void setOptions(Set<Long> options) {
        this.options = options;
    }
}
