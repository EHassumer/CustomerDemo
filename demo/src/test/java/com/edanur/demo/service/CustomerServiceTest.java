package com.edanur.demo.service;

import com.edanur.demo.dbmodel.Address;
import com.edanur.demo.dbmodel.Customer;
import com.edanur.demo.web.dtomodel.CustomerDto;
import com.edanur.demo.web.request.CustomerRequest;
import com.edanur.demo.repository.AddressRepository;
import com.edanur.demo.repository.CustomerRepository;
import com.edanur.demo.exception.NotFoundException;
import org.dozer.DozerBeanMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private AddressRepository addressRepository;
    @Mock
    private DozerBeanMapper mapper;
    @InjectMocks
    private CustomerService customerService;

    @Test
    void getAllCustomers() {
        List<Customer> allCustomers= new ArrayList<Customer>();
        final Address address = new Address(1,"turkey","manisa","utku",70,"0000");
        final Customer customer= new Customer(1,"eda","hassümer", address,"ednr@hotmail.com","25545");
        allCustomers.add(customer);

        List<CustomerDto> allCustomersDTO= allCustomers
                .stream()
                .map(customers -> mapper.map(customers, CustomerDto.class))
                .collect(Collectors.toList());

        given(customerRepository.findAll()).willReturn(allCustomers);
        final List<CustomerDto> expected = customerService.getAllCustomers();
        assertThat(expected).isNotNull();
        assertEquals(expected,allCustomersDTO);
    }
    @Test
    void getCustomerById(){
        final Address address = new Address(1,"turkey","manisa","utku",70,"0000");
        final Customer customer= new Customer(1,"eda","hassümer", address,"ednr@hotmail.com","25545");
        final CustomerDto cdto= new CustomerDto(1,"eda","hassümer", address,"ednr@hotmail.com","25545");
        given(customerRepository.findById(1)).willReturn(java.util.Optional.of(customer));
        given(mapper.map(customer,CustomerDto.class)).willReturn(cdto);
        final CustomerDto expected = customerService.getCustomerById(1);
        assertThat(expected).isNotNull();
        assertEquals(expected,cdto);
    }

    @Test
    void createCustomer() {
        final Address address = new Address(1,"turkey","manisa","utku",70,"0000");
        Customer customer= new Customer(1,"eda","hassümer", address,"ednr@hotmail.com","25545");
        final CustomerRequest customerRequest= new CustomerRequest("eda","hassümer", address,"ednr@hotmail.com","25545");
        final CustomerDto customerDto= new CustomerDto(1,"eda","hassümer", address,"ednr@hotmail.com","25545");
        given(mapper.map(customerRequest,Customer.class)).willReturn(customer);
        given(mapper.map(customer,CustomerDto.class)).willReturn(customerDto);
        given(customerRepository.save(customer)).willReturn(customer);
        final CustomerDto saved= customerService.createCustomer(customerRequest);
        assertThat(saved).isNotNull();
        assertEquals(saved,customerDto);

    }

    @Test
    void deleteCustomer(){
        int id=1;
        final Address address = new Address(1,"turkey","manisa","utku",70,"0000");
        Customer customer= new Customer(id,"eda","hassümer", address,"ednr@hotmail.com","25545");
        given(customerRepository.findById(id)).willReturn(java.util.Optional.of(customer));
        customerService.deleteCustomer(id);
        verify(customerRepository,times(1)).deleteById(id);
    }

    @Test
    void updateCustomer()  {

        final Address address = new Address(1,"turkey","manisa","utku",70,"0000");
        Customer customer= new Customer(1,"eda","hassümer", address,"ednr@hotmail.com","25545");
        final CustomerRequest customerRequest= new CustomerRequest ("eda","hassümer", address,"ednr@hotmail.com","25545");
        Customer newCustomer= new Customer(1,"eda","hassümer", address,"ednr@hotmail.com","25545");
        given(customerRepository.findById(1)).willReturn(java.util.Optional.of(customer));
        given(addressRepository.findById(customerRequest.getAddress().getId())).willReturn(java.util.Optional.of(address));
        given(mapper.map(customerRequest,Customer.class)).willReturn(customer);
        customerService.updateCustomer(1,customerRequest);
        verify(customerRepository).save(newCustomer);
    }


}