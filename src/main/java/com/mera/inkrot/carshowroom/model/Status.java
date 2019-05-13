package com.mera.inkrot.carshowroom.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "order_statuses")
public class Status extends BaseEntity {

    private String code;

    private String name;

    public Status() { }

    public Status(Long id) {
        super.setId(id);
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
}
