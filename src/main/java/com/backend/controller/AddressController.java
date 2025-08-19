package com.backend.controller;

import com.backend.payload.AddressDto;
import com.backend.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    // ➕ Add a new address for a user
    @PostMapping
    public ResponseEntity<AddressDto> addAddress(
            @PathVariable int userId,
            @RequestBody AddressDto addressDto) {
        return ResponseEntity.ok(addressService.addAddress(userId, addressDto));
    }

    // 📌 Get all addresses of a user
    @GetMapping
    public ResponseEntity<List<AddressDto>> getUserAddresses(@PathVariable int userId) {
        return ResponseEntity.ok(addressService.getAddressesByUser(userId));
    }

    // 📌 Get a single address by ID
    @GetMapping("/{addressId}")
    public ResponseEntity<AddressDto> getAddressById(
            @PathVariable int userId,
            @PathVariable int addressId) {
        return ResponseEntity.ok(addressService.getAddressById( addressId));
    }

    // ✏️ Update an address
    @PutMapping("/{addressId}")
    public ResponseEntity<AddressDto> updateAddress(
            @PathVariable int userId,
            @PathVariable int addressId,
            @RequestBody AddressDto addressDto) {
        return ResponseEntity.ok(addressService.updateAddress(addressId, addressDto));
    }

    // ❌ Delete an address
    @DeleteMapping("/{addressId}")
    public ResponseEntity<String> deleteAddress(
            @PathVariable int userId,
            @PathVariable int addressId) {
        addressService.deleteAddress(addressId);
        return ResponseEntity.ok("Address deleted successfully");
    }
}
