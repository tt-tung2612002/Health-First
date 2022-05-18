package com.springboot.userservice.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user", uniqueConstraints = {
                @UniqueConstraint(name = "uni_username", columnNames = "username")
})
public class AppUser {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @NonNull
        private String username;

        @NonNull
        private String password;

        @NonNull
        private String email;

        @NonNull
        private String displayName;

        @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
        @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
        private Set<AppRole> roles = new HashSet<>();

        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(name = "user_region_management", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "address_id"))
        private Set<Address> addresses = new HashSet<>();

        @OneToMany(mappedBy = "createdUser", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
        private Set<Activity> activities = new HashSet<>();
}
