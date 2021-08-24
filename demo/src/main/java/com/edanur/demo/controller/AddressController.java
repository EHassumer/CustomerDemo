package com.edanur.demo.controller;

import com.edanur.demo.web.dtomodel.AddressDto;
import com.edanur.demo.web.request.AddressRequest;
import com.edanur.demo.service.AddressService;
import com.edanur.demo.web.response.AddressResponse;
import com.edanur.demo.web.response.CustomerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.dozer.DozerBeanMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/address")
@Api(value = "Address Api documentation")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;
    private final DozerBeanMapper mapper;


    @GetMapping
    @ApiOperation(value = "All addresses getting method")
    public List<AddressResponse> getAllAddresses() {
        return (addressService.getAllAddresses())
                .stream()
                .map(address -> mapper.map(address, AddressResponse.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{addressId}")
    @ApiOperation(value = "Getting a address with id")
    public AddressResponse getAddress(@PathVariable int addressId){
        return mapper.map(addressService.getAddressById(addressId),AddressResponse.class);
    }


    @PostMapping
    @ApiOperation(value = "New Address creating method")
    public AddressResponse createAddress(@RequestBody AddressRequest addressRequest){
        return mapper.map(addressService.createAddress(addressRequest),AddressResponse.class);
    }


    @DeleteMapping("/{addressId}")
    @ApiOperation(value = "Address deleting method")
    public void deleteAddress(@PathVariable("addressId") int addressId) {
        addressService.deleteAddress(addressId);
    }


    @PutMapping("/{addressId}")
    @ApiOperation(value = "Address updating method")
    public void updateAddress(@PathVariable("addressId") int addressId,
                                @RequestBody AddressRequest addressRequest){
       addressService.updateAddress(addressId, addressRequest);
    }
}
