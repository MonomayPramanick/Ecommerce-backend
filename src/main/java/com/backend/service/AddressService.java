package com.backend.service;

import com.backend.payload.AddressDto;
import java.util.List;

public interface AddressService {

    AddressDto addAddress(int userId, AddressDto addressDto);

    AddressDto updateAddress(int addressId, AddressDto addressDto);

    void deleteAddress(int addressId);

    List<AddressDto> getAddressesByUser(int userId);

    AddressDto getAddressById(int addressId);
}
