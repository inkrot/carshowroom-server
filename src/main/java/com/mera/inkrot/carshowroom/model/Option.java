package com.mera.inkrot.carshowroom.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "options")
@XmlRootElement(name = "option")
public class Option extends BaseEntity {

    private String name;

    public Option(Long id) {
        setId(id);
    }

    public Option() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Option{" +
                "name='" + name + '\'' +
                '}';
    }
}
