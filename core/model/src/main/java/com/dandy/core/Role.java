package com.dandy.core;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "role")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)

public class Role {

    @Id
    @Column(name = "roleId")
    private int roleId;

    @Column(name = "pos")
    private String pos;

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
    public void setPos(String pos) {
        this.pos = pos;
    }
    public int getRoleId() {
        return this.roleId;
    }
    public String getPos() {
        return this.pos;
    }
}
