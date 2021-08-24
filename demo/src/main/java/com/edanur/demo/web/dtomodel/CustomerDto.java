package com.edanur.demo.web.dtomodel;

import com.edanur.demo.dbmodel.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private int id;
    private String firstName;
    private String lastName;
    private Address address;
    private String email;
    private String mobilePhone;
}
