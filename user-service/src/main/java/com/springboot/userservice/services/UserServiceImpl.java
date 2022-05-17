package com.springboot.userservice.services;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.springboot.userservice.entity.Address;
import com.springboot.userservice.entity.AppRole;
import com.springboot.userservice.entity.AppUser;
import com.springboot.userservice.repository.AddressRepository;
import com.springboot.userservice.repository.AppRoleRepository;
import com.springboot.userservice.repository.AppUserRepository;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {
    private final AppUserRepository userRepository;
    private final AppRoleRepository roleRepository;
    private final AddressRepository addressRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AppUser getUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public AppUser saveUser(AppUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public AppRole saveRole(AppRole role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        AppUser user = userRepository.findByUsername(username);
        AppRole role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public List<AppUser> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        Collection<GrantedAuthority> grantedAuthorities = user.getRoles().stream()
                .map(role -> (GrantedAuthority) role::getName).collect(Collectors.toList());
        return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }

    @Override
    public void addAddressToUser(Integer id, String username) {
        AppUser user = userRepository.findByUsername(username);
        Address address = addressRepository.findById(id);
        user.getAddresses().add(address);
    }
}
