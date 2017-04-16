package com.orderservice.web.rest.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the OrderHdr entity.
 * Order Header (OrderHdr) contains the common or aggregate 
 * attributes for an Order.  
 * Contains Order Number, 
 * Total Amount (aggregate amount for all line items), 
 * Order Date and Order Details line items.
 */
public class OrderHdrDTO implements Serializable {

    private Long id;
    
    @Max(value = 10)
    private Integer orderNumber;

    @Max(value = 24)
    private BigDecimal totalAmount;

    private LocalDate orderDate;
    
    @NotNull
    public OrderDtlDTO[] lineItemDetails;

    
    /**
     * The Order Id
     * @return The order id
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * The Order Number .
     * @return The order number
     */
    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }
    
    /**
     * The Order Total Amount.
     * @return the total amount
     */
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    /**
     * The Order Date.
     * @return the order date
     */
    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }
    
    /**
     * Order Detail line items for an Order.
     * @return the order detail line items
     */
    public OrderDtlDTO[] getLineItemDetails() {
        return lineItemDetails;
    }

    public void setLineItemDetails(OrderDtlDTO[] lineItemDetails) {
        this.lineItemDetails = lineItemDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OrderHdrDTO orderHdrDTO = (OrderHdrDTO) o;

        if ( ! Objects.equals(id, orderHdrDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "OrderHdrDTO{" +
            "id=" + id +
            ", orderNumber='" + orderNumber + "'" +
            ", totalAmount='" + totalAmount + "'" +
            ", orderDate='" + orderDate + "'" +
            '}';
    }
}
