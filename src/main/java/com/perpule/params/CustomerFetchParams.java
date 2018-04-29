package com.perpule.params;

import javax.validation.constraints.NotNull;
import javax.ws.rs.PathParam;

public class CustomerFetchParams {

	@NotNull(message= "Customer Id should not be null")
	@PathParam("customerId")
	private Long customerId;

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
}
