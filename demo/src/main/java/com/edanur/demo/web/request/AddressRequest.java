package com.edanur.demo.web.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequest {

    private String country;
    private String city;
    private String street;
    private int number;
    private String postCode;
}
