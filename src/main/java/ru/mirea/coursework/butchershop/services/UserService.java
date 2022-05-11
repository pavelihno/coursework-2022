package ru.mirea.coursework.butchershop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.mirea.coursework.butchershop.entities.RoleEntity;
import ru.mirea.coursework.butchershop.entities.UserEntity;
import ru.mirea.coursework.butchershop.repos.AddressRepo;
import ru.mirea.coursework.butchershop.repos.UserRepo;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private AddressRepo addressRepo;
    @Autowired
    private RoleService roleService;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    private final List adminUsernames = Arrays.asList("ADMIN", "DEMO", "MAIN");

    public List<UserEntity> getAll() {
        return userRepo.findAll();
    }

    public UserEntity addUser(UserEntity user) throws Exception {
        UserEntity userEqualUsername = userRepo.findByUsername(user.getUsername());
        if(userEqualUsername != null) {
            throw new Exception("Пользователь с таким именем уже существует");
        }
        user.setBalance(0L);
        RoleEntity userRole = roleService.findOrCreateRole("USER");
        user.addRole(userRole);
        if(adminUsernames.contains(user.getUsername())) {
            RoleEntity adminRole = roleService.findOrCreateRole("ADMIN");
            user.addRole(adminRole);
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.addAddress(user.getAddress());
        addressRepo.save(user.getAddress());
        userRepo.save(user);
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepo.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }
        return user;
    }
}
