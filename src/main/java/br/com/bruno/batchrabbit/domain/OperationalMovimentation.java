package br.com.bruno.batchrabbit.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Bruno Andrade
 * Dia: 05/10/2019
 **/
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
public class OperationalMovimentation implements Serializable {

    @Id
    @GeneratedValue
    private Long idTransaction;

    private String codTransaction;
    private String codCostumer;
    private Integer accountFrom;
    private Integer accountTo;
    private BigDecimal value;


    public OperationalMovimentation() {
    }

    public OperationalMovimentation(String codTransaction, String codCostumer, Integer accountFrom, Integer accountTo, BigDecimal value) {
        this.codTransaction = codTransaction;
        this.codCostumer = codCostumer;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.value = value;
    }

    public String getCodTransaction() {
        return codTransaction;
    }

    public void setCodTransaction(String codTransaction) {
        this.codTransaction = codTransaction;
    }

    public String getCodCostumer() {
        return codCostumer;
    }

    public void setCodCostumer(String codCostumer) {
        this.codCostumer = codCostumer;
    }

    public Integer getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(Integer accountFrom) {
        this.accountFrom = accountFrom;
    }

    public Integer getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(Integer accountTo) {
        this.accountTo = accountTo;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Long getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(Long idTransaction) {
        this.idTransaction = idTransaction;
    }

    @Override
    public String toString() {
        return "OperationalMovimentation{" +
                "idTransaction=" + idTransaction +
                ", codTransaction='" + codTransaction + '\'' +
                ", codCostumer='" + codCostumer + '\'' +
                ", accountFrom=" + accountFrom +
                ", accountTo=" + accountTo +
                ", value=" + value +
                '}';
    }
}
