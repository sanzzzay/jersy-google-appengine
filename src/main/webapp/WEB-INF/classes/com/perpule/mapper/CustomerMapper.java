package com.perpule.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.perpule.bo.Customer;
import com.perpule.request.CustomerAddRequest;
import com.perpule.response.CustomerResponse;

@Mapper(componentModel="spring")
public interface CustomerMapper {

	CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

	Customer customerAddRequestToCustomer(CustomerAddRequest customerAddRequest);

	CustomerResponse customerToCustomerResponse(Customer customer);
}
