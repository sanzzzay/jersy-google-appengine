package com.perpule.Controller;

import java.sql.SQLException;

import javax.validation.Valid;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.perpule.params.CustomerFetchParams;
import com.perpule.request.CustomerAddRequest;
import com.perpule.response.CustomerResponse;
import com.perpule.service.CustomerService;
import com.perpule.util.UserProfileUrls;


@Path("/v1")
public class CustomerController {

	@Autowired
	CustomerService customerService;
	
	@POST
	@Path(UserProfileUrls.CUSTOMER)
	public CustomerResponse addCustomers(@Valid final CustomerAddRequest customerAddRequest) throws SQLException{
		return customerService.addCustomers(customerAddRequest);
	}	
	
	@GET
	@Path(UserProfileUrls.CUSTOMER_ID)
	public CustomerResponse fetchCustomer(@Valid @BeanParam CustomerFetchParams customerFetchParams) throws SQLException{
		return customerService.fetchCustomer(customerFetchParams);
	}
	
}
