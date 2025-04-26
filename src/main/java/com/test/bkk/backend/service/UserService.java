package com.test.bkk.backend.service;

import com.test.bkk.backend.dto.User;
import com.test.bkk.backend.dto.Address;
import com.test.bkk.backend.dto.Company;
import com.test.bkk.backend.repository.UserRepository;
import com.test.bkk.backend.repository.AddressRepository;
import com.test.bkk.backend.repository.CompanyRepository;
import com.test.bkk.backend.repository.GeoRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


import org.springframework.stereotype.Service;

@Service
public class UserService {
    // private final UserRepository userRepository;
    // private final AddressRepository addressRepository;
    // private final CompanyRepository companyRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final CompanyRepository companyRepository;
    private final GeoRepository geoRepository;


    public UserService(UserRepository userRepository, AddressRepository addressRepository, 
    CompanyRepository companyRepository, GeoRepository geoRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.companyRepository = companyRepository;
        this.geoRepository = geoRepository;
    }

     public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }

    @Transactional
    public void createUser(User user) {
        if (userRepository.findById(user.getId()).isPresent()) {
            throw new IllegalArgumentException("User with this ID already exists");
        }

        // Insert Geo
        int geoId = geoRepository.save(user.getAddress().getGeo());
        user.getAddress().getGeo().setId(geoId);

        // Insert Address
        int addressId = addressRepository.save(user.getAddress());
        user.getAddress().setId(addressId);

        // Insert Company
        int companyId = companyRepository.save(user.getCompany());
        user.getCompany().setId(companyId);

        // Insert User
        userRepository.save(user);
    }

    @Transactional
    public void updateUser(int id, User user) {
        if (userRepository.findById(id).isEmpty()) {
            throw new NoSuchElementException("User not found");
        }

        // Check and update Geo, Address, Company (up to your design — here assumed replace)
        int geoId = geoRepository.save(user.getAddress().getGeo());
        user.getAddress().getGeo().setId(geoId);

        int addressId = addressRepository.save(user.getAddress());
        user.getAddress().setId(addressId);

        int companyId = companyRepository.save(user.getCompany());
        user.getCompany().setId(companyId);

        userRepository.update(user);
    }

    public void deleteUser(int id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new NoSuchElementException("User not found");
        }
        userRepository.deleteById(id);
    }
}
