package com.edanur.demo.service;

import com.edanur.demo.web.dtomodel.AddressDto;
import com.edanur.demo.web.request.AddressRequest;
import javassist.NotFoundException;

import java.util.List;

public interface IAddress {
    public List<AddressDto> getAllAddresses();
    public AddressDto getAddressById(int id) throws NotFoundException;
    public AddressDto createAddress(AddressRequest address);
    public void deleteAddress(int id) throws NotFoundException;
    public void updateAddress(int id, AddressRequest addressRequest) throws NotFoundException;
}
