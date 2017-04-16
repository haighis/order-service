package com.orderservice.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.orderservice.service.OrderService;
import com.orderservice.web.rest.util.HeaderUtil;
import com.orderservice.web.rest.dto.OrderHdrDTO;
import com.orderservice.web.rest.mapper.OrderHdrMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

/**
 * REST controller for managing Order.
* Contains Verbs on Order Resource for:
 * - POST - create an Order
 * - GET with Id - gets a single Order
 */
@RestController
@RequestMapping("/api")
public class OrderResource {

    private final Logger log = LoggerFactory.getLogger(OrderResource.class);
        
    @Inject
    private OrderService orderHdrService;
    
    @Inject
    private OrderHdrMapper orderHdrMapper;
    
    /**
     * POST  /orders : Create a new Order.
     *
     * Order Header to contain Order Detail Line Items
     *
     * @param orderHdrDTO the orderHdrDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new orderHdrDTO, or with status 400 (Bad Request) if the orderHdr has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/orders",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<OrderHdrDTO> createOrder(@Valid @RequestBody OrderHdrDTO orderHdrDTO) throws URISyntaxException {
        log.debug("REST request to save OrderHdr : {}", orderHdrDTO);
        if (orderHdrDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("orderHdr", "idexists", "A new Order cannot already have an Order ID")).body(null);
        }
        OrderHdrDTO result = orderHdrService.save(orderHdrDTO);
        return ResponseEntity.created(new URI("/api/orders/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("orderHdr", result.getId().toString()))
            .body(result);
    }

    /**
     * GET  /orders/:id Gets an Order by Order Id.
     *
     * @param id the Order Id of the orderHdrDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the orderHdrDTO, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/orders/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<OrderHdrDTO> getOrder(@PathVariable Long id) {
        log.debug("REST request to get OrderHdr : {}", id);
        OrderHdrDTO orderHdrDTO = orderHdrService.findOne(id);
        return Optional.ofNullable(orderHdrDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
