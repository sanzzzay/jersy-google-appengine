package com.perpule.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.perpule.bo.Customer;
import com.perpule.params.CustomerFetchParams;
import com.perpule.request.CustomerAddRequest;
import com.perpule.response.CustomerResponse;
import com.perpule.service.CustomerService;


@Service("customerServiceImpl")
public class CustomerServiceImpl implements CustomerService {

	/*
	 * @Autowired CustomerRepository customerRepository;
	 */

	@Override
	public CustomerResponse addCustomers(final CustomerAddRequest customerAddRequest) throws SQLException {

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Customer customer=new Customer();
		try {
			con = DriverManager.getConnection("jdbc:mysql://35.196.69.15:3306/CRM?user=sanjay&password=sanjay");
			String query = "INSERT INTO CUSTOMER(NAME,MOBILE_NUMBER) VALUES(?,?)";
			stmt = con.prepareStatement(query);
			stmt.setLong(1, customerAddRequest.getMobileNumber());
			stmt.setString(2, customerAddRequest.getName());
			int a = stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			customer.setCustomerId(rs.getLong("CUSTOMER_ID"));
			customer.setMobileNumber(customerAddRequest.getMobileNumber());
			customer.setName(customerAddRequest.getName());
		} catch (SQLException e) {
			if (con != null) {
				try {
					System.err.print("Transaction is being rolled back");
					con.rollback();
				} catch (SQLException excep) {
				}
			}
		} finally {
			if (stmt != null) {
				stmt.close();
				con.close();
				rs.close();
			}
		}
		return this.customerToCustomerResponse(customer);
	}

	@Override
	public CustomerResponse fetchCustomer(final CustomerFetchParams customerFetchParams) throws SQLException {
		/*
		 * Customer customer =
		 * customerRepository.findByCustomerId(customerFetchParams.getCustomerId()); if
		 * (customer != null && customer.getCustomerId() != null) { return
		 * this.customerToCustomerResponse(customer); }
		 */
		Connection con = null;
		PreparedStatement stmt = null;
		Customer customer = new Customer();
		ResultSet resultSet = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://35.196.69.15:3306/CRM?user=sanjay&password=sanjay");
			String query = "SELECT * FROM CUSTOMER WHERE CUSTOMER_ID=?";
			stmt = con.prepareStatement(query);
			stmt.setLong(1, customerFetchParams.getCustomerId());
			resultSet = stmt.executeQuery();
			customer.setCustomerId(resultSet.getLong("CUSTOMER_ID"));
			customer.setMobileNumber(resultSet.getLong("MOBILE_NUMBER"));
			customer.setName(resultSet.getString("NAME"));
		} catch (SQLException e) {
			if (con != null) {
				try {
					System.err.print("Transaction is being rolled back");
					con.rollback();
				} catch (SQLException excep) {
				}
			}
		} finally {
			if (stmt != null) {
				stmt.close();
				con.close();
				resultSet.close();
			}
		}
		return this.customerToCustomerResponse(customer);
	}

	private CustomerResponse customerToCustomerResponse(Customer customer) {
		CustomerResponse response = new CustomerResponse();
		response.setCustomerId(customer.getCustomerId());
		response.setMobileNumber(customer.getMobileNumber());
		response.setName(customer.getName());
		return response;
	}

}
