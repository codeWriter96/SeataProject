package com.wang.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author 1312
 */
public class Account implements Serializable {

    private static final long serialVersionUID = 4213129654133628293L;
    private Long id;

    private Long userId;

    private BigDecimal total;

    private BigDecimal  used;

    private BigDecimal  residue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal  getTotal() {
        return total;
    }

    public void setTotal(BigDecimal  total) {
        this.total = total;
    }

    public BigDecimal  getUsed() {
        return used;
    }

    public void setUsed(BigDecimal  used) {
        this.used = used;
    }

    public BigDecimal  getResidue() {
        return residue;
    }

    public void setResidue(BigDecimal  residue) {
        this.residue = residue;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", userId=" + userId +
                ", total=" + total +
                ", used=" + used +
                ", residue=" + residue +
                '}';
    }
}