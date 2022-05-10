package ru.mirea.coursework.butchershop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "roles")
@Getter
@Setter
@ToString
public class RoleEntity implements GrantedAuthority {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    @Transient
    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private Set<UserEntity> users = new HashSet<>();

    @Override
    public String getAuthority() {
        return getName();
    }

}
