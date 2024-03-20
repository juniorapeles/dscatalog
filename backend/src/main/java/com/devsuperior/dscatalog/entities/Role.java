package com.devsuperior.dscatalog.entities;

public class Role {

    private Long id;
    private Long authority;

    public Role() {
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
}
