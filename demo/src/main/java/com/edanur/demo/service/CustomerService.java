package com.edanur.demo.service;

import com.edanur.demo.web.dtomodel.CustomerDto;
import com.edanur.demo.web.request.CustomerRequest;
import com.edanur.demo.dbmodel.Customer;
import com.edanur.demo.repository.AddressRepository;
import com.edanur.demo.repository.CustomerRepository;

import com.edanur.demo.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.dozer.DozerBeanMapper;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomer {

    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;
    private final DozerBeanMapper mapper ;


    public List<CustomerDto> getAllCustomers() {
        return ( customerRepository
                .findAll())
                .stream()
                .map(customer -> mapper.map(customer, CustomerDto.class))
                .collect(Collectors.toList());
    }
    public CustomerDto getCustomerById(int id) throws NotFoundException {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(()->new NotFoundException("There is no customer that id is " + id ));

        return mapper.map(customer, CustomerDto.class);
    }

    public CustomerDto createCustomer(CustomerRequest customerRequest){
        Customer newCustomer = mapper.map(customerRequest,Customer.class);
        customerRepository.save(newCustomer);
        return mapper.map(newCustomer, CustomerDto.class);

    }
    public void deleteCustomer(int id) throws NotFoundException {
        customerRepository.findById(id)
                .orElseThrow(()->new NotFoundException("There is no customer that id is " + id ));
        customerRepository.deleteById(id);
    }
    public void updateCustomer(int id, CustomerRequest customerRequest) throws NotFoundException {
        Customer oldCustomer = customerRepository.findById(id)
                .orElseThrow(()->new NotFoundException("There is no customer that id is " + id ));

        addressRepository.findById(customerRequest.getAddress().getId())
                .orElseThrow(()->new NotFoundException("There is no address that id is " + id ));

        mapper.map(customerRequest,oldCustomer);
        customerRepository.save(oldCustomer);
    }

}
