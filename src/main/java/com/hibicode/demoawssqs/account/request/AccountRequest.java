package com.hibicode.demoawssqs.account.request;

import java.math.BigDecimal;

public class AccountRequest {

    private Integer id;
    private BigDecimal amount;

    public AccountRequest() {
    }

    public AccountRequest(Integer id, BigDecimal amount) {
        this.id = id;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
