package com.dscatalog.dscatalog.entities;

import java.io.Serializable;
import java.util.Objects;

public class Role implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long authority;

    public Role(){

    }

    public Role(Long id, Long authority) {
        this.id = id;
        this.authority = authority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAuthority() {
        return authority;
    }

    public void setAuthority(Long authority) {
        this.authority = authority;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
