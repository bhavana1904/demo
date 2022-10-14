package com.example.project.Domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Total {
    @Id
    private Long userid;
    private Long total;

    public Total(Long userid, Long total) {
        this.userid = userid;
        this.total = total;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
