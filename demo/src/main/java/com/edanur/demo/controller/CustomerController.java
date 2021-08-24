package com.edanur.demo.controller;

import com.edanur.demo.web.dtomodel.CustomerDto;
import com.edanur.demo.web.request.CustomerRequest;
import com.edanur.demo.service.CustomerService;
import com.edanur.demo.web.response.CustomerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.dozer.DozerBeanMapper;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customer")
@Api(value = "Customer Api documentation")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final DozerBeanMapper mapper ;


    @GetMapping
    @ApiOperation(value = "All customers getting method")
    public List<CustomerResponse> getAllCustomers() {
        return (customerService.getAllCustomers())
                .stream()
                .map(customer -> mapper.map(customer,CustomerResponse.class))
                .collect(Collectors.toList());
    }


    @GetMapping("/{customerId}")
    @ApiOperation(value = "Getting a customer with id")
    public CustomerResponse getCustomer(@PathVariable int customerId){
        return mapper.map(customerService.getCustomerById(customerId),CustomerResponse.class);
    }

    @PostMapping
    @ApiOperation(value = "New Customer creating method")
    public CustomerResponse createCustomer(@RequestBody CustomerRequest customerRequest) {
        return mapper.map(customerService.createCustomer(customerRequest),CustomerResponse.class);
    }


    @DeleteMapping("/{customerId}")
    @ApiOperation(value = "Customer deleting method")
    public void deleteCustomer(@PathVariable("customerId") int customerId){
        customerService.deleteCustomer(customerId);
    }


    @PutMapping("/{customerId}")
    @ApiOperation(value = "Customer updating method")
    public void updateCustomer(@PathVariable("customerId") int customerId,
                                   @RequestBody CustomerRequest customerRequest){
       customerService.updateCustomer(customerId, customerRequest);
    }
}
