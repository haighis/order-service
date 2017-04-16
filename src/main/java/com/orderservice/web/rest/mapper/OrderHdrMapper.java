package com.orderservice.web.rest.mapper;

import com.orderservice.domain.*;
import com.orderservice.web.rest.dto.OrderHdrDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity OrderHdr and its DTO OrderHdrDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OrderHdrMapper {

    OrderHdrDTO orderHdrToOrderHdrDTO(OrderHdr orderHdr);

    List<OrderHdrDTO> orderHdrsToOrderHdrDTOs(List<OrderHdr> orderHdrs);

    @Mapping(target = "orderDtls")
    OrderHdr orderHdrDTOToOrderHdr(OrderHdrDTO orderHdrDTO);

    List<OrderHdr> orderHdrDTOsToOrderHdrs(List<OrderHdrDTO> orderHdrDTOs);
}
