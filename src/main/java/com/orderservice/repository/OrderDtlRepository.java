package com.orderservice.repository;

import com.orderservice.domain.OrderDtl;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the OrderDtl entity.
 */
@SuppressWarnings("unused")
public interface OrderDtlRepository extends JpaRepository<OrderDtl,Long> {

}
