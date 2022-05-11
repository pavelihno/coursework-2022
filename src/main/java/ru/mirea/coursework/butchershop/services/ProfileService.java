package ru.mirea.coursework.butchershop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mirea.coursework.butchershop.entities.AddressEntity;
import ru.mirea.coursework.butchershop.entities.UserEntity;
import ru.mirea.coursework.butchershop.repos.AddressRepo;
import ru.mirea.coursework.butchershop.repos.UserRepo;

@Service
public class ProfileService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private AddressRepo addressRepo;

    public void updateMainInfo(UserEntity currentUser, UserEntity updatedUser) {
        currentUser.setPhone(updatedUser.getPhone());
        currentUser.setFirstName(updatedUser.getFirstName());
        currentUser.setLastName(updatedUser.getLastName());
        currentUser.setEmail(updatedUser.getEmail());
        userRepo.save(currentUser);
    }
    public void depositBalance(UserEntity currentUser, UserEntity updatedUser) {
        currentUser.setBalance(updatedUser.getBalance() + currentUser.getBalance());
        userRepo.save(currentUser);
    }
    public void editAddress(UserEntity currentUser, AddressEntity address) {
        AddressEntity newAddress = currentUser.getAddress();
        newAddress.setCity(address.getCity());
        newAddress.setStreet(address.getStreet());
        newAddress.setBuildingNumber(address.getBuildingNumber());
        newAddress.setApartmentNumber(address.getApartmentNumber());
        newAddress.setComment(address.getComment());
        newAddress.setUser(currentUser);
        addressRepo.save(newAddress);
    }
}
