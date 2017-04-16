package com.orderservice.web.rest.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;


/**
 * A DTO for the OrderDtl entity.
 * Order Detail line item for an Order Header.
 * Order Detail line items map one to
 * one with cart line items.
 */
public class OrderDtlDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer orderLineNumber;

    @NotNull
    @Size(max = 64)
    private String sku;

    @NotNull
    @Max(value = 8)
    private Integer quantity;

    @NotNull
    private Double unitCost;

    private Double offeredCost;

    private Double salesTaxAmount;

    private Long orderHdrId;
    
    /**
     * The Order Id
     * @return The Order Id
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * The Order Detail Line Number
     * @return The Order Line Number
     */
    public Integer getOrderLineNumber() {
        return orderLineNumber;
    }

    public void setOrderLineNumber(Integer orderLineNumber) {
        this.orderLineNumber = orderLineNumber;
    }
    
    /**
     * The Order Detail Sku
     * @return The Sku
     */
    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }
    
    /**
     * The Order Detail Quantity
     * @return The Quantity
     */
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
    /**
     * The Order Detail Unit Cost
     * @return The Unit Cost
     */
    public Double getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(Double unitCost) {
        this.unitCost = unitCost;
    }
    
    /**
     * The Order Detail override Cost
     * @return The Offered Cost
     */
    public Double getOfferedCost() {
        return offeredCost;
    }

    public void setOfferedCost(Double offeredCost) {
        this.offeredCost = offeredCost;
    }
    
    /**
     * The Order Detail Sales Tax Amount
     * @return The Sales Tax Amount
     */
    public Double getSalesTaxAmount() {
        return salesTaxAmount;
    }

    public void setSalesTaxAmount(Double salesTaxAmount) {
        this.salesTaxAmount = salesTaxAmount;
    }

    /**
     * The Order Id for the Order Detail line item
     * @return The Order Header Id
     */
    public Long getOrderHdrId() {
        return orderHdrId;
    }

    public void setOrderHdrId(Long orderHdrId) {
        this.orderHdrId = orderHdrId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OrderDtlDTO orderDtlDTO = (OrderDtlDTO) o;

        if ( ! Objects.equals(id, orderDtlDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "OrderDtlDTO{" +
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
