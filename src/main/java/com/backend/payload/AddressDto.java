package com.backend.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddressDto {
    private Integer id;
    private String street;
    private String city;
    private String state;
    private String zipcode;
    private String country;
    private boolean isDefault;
}

