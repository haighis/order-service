package com.orderservice.service;

import com.orderservice.OrderserviceApp;
import com.orderservice.domain.OrderHdr;
import com.orderservice.repository.OrderHdrRepository;
import com.orderservice.service.OrderService;
import com.orderservice.web.rest.OrderResource;
import com.orderservice.web.rest.dto.OrderDtlDTO;
import com.orderservice.web.rest.dto.OrderHdrDTO;
import com.orderservice.web.rest.mapper.OrderHdrMapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.time.LocalDate;
import java.time.ZoneId;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the Order Service Implemenation.
 *
 * @see OrderService
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = OrderserviceApp.class)
@WebAppConfiguration
@IntegrationTest
public class OrderServiceIntTest {
    private static final Integer DEFAULT_ORDER_NUMBER = 10001;
    private static final Integer UPDATED_ORDER_NUMBER = 20000;
    
    private static final Integer DEFAULT_QUANTITY = 2;
    private static final Double DEFAULT_UNIT_COST = 24d;
    private static final String DEFAULT_SKU = "IPHONE";
    
    private static final BigDecimal DEFAULT_TOTAL_AMOUNT_CREATE = new BigDecimal(49);
    private static final BigDecimal DEFAULT_TOTAL_AMOUNT = new BigDecimal(48);
    private static final BigDecimal UPDATED_TOTAL_AMOUNT = new BigDecimal(23);

    private static final LocalDate DEFAULT_ORDER_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ORDER_DATE = LocalDate.now(ZoneId.systemDefault());

    @Inject
    private OrderHdrRepository orderHdrRepository;

    @Inject
    private OrderHdrMapper orderHdrMapper;

    @Inject
    private OrderService orderHdrService;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        OrderResource orderHdrResource = new OrderResource();
        ReflectionTestUtils.setField(orderHdrResource, "orderHdrService", orderHdrService);
        ReflectionTestUtils.setField(orderHdrResource, "orderHdrMapper", orderHdrMapper);
    }

    @Test
    @Transactional
    public void createOrderHdr() throws Exception {
    	// Arrange
    	OrderHdrDTO orderHdrDTO = new OrderHdrDTO();
    	
    	OrderDtlDTO lineItemDetailDTO = new OrderDtlDTO();
    	lineItemDetailDTO.setOrderLineNumber(1);
    	lineItemDetailDTO.setQuantity(DEFAULT_QUANTITY);
    	lineItemDetailDTO.setSku(DEFAULT_SKU);
    	lineItemDetailDTO.setUnitCost(DEFAULT_UNIT_COST);
    	
    	OrderDtlDTO[] lineItems = { lineItemDetailDTO };
    	
    	orderHdrDTO.setLineItemDetails(lineItems);
    	
    	// Act
    	OrderHdrDTO actualOrderHeaderDTO = orderHdrService.save(orderHdrDTO);

    	OrderHdr testOrderHdr = this.orderHdrRepository.getOne(actualOrderHeaderDTO.getId());
    	
        // Assert
        // Validate the OrderHdr being returned from business layer
        assertThat(testOrderHdr.getOrderNumber()).isNotNull();
        assertThat(testOrderHdr.getTotalAmount()).isLessThanOrEqualTo(DEFAULT_TOTAL_AMOUNT_CREATE);
        assertThat(testOrderHdr.getOrderDate()).isNotNull();
    }

    @Test
    @Transactional
    public void getOrder() throws Exception {
    	// Arrange
    	OrderHdrDTO orderHdrDTO = new OrderHdrDTO();
    	
    	OrderDtlDTO lineItemDetailDTO = new OrderDtlDTO();
    	lineItemDetailDTO.setOrderLineNumber(1);
    	lineItemDetailDTO.setQuantity(DEFAULT_QUANTITY);
    	lineItemDetailDTO.setSku(DEFAULT_SKU);
    	lineItemDetailDTO.setUnitCost(DEFAULT_UNIT_COST);
    	
    	OrderDtlDTO[] lineItems = { lineItemDetailDTO };
    	
    	orderHdrDTO.setLineItemDetails(lineItems);
    	
    	OrderHdrDTO testOrderHeaderDTO = orderHdrService.save(orderHdrDTO);
    	
    	// Act
    	OrderHdrDTO actualHeaderOrderDTO = orderHdrService.findOne(testOrderHeaderDTO.getId());
    	
        // Assert
        // Validate the OrderHdr being returned from business layer
    	assertThat(actualHeaderOrderDTO).isNotNull();
        assertThat(actualHeaderOrderDTO.getOrderNumber()).isNotNull();
        assertThat(actualHeaderOrderDTO.getTotalAmount()).isLessThanOrEqualTo(DEFAULT_TOTAL_AMOUNT_CREATE);
        assertThat(actualHeaderOrderDTO.getOrderDate()).isNotNull();
    }
}
