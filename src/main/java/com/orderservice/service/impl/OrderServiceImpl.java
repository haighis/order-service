package com.orderservice.service.impl;

import com.orderservice.service.OrderService;
import com.orderservice.domain.OrderDtl;
import com.orderservice.domain.OrderHdr;
import com.orderservice.repository.OrderDtlRepository;
import com.orderservice.repository.OrderHdrRepository;
import com.orderservice.web.rest.dto.OrderDtlDTO;
import com.orderservice.web.rest.dto.OrderHdrDTO;
import com.orderservice.web.rest.mapper.OrderDtlMapper;
import com.orderservice.web.rest.mapper.OrderHdrMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Service Implementation for managing Order Header and associated Order Detail.
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService{

    private final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
    
    @Inject
    private OrderHdrRepository orderHdrRepository;
    
    @Inject 
    private OrderDtlRepository orderDetailRepository;
    
    @Inject
    private OrderHdrMapper orderHdrMapper;
    
    @Inject 
    private OrderDtlMapper orderDtlMapper;
      
    /**
     * Save an Order with Order Header and Order Detail line items.
     * 
     * @param orderHdrDTO the entity to save
     * @return the OrderHdrDTO entity
     */
    @Transactional
    public OrderHdrDTO save(OrderHdrDTO orderHdrDTO) {
        log.debug("Request to save Order Header and Order Details line items : {}", orderHdrDTO);
        
        OrderHdr orderHdr = orderHdrMapper.orderHdrDTOToOrderHdr(orderHdrDTO);
        // Set Order Number
        orderHdr.setOrderNumber(GenerateOrderNumber());
        
        // Set the Order Date
        orderHdr.setOrderDate(LocalDate.now());
        
        Double total = 0d;
        // Calculate Total
        for(OrderDtlDTO order : orderHdrDTO.getLineItemDetails() ) {
        	total += order.getQuantity() * order.getUnitCost();
        }
        
        // Set Total Amount
        BigDecimal dec = new BigDecimal(total, MathContext.DECIMAL64);
        orderHdr.setTotalAmount(dec);
        
        orderHdr = orderHdrRepository.save(orderHdr);
        
        // Convert Detail dto's to OrderDtl domain objects
        List<OrderDtl> listOrderDetails = this.orderDtlMapper.orderDtlDTOsToOrderDtls(Arrays.asList(orderHdrDTO.getLineItemDetails()));
        
        // Set the OrderHdr on Order Detail
        for(OrderDtl orderDetail : listOrderDetails ) {
        	orderDetail.setOrderHdr(orderHdr);
        }
        
        // Save Order Detail line items
        if(!listOrderDetails.isEmpty())
        {
            this.orderDetailRepository.save(listOrderDetails);
        }
        
        // return the dto with order id
        OrderHdrDTO result = orderHdrMapper.orderHdrToOrderHdrDTO(orderHdr);
        return result;
    }
    
    // Generate Order Number
    // TODO refactor to an order number generator service. Need to guarantee uniqueness among order ids
    private int GenerateOrderNumber() {
    	// Set the Order Number
        Random random = new Random(System.nanoTime());
        return random.nextInt(100000);
    }
    
    /**
     *  Get the Order by Order Id.
     *
     *  @param id the order id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public OrderHdrDTO findOne(Long id) {
        log.debug("Request to get OrderHdr : {}", id);
        OrderHdr orderHdr = orderHdrRepository.findOne(id);
        OrderHdrDTO orderHdrDTO = orderHdrMapper.orderHdrToOrderHdrDTO(orderHdr);
        return orderHdrDTO;
    }
}
