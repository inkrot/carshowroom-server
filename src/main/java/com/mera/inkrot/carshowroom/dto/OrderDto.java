package com.mera.inkrot.carshowroom.dto;

import com.mera.inkrot.carshowroom.model.Option;
import com.mera.inkrot.carshowroom.model.Order;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@ApiModel(description = "Class for transferring data of Order entity.")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="order")
public class OrderDto extends Dto implements Serializable {

    @ApiModelProperty(notes = "Customer of the Order.", required = true)
    @XmlElement
    private CustomerDto customer;

    @ApiModelProperty(notes = "Name of the car model.", example = "camry", required = true)
    @XmlElement
    private String modelName;

    @ApiModelProperty(notes = "Name of the car brand.", example = "Toyota", required = true)
    @XmlElement
    private String brandName;

    @ApiModelProperty(notes = "Status of the Order.")
    @XmlElement
    private StatusDto status;

    @ApiModelProperty(notes = "Options collection of the Order.")
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
        if (order.getCar() != null) {
            orderDto.setModelName(order.getCar().getModel());
            if (order.getCar().getBrand() != null) orderDto.setBrandName(order.getCar().getBrand().getName());
        }
        orderDto.setStatus(StatusDto.getFromEntity(order.getStatus()));
        Set<OptionDto> options = new HashSet<>();
        for (Option option : order.getOptions())
            options.add(OptionDto.getFromEntity(option));
        orderDto.setOptions(options);
        return orderDto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDto orderDto = (OrderDto) o;
        return Objects.equals(customer, orderDto.customer) &&
                Objects.equals(modelName, orderDto.modelName) &&
                Objects.equals(brandName, orderDto.brandName) &&
                Objects.equals(status, orderDto.status) &&
                Objects.equals(options, orderDto.options);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, modelName, brandName, status, options);
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "id=" + getId() + '\'' +
                ", customer=" + customer +
                ", modelName='" + modelName + '\'' +
                ", brandName='" + brandName + '\'' +
                ", status=" + status +
                ", options=" + options +
                '}';
    }
}
