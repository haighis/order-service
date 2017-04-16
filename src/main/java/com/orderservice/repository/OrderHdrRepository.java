package com.orderservice.repository;

import com.orderservice.domain.OrderHdr;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the OrderHdr entity.
 */
@SuppressWarnings("unused")
public interface OrderHdrRepository extends JpaRepository<OrderHdr,Long> {

}
