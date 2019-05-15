package com.mera.inkrot.carshowroom.dto;

import com.mera.inkrot.carshowroom.model.Status;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name="status")
@XmlAccessorType(XmlAccessType.FIELD)
public class StatusDto extends Dto implements Serializable {

    @XmlElement
    private String code;

    @XmlElement
    private String name;

    public StatusDto() { }

    public StatusDto(Long id) {
        setId(id);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static StatusDto getFromEntity(Status status) {
        StatusDto statusDto = new StatusDto();
        statusDto.setId(status.getId());
        statusDto.setCode(status.getCode());
        statusDto.setName(status.getName());
        return statusDto;
    }

    @Override
    public String toString() {
        return "StatusDto{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
