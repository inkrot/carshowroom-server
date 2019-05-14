package com.mera.inkrot.carshowroom.dto;

import com.mera.inkrot.carshowroom.model.Option;
import com.mera.inkrot.carshowroom.model.Order;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@XmlRootElement(name="order")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderDto extends Dto implements Serializable {

    private CustomerDto customer;
    private String modelName;
    private String brandName;
    private StatusDto status;
    @XmlElementWrapper(name = "options")
    @XmlElement(name = "option")
    private Set<OptionDto> options;

    public OrderDto() {
    }

    public OrderDto(CustomerDto customer, String carName, String brandName, Set<OptionDto> options) {
        this.customer = customer;
        this.modelName = carName;
        this.brandName = brandName;
        this.options = options;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
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

    public StatusDto getStatus() {
        return status;
    }

    public void setStatus(StatusDto status) {
        this.status = status;
    }

    public Set<OptionDto> getOptions() {
        return options;
    }

    public void setOptions(Set<OptionDto> options) {
        this.options = options;
    }

    public static OrderDto getFromEntity(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setCustomer(CustomerDto.getFromEntity(order.getCustomer()));
        orderDto.setModelName(order.getCar().getModel());
        orderDto.setBrandName(order.getCar().getBrand().getName());
        orderDto.setStatus(StatusDto.getFromEntity(order.getStatus()));
        Set<OptionDto> options = new HashSet<>();
        for (Option option : order.getOptions())
            options.add(OptionDto.getFromEntity(option));
        orderDto.setOptions(options);
        return orderDto;
    }
}
