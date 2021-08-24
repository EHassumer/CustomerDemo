package com.edanur.demo.web.dtomodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    private int id;
    private String country;
    private String city;
    private String street;
    private int number;
    private String postCode;
}
