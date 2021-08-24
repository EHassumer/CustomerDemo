package com.edanur.demo.web.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponse {
    private int id;
    private String country;
    private String city;
    private String street;
    private int number;
    private String postCode;
}
