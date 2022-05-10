package ru.mirea.coursework.butchershop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mirea.coursework.butchershop.entities.RoleEntity;
import ru.mirea.coursework.butchershop.repos.RoleRepo;

@Service
public class RoleService {
    @Autowired
    private RoleRepo roleRepo;

    public RoleEntity findOrCreateRole(String name) {
        RoleEntity role = roleRepo.findByName(name);
        if (role == null) {
            RoleEntity newRole = new RoleEntity();
            newRole.setName(name);
            roleRepo.save(newRole);
            return newRole;
        }
        return role;
    }
}
