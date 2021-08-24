package com.edanur.demo.controller;

import com.edanur.demo.dbmodel.Address;
import com.edanur.demo.web.dtomodel.AddressDto;
import com.edanur.demo.service.AddressService;
import com.edanur.demo.web.request.AddressRequest;
import com.edanur.demo.web.response.AddressResponse;
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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AddressControllerTest {

    @Mock
    private AddressService addressService;
    @Mock
    private DozerBeanMapper mapper;
    @InjectMocks
    private AddressController addressController;

    @Test
    void getAllAddresses() {
        List<AddressDto> allAddressesDTO = new ArrayList<>();
        final AddressDto addressDTO= new AddressDto(1,"turkey","manisa","utku",70,"0000");
        allAddressesDTO.add(addressDTO);
        List<AddressResponse> allAddressesResponse =allAddressesDTO
                .stream()
                .map(addresses -> mapper.map(addresses, AddressResponse.class))
                .collect(Collectors.toList());


        given(addressService.getAllAddresses()).willReturn(allAddressesDTO);
        final List<AddressResponse>  expected = addressController.getAllAddresses();
        assertThat(expected).isNotNull();
        assertEquals(expected, allAddressesResponse);
    }
    @Test
    void getAddress() {
        final AddressDto addressDto= new AddressDto(1,"turkey","manisa","utku",70,"0000");
        final AddressResponse addressResponse= new AddressResponse(1,"turkey","manisa","utku",70,"0000");
        given(addressService.getAddressById(1)).willReturn(addressDto);
        given(mapper.map(addressDto, AddressResponse.class)).willReturn(addressResponse);
        final AddressResponse expected = addressController.getAddress(1);
        assertThat(expected).isNotNull();
        assertEquals(expected, addressResponse);
    }
    @Test
    void createAddress(){
        final AddressDto addressDto= new AddressDto(1,"turkey","manisa","utku",70,"0000");
        final AddressResponse addressResponse= new AddressResponse(1,"turkey","manisa","utku",70,"0000");
        final AddressRequest addressRequest = new AddressRequest("turkey","manisa","utku",70,"0000");
        given(addressService.createAddress(addressRequest)).willReturn(addressDto);
        given(mapper.map(addressService.createAddress(addressRequest), AddressResponse.class)).willReturn(addressResponse);
        final AddressResponse expected = addressController.createAddress(addressRequest);
        assertThat(expected).isNotNull();
        assertEquals(expected, addressResponse);
    }
    @Test
    void deleteAddress(){
        addressController.deleteAddress(1);
        verify(addressService,times(1)).deleteAddress(1);
    }
    @Test
    void updateAddress()  {
        final AddressRequest addressRequest = new AddressRequest("turkey","Izmir","Bornova",7,"0000");
        addressController.updateAddress(1,addressRequest);
        verify(addressService).updateAddress(1,addressRequest);

    }
}