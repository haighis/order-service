package com.orderservice.web.rest;

import com.orderservice.OrderserviceApp;
import com.orderservice.domain.OrderHdr;
import com.orderservice.repository.OrderHdrRepository;
import com.orderservice.service.OrderService;
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
 * Test class for the OrderHdrResource REST controller.
 *
 * @see OrderHdrResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = OrderserviceApp.class)
@WebAppConfiguration
@IntegrationTest
public class OrderResourceIntTest {
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

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restOrderHdrMockMvc;

    private OrderHdr orderHdr;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        OrderResource orderHdrResource = new OrderResource();
        ReflectionTestUtils.setField(orderHdrResource, "orderHdrService", orderHdrService);
        ReflectionTestUtils.setField(orderHdrResource, "orderHdrMapper", orderHdrMapper);
        this.restOrderHdrMockMvc = MockMvcBuilders.standaloneSetup(orderHdrResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        orderHdr = new OrderHdr();
        orderHdr.setOrderNumber(DEFAULT_ORDER_NUMBER);
        orderHdr.setTotalAmount(DEFAULT_TOTAL_AMOUNT);
        orderHdr.setOrderDate(DEFAULT_ORDER_DATE);
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
    	
        int databaseSizeBeforeCreate = orderHdrRepository.findAll().size();

        // Act
        // Create the OrderHdr
        restOrderHdrMockMvc.perform(post("/api/orders").contentType(TestUtil.APPLICATION_JSON_UTF8).content(TestUtil.convertObjectToJsonBytes(orderHdrDTO))).andExpect(status().isCreated());
        
        // Assert
        // Validate the OrderHdr in the database
        List<OrderHdr> orderHdrs = orderHdrRepository.findAll();
        assertThat(orderHdrs).hasSize(databaseSizeBeforeCreate + 1);
        OrderHdr testOrderHdr = orderHdrs.get(orderHdrs.size() - 1);
        //assertThat(testOrderHdr.getOrderDtls().size()).isEqualTo(1);
        assertThat(testOrderHdr.getOrderNumber()).isNotNull();
        assertThat(testOrderHdr.getTotalAmount()).isLessThanOrEqualTo(DEFAULT_TOTAL_AMOUNT_CREATE);
        assertThat(testOrderHdr.getOrderDate()).isNotNull();
    }

    @Test
    @Transactional
    public void checkOrderDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderHdrRepository.findAll().size();
        // set the field null
        orderHdr.setOrderDate(null);

        // Create the OrderHdr, which fails.
        OrderHdrDTO orderHdrDTO = orderHdrMapper.orderHdrToOrderHdrDTO(orderHdr);

        restOrderHdrMockMvc.perform(post("/api/orders")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(orderHdrDTO)))
                .andExpect(status().isBadRequest());

        List<OrderHdr> orderHdrs = orderHdrRepository.findAll();
        assertThat(orderHdrs).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getOrder() throws Exception {
        // Initialize the database
        orderHdrRepository.saveAndFlush(orderHdr);

        // Get the orderHdr
        restOrderHdrMockMvc.perform(get("/api/orders/{id}", orderHdr.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(orderHdr.getId().intValue()))
            .andExpect(jsonPath("$.orderNumber").value(DEFAULT_ORDER_NUMBER))
            .andExpect(jsonPath("$.totalAmount").value(DEFAULT_TOTAL_AMOUNT.intValue()))
            .andExpect(jsonPath("$.orderDate").value(DEFAULT_ORDER_DATE.toString()));
    }
    
    @Test
    @Transactional
    public void getNonExistingOrder() throws Exception {
        // Get the orderHdr
        restOrderHdrMockMvc.perform(get("/api/orders/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }
}
