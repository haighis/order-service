package com.orderservice.web.rest.mapper;

import com.orderservice.domain.*;
import com.orderservice.web.rest.dto.OrderDtlDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity OrderDtl and its DTO OrderDtlDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OrderDtlMapper {

    @Mapping(source = "orderHdr.id", target = "orderHdrId")
    OrderDtlDTO orderDtlToOrderDtlDTO(OrderDtl orderDtl);

    List<OrderDtlDTO> orderDtlsToOrderDtlDTOs(List<OrderDtl> orderDtls);

    @Mapping(source = "orderHdrId", target = "orderHdr")
    OrderDtl orderDtlDTOToOrderDtl(OrderDtlDTO orderDtlDTO);

    List<OrderDtl> orderDtlDTOsToOrderDtls(List<OrderDtlDTO> orderDtlDTOs);

    default OrderHdr orderHdrFromId(Long id) {
        if (id == null) {
            return null;
        }
        OrderHdr orderHdr = new OrderHdr();
        orderHdr.setId(id);
        return orderHdr;
    }
}
