package com.edanur.demo.service;

import com.edanur.demo.web.dtomodel.CustomerDto;
import com.edanur.demo.web.request.CustomerRequest;
import javassist.NotFoundException;

import java.util.List;

public interface ICustomer {
    public List<CustomerDto> getAllCustomers();
    public CustomerDto getCustomerById(int id) throws NotFoundException;
    public CustomerDto createCustomer(CustomerRequest customer);
    public void deleteCustomer(int id) throws NotFoundException;
    public void updateCustomer(int id, CustomerRequest customer) throws NotFoundException;
}
