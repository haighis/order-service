package com.orderservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * The OrderHdr Entity
 * 
 * Order Header contains one to many Order Detail line items
 */
@Entity
@Table(name = "order_hdr")
public class OrderHdr implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "order_number", nullable = false)
    private Integer orderNumber;

    @NotNull
    @Column(name = "total_amount", precision=10, scale=2, nullable = false)
    private BigDecimal totalAmount;

    @NotNull
    @Column(name = "order_date", nullable = false)
    private LocalDate orderDate;

    @OneToMany(mappedBy = "orderHdr", cascade=CascadeType.PERSIST)
    @JsonIgnore
    private Set<OrderDtl> orderDtls = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public Set<OrderDtl> getOrderDtls() {
        return orderDtls;
    }

    public void setOrderDtls(Set<OrderDtl> orderDtls) {
        this.orderDtls = orderDtls;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OrderHdr orderHdr = (OrderHdr) o;
        if(orderHdr.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, orderHdr.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "OrderHdr{" +
            "id=" + id +
            ", orderNumber='" + orderNumber + "'" +
            ", totalAmount='" + totalAmount + "'" +
            ", orderDate='" + orderDate + "'" +
            '}';
    }
}
