package ru.mirea.coursework.butchershop.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.mirea.coursework.butchershop.entities.UserEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class User {
    private UUID id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String phone;

    public static User toModel(UserEntity userEntity) {
        User user = new User();
        user.setId(userEntity.getId());
        user.setFirstName(userEntity.getFirstName());
        user.setLastName(userEntity.getLastName());
        user.setUsername(userEntity.getUsername());
        user.setEmail(userEntity.getEmail());
        user.setPhone(userEntity.getPhone());

        return user;
    }
    public static List<User> toModel(List<UserEntity> userEntities) {
        List<User> users = new ArrayList<>();
        for (UserEntity user : userEntities) {
            users.add(toModel(user));
        }
        return users;
    }
}
