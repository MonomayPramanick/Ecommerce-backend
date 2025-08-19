package com.backend.serviceImpl;

import com.backend.exception.ResourceNotFoundException;
import com.backend.model.Address;
import com.backend.model.User;
import com.backend.payload.AddressDto;
import com.backend.repositories.AddressRepo;
import com.backend.repositories.UserRepo;

import com.backend.service.AddressService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepo addressRepository;

    @Autowired
    private UserRepo userRepository;

    @Override
    public AddressDto addAddress(int userId, AddressDto addressDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        Address address = new Address();
        address.setStreet(addressDto.getStreet());
        address.setCity(addressDto.getCity());
        address.setState(addressDto.getState());
        address.setZipcode(addressDto.getZipcode());
        address.setCountry(addressDto.getCountry());
        address.setDefault(addressDto.isDefault());
        address.setUser(user);

        Address savedAddress = addressRepository.save(address);
        return mapToDto(savedAddress);
    }

    @Override
    public AddressDto updateAddress(int addressId, AddressDto addressDto) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address", "id", addressId));

        address.setStreet(addressDto.getStreet());
        address.setCity(addressDto.getCity());
        address.setState(addressDto.getState());
        address.setZipcode(addressDto.getZipcode());
        address.setCountry(addressDto.getCountry());
        address.setDefault(addressDto.isDefault());

        Address updatedAddress = addressRepository.save(address);
        return mapToDto(updatedAddress);
    }

    @Override
    public void deleteAddress(int addressId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address", "id", addressId));
        addressRepository.delete(address);
    }

    @Override
    public List<AddressDto> getAddressesByUser(int userId) {
        List<Address> addresses = addressRepository.findByUserId(userId);
        return addresses.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public AddressDto getAddressById(int addressId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address", "id", addressId));
        return mapToDto(address);
    }

    private AddressDto mapToDto(Address address) {
        AddressDto dto = new AddressDto();
        dto.setId(address.getId());
        dto.setStreet(address.getStreet());
        dto.setCity(address.getCity());
        dto.setState(address.getState());
        dto.setZipcode(address.getZipcode());
        dto.setCountry(address.getCountry());
        dto.setDefault(address.isDefault());
        return dto;
    }
}
