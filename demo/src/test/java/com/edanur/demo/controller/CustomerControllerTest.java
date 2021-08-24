package com.edanur.demo.controller;

import com.edanur.demo.dbmodel.Address;
import com.edanur.demo.web.dtomodel.AddressDto;
import com.edanur.demo.web.dtomodel.CustomerDto;
import com.edanur.demo.service.CustomerService;
import com.edanur.demo.web.request.CustomerRequest;
import com.edanur.demo.web.response.AddressResponse;
import com.edanur.demo.web.response.CustomerResponse;
import org.dozer.DozerBeanMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @Mock
    private CustomerService customerService;
    @Mock
    private DozerBeanMapper mapper;
    @InjectMocks
    private CustomerController customerController;


    @Test
    void getAllCustomers() {
        List<CustomerDto> allCustomersDTO= new ArrayList<CustomerDto>();
        final Address address= new Address(1,"turkey","manisa","utku",70,"0000");
        final CustomerDto cADTO=new CustomerDto(1,"eda","hassümer",address,"ednr@hotmail.com","25545");
        allCustomersDTO.add(cADTO);
        List<CustomerResponse> allCustomersResponse= allCustomersDTO
                .stream()
                .map(customers -> mapper.map(customers, CustomerResponse.class))
                .collect(Collectors.toList());

        given(customerService.getAllCustomers()).willReturn(allCustomersDTO);
        final List<CustomerResponse> expected = customerController.getAllCustomers();
        assertThat(expected).isNotNull();
        assertEquals(expected,allCustomersResponse);
    }

    @Test
    void getCustomer(){
        final Address address= new Address(1,"turkey","manisa","utku",70,"0000");
        final CustomerDto cADTO=new CustomerDto(1,"eda","hassümer",address,"ednr@hotmail.com","25545");
        final CustomerResponse customerResponse= new CustomerResponse(1,"eda","hassümer",address,"ednr@hotmail.com","25545");
        given(mapper.map(cADTO,CustomerResponse.class)).willReturn(customerResponse);
        given(customerService.getCustomerById(1)).willReturn(cADTO);
        final CustomerResponse expected = customerController.getCustomer(1);
        assertThat(expected).isNotNull();
        assertEquals(expected,customerResponse);
    }

    @Test
    void createCustomer(){
        final Address address= new Address(1,"turkey","manisa","utku",70,"0000");
        final CustomerRequest customerRequest= new CustomerRequest("eda","hassümer",address,"ednr@hotmail.com","25545");
        final CustomerDto cADTO=new CustomerDto(1,"eda","hassümer",address,"ednr@hotmail.com","25545");
        final CustomerResponse customerResponse=new CustomerResponse(1,"eda","hassümer",address,"ednr@hotmail.com","25545");
        given(customerService.createCustomer(customerRequest)).willReturn(cADTO);
        given(mapper.map(customerService.createCustomer(customerRequest),CustomerResponse.class)).willReturn(customerResponse);

        final CustomerResponse expected = customerController.createCustomer(customerRequest);
        assertThat(expected).isNotNull();
        assertEquals(expected,customerResponse);
    }

    @Test
    void deleteCustomer() {
        customerController.deleteCustomer(1);
        verify(customerService,times(1)).deleteCustomer(1);
    }

    @Test
    void updateCustomer(){
        final Address address= new Address(1,"turkey","manisa","utku",70,"0000");
        final CustomerRequest customerRequest= new CustomerRequest("eda","hassümer",address,"ednr@hotmail.com","25545");
        customerController.updateCustomer(1,customerRequest);
        verify(customerService).updateCustomer(1,customerRequest);

    }
}