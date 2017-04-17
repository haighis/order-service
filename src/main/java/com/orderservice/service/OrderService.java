package com.orderservice.service;

import com.orderservice.web.rest.dto.OrderHdrDTO;

/**
 * Service Interface for managing Order Header and associated Order Detail..
 */
public interface OrderService {
	
    /**
     * Save an Order with Order Header and Order Detail line items.
     * 
     * @param orderHdrDTO the entity to save
     * @return the persisted entity
     */
    OrderHdrDTO save(OrderHdrDTO orderHdrDTO);

    /**
     *  Get the Order by Order Id.
     *  
     *  @param id the order id of the entity
     *  @return the entity
     */
    OrderHdrDTO findOne(Long id);
}
