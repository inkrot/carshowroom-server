package com.mera.inkrot.carshowroom.dto;

import com.mera.inkrot.carshowroom.model.Option;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name="order")
@XmlAccessorType(XmlAccessType.FIELD)
public class OptionDto extends Dto implements Serializable {

    @XmlElement
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static OptionDto getFromEntity(Option option) {
        OptionDto optionDto = new OptionDto();
        optionDto.setId(option.getId());
        optionDto.setName(option.getName());
        return optionDto;
    }
}
