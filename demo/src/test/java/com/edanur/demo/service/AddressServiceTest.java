package com.edanur.demo.service;

import com.edanur.demo.dbmodel.Address;
import com.edanur.demo.dbmodel.Customer;
import com.edanur.demo.web.dtomodel.AddressDto;
import com.edanur.demo.web.dtomodel.CustomerDto;
import com.edanur.demo.web.request.AddressRequest;
import com.edanur.demo.repository.AddressRepository;
import com.edanur.demo.web.request.CustomerRequest;
import javassist.NotFoundException;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddressServiceTest {
    @Mock
    private AddressRepository addressRepository;
    @Mock
    private DozerBeanMapper mapper;
    @InjectMocks
    private AddressService addressService;

    @Test
    void getAllAddresses() {
        List<Address> allAddresses = new ArrayList<>();
        final Address address = new Address(1,"turkey","manisa","utku",70,"0000");
        allAddresses.add(address);
        List<AddressDto> allAddressesDTO = allAddresses
                .stream()
                .map(addresses -> mapper.map(addresses, AddressDto.class))
                .collect(Collectors.toList());

        given(addressRepository.findAll()).willReturn(allAddresses);
        final List<AddressDto>  expected = addressService.getAllAddresses();
        assertThat(expected).isNotNull();
        assertEquals(expected, allAddressesDTO);
    }
    @Test
    void getAddressById() {
        int id=2;
        final Address address= new Address(id,"turkey","manisa","utku",70,"0000");
        given(addressRepository.findById(id)).willReturn(java.util.Optional.of(address));
        final AddressDto addressDTO = new AddressDto(id,"turkey","manisa","utku",70,"0000");
        given(mapper.map(address, AddressDto.class)).willReturn(addressDTO);
        final AddressDto expected = addressService.getAddressById(id);
        assertThat(expected).isNotNull();
        assertEquals(expected, addressDTO);
    }

    @Test
    void createAddress(){
        final Address address= new Address(1,"turkey","manisa","utku",70,"0000");
        final AddressRequest addressRequest = new AddressRequest("turkey","manisa","utku",70,"0000");
        final AddressDto addressDto = new AddressDto(1,"turkey","manisa","utku",70,"0000");
        given(mapper.map(addressRequest, Address.class)).willReturn(address);
        given(mapper.map(address, AddressDto.class)).willReturn(addressDto);
        given(addressRepository.save(any(Address.class))).willReturn(address);
        final AddressDto saved= addressService.createAddress(addressRequest);
        assertThat(saved).isNotNull();
    }


    @Test
    void deleteAddress() throws NotFoundException {
        int id=1;
        final Address address = new Address(id,"turkey","manisa","utku",70,"0000");
        given(addressRepository.findById(id)).willReturn(java.util.Optional.of(address));
        addressService.deleteAddress(id);
        verify(addressRepository,times(1)).deleteById(id);
    }


    @Test
    void updateAddress()  {
        final Address address = new Address(1,"turkey","manisa","utku",70,"0000");
        final AddressRequest addressRequest = new AddressRequest("turkey","manisa","utku",70,"0000");
        final Address newAddress = new Address(1,"turkey","manisa","utku",70,"0000");
        given(addressRepository.findById(1)).willReturn(java.util.Optional.of(address));
        given(mapper.map(addressRequest, Address.class)).willReturn(address);
        addressService.updateAddress(1,addressRequest);
        verify(addressRepository).save(newAddress);

    }




}