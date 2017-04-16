// package com.orderservice.easymock;

// import java.time.LocalDate;

// import org.junit.Before;
// import org.junit.Test;
// import static org.easymock.EasyMock.createStrictMock;
// import static org.easymock.EasyMock.expect;
// import static org.easymock.EasyMock.replay;
// import static org.easymock.EasyMock.verify;
// import static org.easymock.EasyMock.eq;

// import com.orderservice.domain.OrderHdr;
// import com.orderservice.repository.OrderDtlRepository;
// import com.orderservice.repository.OrderHdrRepository;
// import com.orderservice.service.impl.OrderServiceImpl;
// import com.orderservice.web.rest.dto.OrderHdrDTO;
// import com.orderservice.web.rest.mapper.OrderDtlMapper;
// import com.orderservice.web.rest.mapper.OrderHdrMapper;

// import junit.framework.TestCase;

// import static org.assertj.core.api.Assertions.assertThat;
// import static org.junit.Assert.*;

// public class OrderServiceImplUnitTest extends TestCase {
	
// 	private OrderServiceImpl orderService;
// 	private OrderHdrRepository mockOrderHeaderRepository;
// 	private OrderHdrMapper mockOrderHeaderMapper;
	
// 	//@Before
// 	@Override
//     public void setUp() {
// 		orderService = new OrderServiceImpl();
//         mockOrderHeaderRepository = createStrictMock(OrderHdrRepository.class);
//         mockOrderHeaderMapper = createStrictMock(OrderHdrMapper.class);
//         orderService.setOrderHeaderMapper(mockOrderHeaderMapper);
//         orderService.setOrderHeaderRepository(mockOrderHeaderRepository);
//     }
  
// 	/**
//    	* A test that will findOne Order to assert that findOne 
//    	* will find a single Order by order id
//    	* in Order Service.
//    	* @throws Exception
//    	*/
// 	@Test
// 	public void OrderServiceImpl_CanfindOne() throws Exception 
// 	{
// 		OrderHdr results = new OrderHdr();
// 		results.setOrderNumber(1);
// 		OrderHdrDTO resultDTO = new OrderHdrDTO();
		
// 		expect(mockOrderHeaderRepository.findOne(1l)).andReturn(results);
	
// 		replay(mockOrderHeaderRepository);
		
// 		OrderHdrDTO actualDTO = orderService.findOne(1l);
		
// 		assertThat(actualDTO.getOrderNumber()).isEqualTo(1);
// 	   	verify(mockOrderHeaderRepository);
	   
// 		// Arrange / Setup
// //		LocalDate expectedDate = LocalDate.now();
// //	  
// //		OrderHdrDTO expected = new OrderHdrDTO();
// //		expected.setOrderDate(expectedDate);
	  
// 		//assertNotNull("Service Request DTO is not null after findOne", actualDTO);
// 	}
// }


