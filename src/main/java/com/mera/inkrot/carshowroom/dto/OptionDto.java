package com.mera.inkrot.carshowroom.dto;

import com.mera.inkrot.carshowroom.model.Option;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Objects;

@ApiModel(description = "Class for transferring data of Option entity.")
@XmlRootElement(name="option")
@XmlAccessorType(XmlAccessType.FIELD)
public class OptionDto extends Dto implements Serializable {

    @ApiModelProperty(notes = "Name of the Option.", example = "зимняя резина")
    @XmlElement
    private String name;

    public OptionDto() { }

    public OptionDto(Long id) {
        setId(id);
    }

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

    @Override
    public String toString() {
        return "OptionDto{" +
                "id='" + getId() + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OptionDto optionDto = (OptionDto) o;
        return Objects.equals(name, optionDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), name);
    }
}
