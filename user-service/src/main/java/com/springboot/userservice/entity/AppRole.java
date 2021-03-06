package com.springboot.userservice.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "app_role", uniqueConstraints = {
        @UniqueConstraint(name = "UNI_ROLE", columnNames = "name")
})
public class AppRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    // @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    // @JoinTable(
    // name = "app_role_permission",
    // joinColumns = @JoinColumn(name = "role_id"),
    // inverseJoinColumns = @JoinColumn(name = "permission_id")
    // )
    // private Set<AppPermission> permissions = new HashSet<>();
}
