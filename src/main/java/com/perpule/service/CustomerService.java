package com.perpule.service;

import java.sql.SQLException;

import javax.validation.Valid;

import com.perpule.params.CustomerFetchParams;
import com.perpule.request.CustomerAddRequest;
import com.perpule.response.CustomerResponse;


public interface CustomerService {

	CustomerResponse addCustomers(@Valid CustomerAddRequest customerAddRequest) throws SQLException;

	CustomerResponse fetchCustomer(@Valid CustomerFetchParams customerFetchParams)throws SQLException;

}
