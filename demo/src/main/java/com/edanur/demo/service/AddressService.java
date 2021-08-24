package com.edanur.demo.service;

import com.edanur.demo.exception.NotFoundException;
import com.edanur.demo.web.dtomodel.AddressDto;
import com.edanur.demo.web.request.AddressRequest;
import com.edanur.demo.dbmodel.Address;
import com.edanur.demo.repository.AddressRepository;

import lombok.RequiredArgsConstructor;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressService implements IAddress {

    private final AddressRepository addressRepository;
    private final DozerBeanMapper mapper;


    public List<AddressDto> getAllAddresses(){
        return (addressRepository
                .findAll())
                .stream()
                .map(address -> mapper.map(address, AddressDto.class))
                .collect(Collectors.toList());
    }
    public AddressDto getAddressById(int id) throws NotFoundException {
        Address address = addressRepository.findById(id)
                .orElseThrow(()->new NotFoundException("There is no address that id is " + id ));

        return mapper.map(address,AddressDto.class);
    }
    public AddressDto createAddress(AddressRequest addressRequest) {
        Address newAddress = mapper.map(addressRequest,Address.class);
        addressRepository.save(newAddress);
        return mapper.map(newAddress,AddressDto.class);
    }
    public void deleteAddress(int id) throws NotFoundException {
        addressRepository.findById(id)
                .orElseThrow(()->new NotFoundException("There is no address that id is " + id ));

        addressRepository.deleteById(id);
    }
    public void updateAddress(int id, AddressRequest addressRequest) throws NotFoundException {

        Address oldAddress = addressRepository.findById(id)
                .orElseThrow(()->new NotFoundException("There is no address that id is " + id ));
        mapper.map(addressRequest,oldAddress);
        addressRepository.save(oldAddress);

    }
}
