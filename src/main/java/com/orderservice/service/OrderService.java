package com.orderservice.service;

import com.orderservice.web.rest.dto.OrderHdrDTO;

/**
 * Service Interface for managing OrderHdr.
 */
public interface OrderService {

    /**
     * Save a orderHdr.
     * 
     * @param orderHdrDTO the entity to save
     * @return the persisted entity
     */
    OrderHdrDTO save(OrderHdrDTO orderHdrDTO);

    /**
     *  Get the "id" orderHdr.
     *  
     *  @param id the id of the entity
     *  @return the entity
     */
    OrderHdrDTO findOne(Long id);
}
