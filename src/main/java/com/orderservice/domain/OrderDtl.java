package com.orderservice.domain;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A OrderDtl Domain Entity
 */
@Entity
@Table(name = "order_dtl")
public class OrderDtl implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "order_line_number", nullable = false)
    private Integer orderLineNumber;

    @NotNull
    @Size(max = 64)
    @Column(name = "sku", length = 64, nullable = false)
    private String sku;

    @NotNull
    @Max(value = 9)
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @NotNull
    @Column(name = "unit_cost", nullable = false)
    private Double unitCost;

    @Column(name = "offered_cost")
    private Double offeredCost;

    @Column(name = "sales_tax_amount")
    private Double salesTaxAmount;

    @ManyToOne
    private OrderHdr orderHdr;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOrderLineNumber() {
        return orderLineNumber;
    }

    public void setOrderLineNumber(Integer orderLineNumber) {
        this.orderLineNumber = orderLineNumber;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(Double unitCost) {
        this.unitCost = unitCost;
    }

    public Double getOfferedCost() {
        return offeredCost;
    }

    public void setOfferedCost(Double offeredCost) {
        this.offeredCost = offeredCost;
    }

    public Double getSalesTaxAmount() {
        return salesTaxAmount;
    }

    public void setSalesTaxAmount(Double salesTaxAmount) {
        this.salesTaxAmount = salesTaxAmount;
    }

    public OrderHdr getOrderHdr() {
        return orderHdr;
    }

    public void setOrderHdr(OrderHdr orderHdr) {
        this.orderHdr = orderHdr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OrderDtl orderDtl = (OrderDtl) o;
        if(orderDtl.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, orderDtl.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "OrderDtl{" +
            "id=" + id +
            ", orderLineNumber='" + orderLineNumber + "'" +
            ", sku='" + sku + "'" +
            ", quantity='" + quantity + "'" +
            ", unitCost='" + unitCost + "'" +
            ", offeredCost='" + offeredCost + "'" +
            ", salesTaxAmount='" + salesTaxAmount + "'" +
            '}';
    }
}
